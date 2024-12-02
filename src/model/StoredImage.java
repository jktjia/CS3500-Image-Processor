package model;

/**
 * Represents a stored image that can have the RGB values of any given pixel retrieved.
 */
public interface StoredImage extends ViewableImage {
  /**
   * Gets the red component of the color of the pixel at (x,y).
   *
   * @param x x coordinate of pixel
   * @param y y coordinate of pixel
   * @return red component of the color of the pixel
   */
  int getRed(int x, int y);

  /**
   * Gets the green component of the color of the pixel at (x,y).
   *
   * @param x x coordinate of pixel
   * @param y y coordinate of pixel
   * @return green component of the color of the pixel
   */
  int getGreen(int x, int y);

  /**
   * Gets the blue component of the color of the pixel at (x,y).
   *
   * @param x x coordinate of pixel
   * @param y y coordinate of pixel
   * @return blue component of the color of the pixel
   */
  int getBlue(int x, int y);

  /**
   * Gets the width of the image.
   *
   * @return image width
   */
  int getWidth();

  /**
   * Gets the height of the image.
   *
   * @return image height
   */
  int getHeight();
}