package model.functions;

import model.SimpleRGBImage;
import model.StoredImage;

/**
 * A class for making grayscale versions of images.
 */
public abstract class AbstractNewGrayscale extends AbstractTransformImage {
  /**
   * Constructs a new AbstractNewGrayscale given the original image name and the name of the new
   * image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  protected AbstractNewGrayscale(String imageName, String destName) {
    super(imageName, destName);
  }

  @Override
  protected StoredImage generateImage(StoredImage original) {
    int h = original.getHeight();
    int w = original.getWidth();
    int[][] grayscale = new int[h][w];
    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        int n = this.component(j, i, original);
        grayscale[i][j] = n;
      }
    }
    return new SimpleRGBImage(w, h, grayscale, grayscale, grayscale);
  }

  /**
   * Calculates the one component that will be used for red, green, and blue values in the
   * grayscale image for one pixel.
   *
   * @param x     x value of pixel
   * @param y     y value of pixel
   * @param image image to take pixel from
   * @return value of the component
   */
  protected abstract int component(int x, int y, StoredImage image);
}
