package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.ImageStorage;
import model.StoredImage;
import model.functions.BlueComponent;
import model.functions.Blur;
import model.functions.Brighten;
import model.functions.FlipHorizontal;
import model.functions.FlipVertical;
import model.functions.Grayscale;
import model.functions.GreenComponent;
import model.functions.ImageFunction;
import model.functions.IntensityComponent;
import model.functions.Load;
import model.functions.LumaComponent;
import model.functions.RedComponent;
import model.functions.Sepia;
import model.functions.Sharpen;
import model.functions.ValueComponent;
import model.functions.loadin.ImageIOCompatibleLoader;
import model.functions.loadin.ImageLoader;
import model.functions.loadin.PPMLoader;
import view.GuiView;

/**
 * An image controller with the same functionality as TextImageController that extends Features and
 * takes inputs from a GuiView.
 */
public class GuiController implements ImageController, Features, ActionListener {
  private final ImageStorage storage;
  protected final GuiView view; // TODO this is temporary
  protected final Map<String, Runnable> actions; // TODO this is temporary
  protected String currentImage; // TODO this is temporary

  /**
   * Constructs a new GuiController given the storage and view.
   *
   * @param storage place to store images used in this program
   * @param guiView view for user interaction
   */
  public GuiController(ImageStorage storage, GuiView guiView) {
    this.storage = storage;
    this.view = guiView;

    this.actions = new HashMap<>();
    this.actions.put("save", (this::save));
    this.actions.put("load", (this::load));
    this.actions.put("red component", (() -> {
      String newImage = this.view.requestImageName();
      this.runFunction(
              new RedComponent(this.currentImage, newImage));
      this.currentImage = newImage;
    }));
    this.actions.put("green component", (() -> {
      String newImage = this.view.requestImageName();
      this.runFunction(
              new GreenComponent(this.currentImage, newImage));
      this.currentImage = newImage;
    }));
    this.actions.put("blue component", (() -> {
      String newImage = this.view.requestImageName();
      this.runFunction(
              new BlueComponent(this.currentImage, newImage));
      this.currentImage = newImage;
    }));
    this.actions.put("value component", (() -> {
      String newImage = this.view.requestImageName();
      this.runFunction(
              new ValueComponent(this.currentImage, newImage));
      this.currentImage = newImage;
    }));
    this.actions.put("intensity component", (() -> {
      String newImage = this.view.requestImageName();
      this.runFunction(
              new IntensityComponent(this.currentImage, newImage));
      this.currentImage = newImage;
    }));
    this.actions.put("luma component", (() -> {
      String newImage = this.view.requestImageName();
      this.runFunction(
              new LumaComponent(this.currentImage, newImage));
      this.currentImage = newImage;
    }));
    this.actions.put("horizontal flip", (() -> {
      String newImage = this.view.requestImageName();
      this.runFunction(
              new FlipHorizontal(this.currentImage, newImage));
      this.currentImage = newImage;
    }));
    this.actions.put("vertical flip", (() -> {
      String newImage = this.view.requestImageName();
      this.runFunction(
              new FlipVertical(this.currentImage, newImage));
      this.currentImage = newImage;
    }));
    this.actions.put("brighten", (() -> {
      int increment = this.view.requestInteger("Increment to brighten image by");
      String newImage = this.view.requestImageName();
      this.runFunction(
              new Brighten(increment, this.currentImage, newImage));
      this.currentImage = newImage;
    }));
    this.actions.put("grayscale", (() -> {
      String newImage = this.view.requestImageName();
      this.runFunction(
              new Grayscale(this.currentImage, newImage));
      this.currentImage = newImage;
    }));
    this.actions.put("sepia", (() -> {
      String newImage = this.view.requestImageName();
      this.runFunction(
              new Sepia(this.currentImage, newImage));
      this.currentImage = newImage;
    }));
    this.actions.put("blur", (() -> {
      String newImage = this.view.requestImageName();
      this.runFunction(
              new Blur(this.currentImage, newImage));
      this.currentImage = newImage;
    }));
    this.actions.put("sharpen", (() -> {
      String newImage = this.view.requestImageName();
      this.runFunction(
              new Sharpen(this.currentImage, newImage));
      this.currentImage = newImage;
    }));
  }

  @Override
  public void run() {
    this.view.setController(this);
    this.view.setActionListener(this);

    view.makeVisible();
  }

  @Override
  public void selectedImage(String imageName) {
    this.currentImage = imageName;
    this.view.refresh();
  }

  @Override
  public Image currentImage() {
    return storage.viewImage(this.currentImage);
  }

  @Override
  public int[] currentReds(int numBoxes) {
    return this.storage.getImage(this.currentImage).redHistogram(numBoxes);
  }

  @Override
  public int[] currentGreens(int numBoxes) {
    return this.storage.getImage(this.currentImage).greenHistogram(numBoxes);
  }

  @Override
  public int[] currentBlues(int numBoxes) {
    return this.storage.getImage(this.currentImage).blueHistogram(numBoxes);
  }

  @Override
  public int[] currentIntensities(int numBoxes) {
    return this.storage.getImage(this.currentImage).intensityHistogram(numBoxes);
  }

  @Override
  public String[] imageNames() {
    return this.storage.imagesByName();
  }

  private void save() {
    if (this.currentImage == null) {
      throw new IllegalStateException("No image to save");
    } else {
      String location = this.view.requestSaveLoc();
      if (location != null) {
        // this will overwrite a file if the imagePath leads to an existing file
        Path path = Path.of(location);
        if (!Files.exists(path)) {
          try {
            Files.createFile(path);
          } catch (IOException e) {
            throw new IllegalStateException("Unable to create file");
          }
        }
        File file = new File(location);
        StoredImage image = this.storage.getImage(currentImage);
        String extension = this.extension(location);
        if (extension.equals("ppm")) {
          SaveUtils.saveFilePPM(file, image);
        } else if (extension.equals("jpg") || extension.equals("png") || extension.equals("bmp")) {
          SaveUtils.saveFileImageIOCompatible(file, image, extension);
        } else {
          throw new IllegalArgumentException("Unknown file type");
        }
      }
    }
  }

  private void load() {
    String location = this.view.requestLoadLoc();
    if (location != null) {
      String name = this.view.requestImageName();
      if (name != null) {
        ImageLoader l;
        String extension = this.extension(location);
        if (extension.equalsIgnoreCase("ppm")) {
          l = new PPMLoader();
        } else if (extension.equalsIgnoreCase("jpg")
                || extension.equalsIgnoreCase("png")
                || extension.equalsIgnoreCase("bmp")) {
          l = new ImageIOCompatibleLoader();
        } else {
          throw new IllegalArgumentException("Unknown file type");
        }
        new Load(location, name, l).run(this.storage);
        this.currentImage = name;
      }
    }
  }

  private String extension(String filePath) {
    String[] arr = filePath.split("[.]");
    return arr[arr.length - 1];
  }

  protected void runFunction(ImageFunction f) { // TODO this is temporary
    if (this.currentImage == null) {
      throw new IllegalStateException("No image to process");
    } else {
      f.run(this.storage);
    }
  }

  @Override
  public String[] validCommands() {
    Set<String> s = new HashSet<>(this.actions.keySet());
    s.remove("load");
    s.remove("save");
    return s.toArray(new String[0]);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!this.actions.containsKey(e.getActionCommand())) {
      view.errorPopUp("Unrecognized action");
    } else {
      Runnable r = this.actions.get(e.getActionCommand());
      try {
        r.run();
        view.refresh();
      } catch (IllegalArgumentException | IllegalStateException exception) {
        view.errorPopUp(exception.getMessage());
      } catch (NullPointerException exception) {
        view.errorPopUp("Action cancelled due to lack of input");
      }
    }
  }
}