package model;

/**
 * Represents a group of stored images that can have images loaded in and retrieved.
 */
public interface ImageStorage extends ViewableStorage {
  /**
   * Stores the image under the given name.
   *
   * @param imageName name of the image within storage
   * @param image     image to store
   */
  void add(String imageName, StoredImage image);

  /**
   * Retrieves the image from storage.
   *
   * @param imageName name of the image being retrieved
   * @return the image
   * @throws IllegalArgumentException if the name does not correspond to any image in storage
   */
  StoredImage getImage(String imageName) throws IllegalArgumentException;
}
