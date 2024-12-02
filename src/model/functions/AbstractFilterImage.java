package model.functions;

import java.util.Arrays;

import model.ImageUtils;
import model.SimpleRGBImage;
import model.StoredImage;

/**
 * An abstraction for all image functions that create new images by applying a filter to another
 * image.
 */
public abstract class AbstractFilterImage extends AbstractTransformImage {
  private final int middle;
  private final double[][] arr;

  /**
   * Constructs a new AbstractFilterImage given the original image name and the name of the new
   * image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   * @param arr       2d array representing the filter (dimensions must be the same and odd)
   */
  protected AbstractFilterImage(String imageName, String destName, double[][] arr) {
    super(imageName, destName);
    int l = arr.length;
    if (l % 2 != 1 || Arrays.stream(arr).anyMatch(a -> a.length != l)) {
      throw new IllegalArgumentException("arr is not a odd square");
    }
    this.arr = arr;
    this.middle = l / 2;
  }

  @Override
  protected StoredImage generateImage(StoredImage original) {
    int h = original.getHeight();
    int w = original.getWidth();
    int[][] r = new int[h][w];
    int[][] g = new int[h][w];
    int[][] b = new int[h][w];
    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        double red = 0;
        double green = 0;
        double blue = 0;
        for (int i = Math.max(this.middle - y, 0);
             i < Math.min(this.middle + h - 1 - y, this.arr.length); i++) {
          for (int j = Math.max(this.middle - x, 0);
               j < Math.min(this.middle + w - 1 - x, this.arr.length); j++) {
            red += this.arr[i][j]
                    * original.getRed(x + j - this.middle, y + i - this.middle);
            green += this.arr[i][j]
                    * original.getGreen(x + j - this.middle, y + i - this.middle);
            blue += this.arr[i][j]
                    * original.getBlue(x + j - this.middle, y + i - this.middle);
          }
        }
        r[y][x] = ImageUtils.clampValue((int) red);
        g[y][x] = ImageUtils.clampValue((int) green);
        b[y][x] = ImageUtils.clampValue((int) blue);
      }
    }
    return new SimpleRGBImage(w, h, r, g, b);
  }
}
