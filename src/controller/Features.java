package controller;

import java.awt.Image;

/**
 * An interfaces for the properties a view can observe of an ImageController without modifying it
 * and for inputting information to the controller.
 */
public interface Features {
  /**
   * Gives an image name to the controller.
   *
   * @param imageName image name for the controller
   */
  void selectedImage(String imageName);

  /**
   * Gets a copy of the image currently being worked on. Returns null if no currentImage.
   *
   * @return copy of the image currently being worked on
   */
  Image currentImage();

  /**
   * Gets an array of the given length that represents a histogram of the red values with the given
   * number of columns.
   *
   * @param numBoxes number of columns in histogram
   * @return array of the given length that represents a histogram
   */
  int[] currentReds(int numBoxes);

  /**
   * Gets an array of the given length that represents a histogram of the green values with the
   * given number of columns.
   *
   * @param numBoxes number of columns in histogram
   * @return array of the given length that represents a histogram
   */
  int[] currentGreens(int numBoxes);

  /**
   * Gets an array of the given length that represents a histogram of the blue values with the given
   * number of columns.
   *
   * @param numBoxes number of columns in histogram
   * @return array of the given length that represents a histogram
   */
  int[] currentBlues(int numBoxes);

  /**
   * Gets an array of the given length that represents a histogram of the intensity values with the
   * given number of columns.
   *
   * @param numBoxes number of columns in histogram
   * @return array of the given length that represents a histogram
   */
  int[] currentIntensities(int numBoxes);

  /**
   * Lists the names of all the images in storage.
   *
   * @return array of all names in storage
   */
  String[] imageNames();

  /**
   * Returns an array of all the valid commands.
   */
  String[] validCommands();
}
