package model.functions;

import model.StoredImage;

import static model.ImageUtils.value;

/**
 * A class to create a new grayscale image based on the value component of another image.
 */
public class ValueComponent extends AbstractNewGrayscale {
  /**
   * Constructs a new ValueComponent given the original image name and the name of the new image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public ValueComponent(String imageName, String destName) {
    super(imageName, destName);
  }

  @Override
  protected int component(int x, int y, StoredImage image) {
    return value(image.getRed(x, y), image.getGreen(x, y), image.getBlue(x, y));
  }
}
