package model;

/**
 * An utils class for the functions in the program.
 */
public class ImageUtils {
  /**
   * Calculates the intensity (average value rounded down) of a pixel given the red, green, and
   * blue values of the pixel.
   *
   * @param r red value
   * @param g green value
   * @param b blue value
   * @return intensity of the pixel
   */
  public static int intensity(int r, int g, int b) {
    return (r + g + b) / 3;
  }

  /**
   * Calculates the luma of a pixel given the red, green, and blue values of the pixel.
   *
   * @param r red value
   * @param g green value
   * @param b blue value
   * @return luma of the pixel
   */
  public static int luma(int r, int g, int b) {
    return (int) ((0.2126 * r) + (0.7152 * g) + (0.0722 * b));
  }

  /**
   * Calculates the value of a pixel given the red, green, and blue values of the pixel.
   *
   * @param r red value
   * @param g green value
   * @param b blue value
   * @return value of the pixel
   */
  public static int value(int r, int g, int b) {
    return Math.max(Math.max(r, g), b);
  }

  /**
   * Clamps a number, so it stays between 0 and 255 inclusive.
   *
   * @param num number to be clamped
   * @return clamped number
   */
  public static int clampValue(int num) {
    if (num < 0) {
      return 0;
    } else if (num > 255) {
      return 255;
    } else {
      return num;
    }
  }
}
