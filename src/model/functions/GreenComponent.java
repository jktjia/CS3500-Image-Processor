package model.functions;

import model.StoredImage;

/**
 * A class to create a new grayscale image based on the green component of another image.
 */
public class GreenComponent extends AbstractNewGrayscale {
  /**
   * Constructs a new GreenComponent given the original image name and the name of the new image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public GreenComponent(String imageName, String destName) {
    super(imageName, destName);
  }

  @Override
  protected int component(int x, int y, StoredImage image) {
    return image.getGreen(x, y);
  }
}
