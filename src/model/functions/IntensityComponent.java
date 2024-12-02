package model.functions;

import model.StoredImage;

import static model.ImageUtils.intensity;

/**
 * A class to create a new grayscale image based on the intensity component of another image.
 */
public class IntensityComponent extends AbstractNewGrayscale {
  /**
   * Constructs a new IntensityComponent given the original image name and the name of the new
   * image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public IntensityComponent(String imageName, String destName) {
    super(imageName, destName);
  }

  @Override
  protected int component(int x, int y, StoredImage image) {
    return intensity(image.getRed(x, y), image.getGreen(x, y), image.getBlue(x, y));
  }
}
