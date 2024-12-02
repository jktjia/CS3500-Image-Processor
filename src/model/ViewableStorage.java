package model;

import java.awt.Image;

/**
 * Represents the viewable properties of the storage (i.e. the things you can get without modifying
 * the storage).
 */
public interface ViewableStorage {
  /**
   * Lists the names of the images in storage.
   *
   * @return array of the names of images in storage.
   */
  String[] imagesByName();

  /**
   * Gets a copy of the image under the given name from storage.
   *
   * @return image from storage
   */
  Image viewImage(String imageName);
}
