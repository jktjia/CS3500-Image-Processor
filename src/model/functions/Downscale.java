package model.functions;

import model.SimpleRGBImage;
import model.StoredImage;

/**
 * A class to downscale an image.
 */
public class Downscale extends AbstractTransformImage {
  private final int newWidth;
  private final int newHeight;

  /**
   * Constructs a new Downscale given the original image name and the name of the new image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public Downscale(int newWidth, int newHeight, String imageName, String destName) {
    super(imageName, destName);
    if (newWidth <= 0 || newHeight <= 0) {
      throw new IllegalArgumentException("Width and height must be positive");
    }
    this.newWidth = newWidth;
    this.newHeight = newHeight;
  }

  @Override
  protected StoredImage generateImage(StoredImage original) {
    if (this.newWidth > original.getWidth()
            || this.newHeight > original.getHeight()) {
      throw new IllegalArgumentException("Downscaled image must be smaller than original image");
    }

    int[][] r = new int[this.newHeight][this.newWidth];
    int[][] g = new int[this.newHeight][this.newWidth];
    int[][] b = new int[this.newHeight][this.newWidth];
    for (int i = 0; i < this.newHeight; i++) {
      for (int j = 0; j < this.newWidth; j++) {
        double x = (double) j * original.getWidth() / this.newWidth;
        double y = (double) i * original.getHeight() / this.newHeight;
        int floorX = (int) x;
        int ceilingX = (int) x + 1;
        int floorY = (int) y;
        int ceilingY = (int) y + 1;

        r[i][j] = calculateNewColor(original.getRed(floorX, floorY),
                                    original.getRed(ceilingX, floorY),
                                    original.getRed(floorX, ceilingY),
                                    original.getRed(ceilingX, ceilingY),
                                    floorX, ceilingX, floorY, ceilingY, x, y);
        g[i][j] = calculateNewColor(original.getGreen(floorX, floorY),
                                    original.getGreen(ceilingX, floorY),
                                    original.getGreen(floorX, ceilingY),
                                    original.getGreen(ceilingX, ceilingY),
                                    floorX, ceilingX, floorY, ceilingY, x, y);
        b[i][j] = calculateNewColor(original.getBlue(floorX, floorY),
                                    original.getBlue(ceilingX, floorY),
                                    original.getBlue(floorX, ceilingY),
                                    original.getBlue(ceilingX, ceilingY),
                                    floorX, ceilingX, floorY, ceilingY, x, y);
      }
    }
    return new SimpleRGBImage(newWidth, newHeight, r, g, b);
  }

  private int calculateNewColor(int a, int b, int c, int d,
                                int floorX, int ceilingX, int floorY, int ceilingY,
                                double x, double y) {
    double m = b * (x - floorX) + a * (ceilingX - x);
    double n = d * (x - floorX) + c * (ceilingX - x);
    return (int) (n * (y - floorY) + m * (ceilingY - y));
  }
}
