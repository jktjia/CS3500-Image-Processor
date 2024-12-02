package model.functions;

import java.util.Objects;

import model.ImageStorage;
import model.functions.loadin.ImageLoader;

/**
 * A class to load an image to storage.
 */
public class Load implements ImageFunction {
  private final String imagePath;
  private final String imageName;
  private final ImageLoader loader;

  /**
   * Creates a new LoadImage given the location of the image to be loaded, the name of the image,
   * and an ImageLoader to load images from files.
   *
   * @param imagePath image location
   * @param imageName name of image in storage
   * @param loader    class to load images from files
   */
  public Load(String imagePath, String imageName, ImageLoader loader) {
    this.imagePath = imagePath;
    this.imageName = imageName;
    this.loader = Objects.requireNonNull(loader);
  }

  @Override
  public void run(ImageStorage storage) {
    storage.add(imageName, this.loader.load(imagePath));
  }
}
