package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;

import controller.Features;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.SwingConstants.CENTER;

/**
 * A class for a GUI that shows the image that is currently being worked on. The image may be
 * bigger than the area allocated to it, but in this case, the user can scroll the image. The
 * histograms of the red, blue, green, and intensity values of the visible image are visible as
 * line charts on the screen at all times. If the image is manipulated, the histogram automatically
 * refreshes. There are buttons for different commands for manipulating images and for loading and
 * saving images. Also, there is a panel that displays the names of all the images in storage and
 * allows the user to select one of the images to display.
 */
public class SwingGuiView extends JFrame implements GuiView {
  private final JLabel centerImage;
  private final ControllerInteracter images;
  private final ActionInteracter commands;
  private final JPanel redHistogram;
  private final JPanel greenHistogram;
  private final JPanel blueHistogram;
  private final JPanel intensityHistogram;
  private final JButton load;
  private final JButton save;
  private Features controller;

  /**
   * Creates a new SwingGuiView.
   */
  public SwingGuiView() {
    super("Image Processor");

    int imageHeight = 500;
    int imageWidth = 750;
    int sideWidth = 150;
    int bottomHeight = 175;
    int bottomWidth = 170;

    setSize(2 * sideWidth + imageWidth + 50, imageHeight + 225);
    this.setMinimumSize(new Dimension(450, 300));
    this.setMaximumSize(new Dimension(
            2 * sideWidth + imageWidth + 50,
            imageHeight + 225));
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    JPanel centerPanel = new JPanel();
    this.centerImage = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(centerImage);
    imageScrollPane.setPreferredSize(new Dimension(imageWidth, imageHeight));
    centerPanel.add(imageScrollPane);
    this.add(centerPanel, BorderLayout.CENTER);

    ImageNamesPanel imageNames = new ImageNamesPanel(sideWidth, imageHeight);
    this.images = imageNames;
    JScrollPane scrollImages = new JScrollPane(imageNames);
    scrollImages.setPreferredSize(new Dimension(sideWidth, imageHeight));
    this.add(scrollImages, BorderLayout.WEST);

    ButtonCommandPanel buttonCommands =
            new ButtonCommandPanel(sideWidth, 300);
    this.commands = buttonCommands;
    JScrollPane scrollCommands = new JScrollPane(buttonCommands);
    scrollCommands.setPreferredSize(new Dimension(sideWidth, imageHeight));
    this.add(new JScrollPane(buttonCommands), BorderLayout.EAST);

    JPanel bottom = new JPanel();

    bottom.setLayout(new FlowLayout());
    JPanel histogramPanel = new JPanel();
    histogramPanel.setLayout(new GridLayout(1, 0));
    histogramPanel.setPreferredSize(new Dimension(imageWidth, bottomHeight));
    this.redHistogram = histogramHolder("Red values", bottomWidth, bottomHeight);
    histogramPanel.add(redHistogram);
    this.greenHistogram = histogramHolder("Green values", bottomWidth, bottomHeight);
    histogramPanel.add(greenHistogram);
    this.blueHistogram = histogramHolder("Blue values", bottomWidth, bottomHeight);
    histogramPanel.add(blueHistogram);
    this.intensityHistogram = histogramHolder("Intensity values", bottomWidth, bottomHeight);
    histogramPanel.add(intensityHistogram);
    bottom.add(histogramPanel);

    JPanel saveLoad = new JPanel();
    saveLoad.setLayout(new GridLayout(0, 1));
    this.load = new JButton("Load image");
    this.load.setActionCommand("load");
    this.save = new JButton("Save image");
    this.save.setActionCommand("save");
    saveLoad.add(this.load);
    saveLoad.add(this.save);
    bottom.add(saveLoad);
    this.add(bottom, BorderLayout.SOUTH);

    this.refresh();
  }

  /**
   * Creates a panel to hold a histogram given the name of the histogram and the width and height
   * of the area for the histogram.
   *
   * @param name   name of the histogram
   * @param width  width of the histogram
   * @param height height of the histogram
   * @return JPanel that will contain a histogram
   */
  private static JPanel histogramHolder(String name, int width, int height) {
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(width, height));
    panel.setLayout(new BorderLayout());
    JLabel label = new JLabel(name);
    label.setHorizontalAlignment(CENTER);
    panel.add(label, BorderLayout.NORTH);
    return panel;
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh() {
    this.setCenterImage();
    this.setHistograms();
    this.images.refresh();
    this.commands.refresh();
    this.pack();
  }

  /**
   * Sets the center image to the controller's currently selected image.
   */
  private void setCenterImage() {
    if (this.controller == null || this.controller.currentImage() == null) {
      this.centerImage.setText("No image selected");
    } else {
      this.centerImage.setIcon(new ImageIcon(this.controller.currentImage()));
      this.centerImage.setText("");
    }
    this.centerImage.setHorizontalAlignment(CENTER);
  }

  /**
   * Sets the histograms to match the controller's currently selected image.
   */
  private void setHistograms() {
    if (this.controller == null || this.controller.currentImage() == null) {
      JLabel noImage = new JLabel("No image selected");
      noImage.setHorizontalAlignment(CENTER);
      noImage.setBorder(BorderFactory.createLineBorder(Color.black));
      this.redHistogram.add(noImage, BorderLayout.CENTER);
      noImage = new JLabel("No image selected");
      noImage.setHorizontalAlignment(CENTER);
      noImage.setBorder(BorderFactory.createLineBorder(Color.black));
      this.greenHistogram.add(noImage, BorderLayout.CENTER);
      noImage = new JLabel("No image selected");
      noImage.setHorizontalAlignment(CENTER);
      noImage.setBorder(BorderFactory.createLineBorder(Color.black));
      this.blueHistogram.add(noImage, BorderLayout.CENTER);
      noImage = new JLabel("No image selected");
      noImage.setHorizontalAlignment(CENTER);
      noImage.setBorder(BorderFactory.createLineBorder(Color.black));
      this.intensityHistogram.add(noImage, BorderLayout.CENTER);
    } else {
      this.redHistogram.remove(1);
      this.greenHistogram.remove(1);
      this.blueHistogram.remove(1);
      this.intensityHistogram.remove(1);

      this.redHistogram.add(new ValueHistogram(
              this.controller.currentReds(16), Color.RED));
      this.greenHistogram.add(new ValueHistogram(
              this.controller.currentGreens(16), Color.GREEN));
      this.blueHistogram.add(new ValueHistogram(
              this.controller.currentBlues(16), Color.BLUE));
      this.intensityHistogram.add(new ValueHistogram(
              this.controller.currentIntensities(16), Color.BLACK));
      this.repaint();
    }
  }

  @Override
  public String requestLoadLoc() {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Loadable Images", "ppm", "jpg", "png", "bmp");
    chooser.setFileFilter(filter);
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      File f = chooser.getSelectedFile();
      return f.getAbsolutePath();
    } else {
      throw new IllegalStateException("No load location chosen");
    }
  }

  @Override
  public String requestSaveLoc() {
    final JFileChooser chooser = new JFileChooser(".");
    if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
      File f = chooser.getSelectedFile();
      return f.getAbsolutePath();
    } else {
      throw new IllegalStateException("No save location chosen");
    }
  }

  @Override
  public String requestImageName() {
    return JOptionPane.showInputDialog("Name of new image:");
  }

  @Override
  public int requestInteger(String message) {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2,1));
    panel.add(new JLabel(message));

    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setAllowsInvalid(false);
    JFormattedTextField field = new JFormattedTextField(formatter);
    panel.add(field);

    JOptionPane.showMessageDialog(this, panel);
    return (int) field.getValue();
  }

  @Override
  public void errorPopUp(String message) {
    JOptionPane.showMessageDialog(
            this, message, "Error Message", ERROR_MESSAGE);
  }

  @Override
  public void setController(Features controller) {
    this.controller = controller;
    this.images.setController(controller);
    this.commands.setController(controller);
  }

  @Override
  public void setActionListener(ActionListener listener) {
    this.commands.setActionListener(listener);
    this.load.addActionListener(listener);
    this.save.addActionListener(listener);
  }
}
