import org.junit.Test;

import model.functions.loadin.PPMLoader;
import model.ImageStorage;
import model.MappedStorage;
import model.StoredImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A class to test MappedStorage.
 */
public class MappedStorageTest {
  @Test
  public void testImageStorage() {
    ImageStorage storage = new MappedStorage();
    storage.add("thing.", new PPMLoader().load("res/thing.ppm"));
    StoredImage stored = storage.getImage("thing.");
    assertEquals(new PPMLoader().load("res/thing.ppm"), stored);

    assertEquals(1, storage.imagesByName().length);
    assertEquals("thing.", storage.imagesByName()[0]);
  }

  @Test
  public void testImageNotInStorage() {
    try {
      ImageStorage storage = new MappedStorage();
      storage.getImage("banana");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Name \"banana\" does not correspond to any image in storage",
              e.getMessage());
    }
  }
}
