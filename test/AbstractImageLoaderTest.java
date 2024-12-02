import org.junit.Before;
import org.junit.Test;

import model.StoredImage;

import static org.junit.Assert.assertEquals;

/**
 * An abstraction of the shared tests for all ImageLoaders.
 */
public abstract class AbstractImageLoaderTest {
  StoredImage image;

  @Before
  public abstract void initImage();

  @Test
  public void testGetRed() {
    assertEquals(255, image.getRed(0, 0));
    assertEquals(0, image.getRed(1, 0));
    assertEquals(224, image.getRed(2, 0));
    assertEquals(188, image.getRed(3, 0));
    assertEquals(250, image.getRed(0, 1));
    assertEquals(32, image.getRed(1, 1));
    assertEquals(255, image.getRed(2, 1));
    assertEquals(160, image.getRed(3, 1));
    assertEquals(147, image.getRed(0, 2));
    assertEquals(0, image.getRed(1, 2));
    assertEquals(165, image.getRed(2, 2));
    assertEquals(255, image.getRed(3, 2));
  }

  @Test
  public void testGetGreen() {
    assertEquals(239, image.getGreen(0, 0));
    assertEquals(255, image.getGreen(1, 0));
    assertEquals(255, image.getGreen(2, 0));
    assertEquals(143, image.getGreen(3, 0));
    assertEquals(250, image.getGreen(0, 1));
    assertEquals(178, image.getGreen(1, 1));
    assertEquals(250, image.getGreen(2, 1));
    assertEquals(82, image.getGreen(3, 1));
    assertEquals(112, image.getGreen(0, 2));
    assertEquals(0, image.getGreen(1, 2));
    assertEquals(42, image.getGreen(2, 2));
    assertEquals(255, image.getGreen(3, 2));
  }

  @Test
  public void testGetBlue() {
    assertEquals(213, image.getBlue(0, 0));
    assertEquals(255, image.getBlue(1, 0));
    assertEquals(255, image.getBlue(2, 0));
    assertEquals(143, image.getBlue(3, 0));
    assertEquals(210, image.getBlue(0, 1));
    assertEquals(170, image.getBlue(1, 1));
    assertEquals(205, image.getBlue(2, 1));
    assertEquals(45, image.getBlue(3, 1));
    assertEquals(219, image.getBlue(0, 2));
    assertEquals(128, image.getBlue(1, 2));
    assertEquals(42, image.getBlue(2, 2));
    assertEquals(224, image.getBlue(3, 2));
  }
}
