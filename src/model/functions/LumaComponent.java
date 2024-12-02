package model.functions;

import model.StoredImage;

import static model.ImageUtils.luma;

/**
 * A class to create a new grayscale image based on the luma component of another image.
 */
public class LumaComponent extends AbstractNewGrayscale {
  /**
   * Constructs a new LumaComponent given the original image name and the name of the new image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public LumaComponent(String imageName, String destName) {
    super(imageName, destName);
  }

  @Override
  protected int component(int x, int y, StoredImage image) {
    return luma(image.getRed(x, y), image.getGreen(x, y), image.getBlue(x, y));
  }
}
