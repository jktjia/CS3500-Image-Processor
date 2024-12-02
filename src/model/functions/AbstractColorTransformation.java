package model.functions;

import java.util.Arrays;

import model.ImageUtils;
import model.SimpleRGBImage;
import model.StoredImage;

/**
 * An abstraction of all image functions that create a new image by changing the colors using
 * matrix multiplication. All values are rounded down to an integer.
 */
public abstract class AbstractColorTransformation extends AbstractTransformImage {
  private final double[][] matrix;

  /**
   * Constructs a new AbstractColorTransformation given the original image name and the name of the
   * new image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   * @param matrix    2d array representing the transformation matrix
   */
  protected AbstractColorTransformation(String imageName, String destName, double[][] matrix) {
    super(imageName, destName);
    if (matrix.length != 3 || Arrays.stream(matrix).anyMatch(arr -> arr.length != 3)) {
      throw new IllegalArgumentException("Matrix is not a 3x3");
    }
    this.matrix = matrix;
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
        int red = original.getRed(x, y);
        int green = original.getGreen(x, y);
        int blue = original.getBlue(x, y);
        r[y][x] = ImageUtils.clampValue((int)
                        (this.matrix[0][0] * red
                                + this.matrix[0][1] * green
                                + this.matrix[0][2] * blue));
        g[y][x] = ImageUtils.clampValue((int)
                        (this.matrix[1][0] * red
                                + this.matrix[1][1] * green
                                + this.matrix[1][2] * blue));
        b[y][x] = ImageUtils.clampValue((int)
                        (matrix[2][0] * red
                                + matrix[2][1] * green
                                + matrix[2][2] * blue));
      }
    }
    return new SimpleRGBImage(w, h, r, g, b);
  }
}
