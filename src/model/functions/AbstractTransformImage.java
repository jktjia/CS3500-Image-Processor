package model.functions;

import model.ImageStorage;
import model.StoredImage;

/**
 * An abstraction of all image functions that create a new image based on another image.
 */
public abstract class AbstractTransformImage implements ImageFunction {
  private final String imageName;
  private final String destName;

  /**
   * Constructs a new AbstractTransformImage given the original image name and the name of the new
   * image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  protected AbstractTransformImage(String imageName, String destName) {
    this.imageName = imageName;
    this.destName = destName;
  }

  @Override
  public void run(ImageStorage storage) {
    storage.add(destName, this.generateImage(storage.getImage(this.imageName)));
  }

  /**
   * Creates a new transformed image based on the original image.
   *
   * @param original original image to transform
   * @return new transformed image
   */
  protected abstract StoredImage generateImage(StoredImage original);
}
