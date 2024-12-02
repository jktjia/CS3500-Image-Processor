package model.functions.loadin;

import model.StoredImage;

/**
 * An interface for loading files to images in this program.
 */
public interface ImageLoader {
  /**
   * Loads the file at the given location to a new StoredImage.
   *
   * @param filename name and location of the file
   * @return image created from file
   * @throws IllegalArgumentException if the file cannot be found or the file is not valid
   */
  StoredImage load(String filename) throws IllegalArgumentException;
}
