package model.functions;

import model.StoredImage;

/**
 * A class to create a new grayscale image based on the blue component of another image.
 */
public class BlueComponent extends AbstractNewGrayscale {
  /**
   * Constructs a new BlueComponent given the original image name and the name of the new image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public BlueComponent(String imageName, String destName) {
    super(imageName, destName);
  }

  @Override
  protected int component(int x, int y, StoredImage image) {
    return image.getBlue(x, y);
  }
}
