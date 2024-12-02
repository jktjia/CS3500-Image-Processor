package model.functions;

import model.ImageUtils;
import model.SimpleRGBImage;
import model.StoredImage;

/**
 * A class to brighten the image by the given increment to create a new image, referred to
 * henceforth by the given destination name.
 */
public class Brighten extends AbstractTransformImage {
  private final int increment;

  /**
   * Constructs a new Brighten given the amount to increment by, the original image name and the
   * name of the new image.
   *
   * @param increment amount to increment image by
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public Brighten(int increment, String imageName, String destName) {
    super(imageName, destName);
    this.increment = increment;
  }

  @Override
  protected StoredImage generateImage(StoredImage original) {
    int h = original.getHeight();
    int w = original.getWidth();
    int[][] r = new int[h][w];
    int[][] g = new int[h][w];
    int[][] b = new int[h][w];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        r[i][j] = ImageUtils.clampValue(original.getRed(j, i) + this.increment);
        g[i][j] = ImageUtils.clampValue(original.getGreen(j, i) + this.increment);
        b[i][j] = ImageUtils.clampValue(original.getBlue(j, i) + this.increment);
      }
    }
    return new SimpleRGBImage(w, h, r, g, b);
  }
}
