package model;

import java.awt.image.BufferedImage;

/**
 * An interface representing the viewable aspects of an image.
 */
public interface ViewableImage {
  /**
   * Converts an image to a BufferedImage.
   *
   * @return BufferedImage copy of image.
   */
  BufferedImage toBufferedImage();

  /**
   * Gets an array of the given length that represents a histogram of the red values with the given
   * number of columns.
   *
   * @param numBoxes number of columns in histogram
   * @return array of the given length that represents a histogram
   */
  int[] redHistogram(int numBoxes);

  /**
   * Gets an array of the given length that represents a histogram of the green values with the
   * given number of columns.
   *
   * @param numBoxes number of columns in histogram
   * @return array of the given length that represents a histogram
   */
  int[] greenHistogram(int numBoxes);

  /**
   * Gets an array of the given length that represents a histogram of the blue values with the given
   * number of columns.
   *
   * @param numBoxes number of columns in histogram
   * @return array of the given length that represents a histogram
   */
  int[] blueHistogram(int numBoxes);

  /**
   * Gets an array of the given length that represents a histogram of the intensity values with the
   * given number of columns.
   *
   * @param numBoxes number of columns in histogram
   * @return array of the given length that represents a histogram
   */
  int[] intensityHistogram(int numBoxes);
}
