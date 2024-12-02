package view;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Features;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

/**
 * A class for a panel showing the names of all the images in storage and allows the user to
 * select one of the image names.
 */
public class ImageNamesPanel extends JPanel implements ControllerInteracter, ListSelectionListener {
  private final DefaultListModel<String> listModel;
  private final JList<String> list;
  private Features controller;

  /**
   * Constructs a new ImageNamesPanel given the width and height of the panel.
   *
   * @param width  width of the panel
   * @param height height of the panel
   */
  public ImageNamesPanel(int width, int height) {
    this.listModel = new DefaultListModel<>();
    this.list = new JList<>(listModel);
    this.list.setPreferredSize(new Dimension(width - 14, height - 14));
    this.list.setSelectionMode(SINGLE_SELECTION);
    this.list.addListSelectionListener(this);
    this.add(this.list);

    this.setMinimumSize(new Dimension(width, 0));
    this.setMaximumSize(new Dimension(width, 900));
  }


  @Override
  public void refresh() {
    if (this.controller != null) {
      String[] names = this.controller.imageNames();
      for (String s : names) {
        if (! this.listModel.contains(s)) {
          this.listModel.addElement(s);
        }
      }
    }
  }

  @Override
  public void setController(Features controller) {
    this.controller = controller;
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    this.controller.selectedImage(this.list.getSelectedValue());
  }
}
