package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Objects;

import static model.ImageUtils.intensity;

/**
 * A class for an image generated from a ppm file.
 */
public class SimpleRGBImage implements StoredImage {
  private final int[][] r;
  private final int[][] g;
  private final int[][] b;

  /**
   * Creates a new storage. SimpleRGBImage given 2D arrays of the colors. The max value is 255, the
   * min is 0.
   *
   * @param r red value at each pixel
   * @param g green value at each pixel
   * @param b blue value at each pixel
   */
  public SimpleRGBImage(int width, int height, int[][] r, int[][] g, int[][] b) {
    checkSize(width, height, r, "Red");
    checkSize(width, height, g, "Green");
    checkSize(width, height, b, "Blue");

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (r[i][j] > 255 || r[i][j] < 0) {
          throw new IllegalArgumentException("Invalid red value at (" + i + "," + j + ")");
        }
        if (g[i][j] > 255 || g[i][j] < 0) {
          throw new IllegalArgumentException("Invalid green value at (" + i + "," + j + ")");
        }
        if (b[i][j] > 255 || b[i][j] < 0) {
          throw new IllegalArgumentException("Invalid blue value at (" + i + "," + j + ")");
        }
      }
    }
    this.r = deepCopy(r);
    this.g = deepCopy(g);
    this.b = deepCopy(b);
  }

  private static void checkSize(int width, int height, int[][] arr, String arrName) {
    if (arr.length != height) {
      throw new IllegalArgumentException(arrName + " array does not match height");
    } else {
      for (int i = 0; i < height; i++) {
        if (arr[i].length != width) {
          throw new IllegalArgumentException(arrName + " array does not match width");
        }
      }
    }
  }

  private static int[][] deepCopy(int[][] original) {
    int[][] toReturn = new int[original.length][];
    for (int i = 0; i < original.length; i++) {
      toReturn[i] = Arrays.copyOf(original[i], original[i].length);
    }
    return toReturn;
  }

  @Override
  public int getRed(int x, int y) {
    return this.r[y][x];
  }

  @Override
  public int getGreen(int x, int y) {
    return this.g[y][x];
  }

  @Override
  public int getBlue(int x, int y) {
    return this.b[y][x];
  }

  @Override
  public int getWidth() {
    return this.r[0].length;
  }

  @Override
  public int getHeight() {
    return this.r.length;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof StoredImage)) {
      return false;
    } else {
      StoredImage other = (StoredImage) o;
      if (!((this.getHeight() == other.getHeight())
              && (this.getWidth() == other.getWidth()))) {
        return false;
      } else {
        for (int i = 0; i < this.getHeight(); i++) {
          for (int j = 0; j < this.getWidth(); j++) {
            if (!((this.getRed(j, i) == other.getRed(j, i))
                    && (this.getGreen(j, i) == other.getGreen(j, i))
                    && (this.getBlue(j, i) == other.getBlue(j, i)))) {
              return false;
            }
          }
        }
      }
      return true;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(
            Arrays.deepHashCode(this.r),
            Arrays.deepHashCode(this.g),
            Arrays.deepHashCode(this.b));
  }

  @Override
  public BufferedImage toBufferedImage() {
    BufferedImage buffered = new BufferedImage(
            this.getWidth(),
            this.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        int red = this.r[i][j];
        int green = this.g[i][j];
        int blue = this.b[i][j];
        buffered.setRGB(j, i,
                new Color(red, green, blue).getRGB());
      }
    }
    return buffered;
  }

  @Override
  public int[] redHistogram(int numBoxes) {
    int[] toReturn = new int[numBoxes];
    Arrays.stream(this.r).flatMapToInt(Arrays::stream)
            .forEach(i -> toReturn[i / (256 / numBoxes)]++);
    return toReturn;
  }

  @Override
  public int[] greenHistogram(int numBoxes) {
    int[] toReturn = new int[numBoxes];
    Arrays.stream(this.g).flatMapToInt(Arrays::stream)
            .forEach(i -> toReturn[i / (256 / numBoxes)]++);
    return toReturn;
  }

  @Override
  public int[] blueHistogram(int numBoxes) {
    int[] toReturn = new int[numBoxes];
    Arrays.stream(this.b).flatMapToInt(Arrays::stream)
            .forEach(i -> toReturn[i / (256 / numBoxes)]++);
    return toReturn;
  }

  @Override
  public int[] intensityHistogram(int numBoxes) {
    int[] toReturn = new int[numBoxes];
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        int intensity = intensity(this.r[i][j], this.g[i][j], this.b[i][j]);
        toReturn[intensity / (256 / numBoxes)]++;
      }
    }
    return toReturn;
  }
}
