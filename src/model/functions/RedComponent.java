package model.functions;

import model.StoredImage;

/**
 * A class to create a new grayscale image based on the red component of another image.
 */
public class RedComponent extends AbstractNewGrayscale {
  /**
   * Constructs a new RedComponent given the original image name and the name of the new image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public RedComponent(String imageName, String destName) {
    super(imageName, destName);
  }

  @Override
  protected int component(int x, int y, StoredImage image) {
    return image.getRed(x, y);
  }
}
