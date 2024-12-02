package model;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that can take in and store images.
 */
public class MappedStorage implements ImageStorage {
  private final Map<String, StoredImage> map;

  /**
   * Constructs a new MappedStorage.
   */
  public MappedStorage() {
    this.map = new HashMap<>();
  }

  @Override
  public void add(String imageName, StoredImage image) {
    this.map.put(imageName, image);
  }

  @Override
  public StoredImage getImage(String imageName) throws IllegalArgumentException {
    if (this.map.containsKey(imageName)) {
      return this.map.get(imageName);
    } else {
      throw new IllegalArgumentException("Name \"" + imageName + "\" does not correspond to any "
              + "image in storage");
    }
  }

  @Override
  public String[] imagesByName() {
    return this.map.keySet().toArray(new String[0]);
  }

  @Override
  public Image viewImage(String imageName) {
    return this.getImage(imageName).toBufferedImage();
  }
}
