import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import controller.SaveUtils;
import model.functions.loadin.ImageIOCompatibleLoader;
import model.functions.loadin.PPMLoader;
import model.StoredImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A class to test saving images.
 */
public class SaveTests {
  StoredImage image = new PPMLoader().load("res/thing.ppm");

  @Test
  public void testSave() {
    Appendable sb = new StringBuilder();
    SaveUtils.savePPM(sb, image);
    assertEquals("P3\n4 3\n255\n255\n239\n213\n0\n255\n255\n224\n255\n255\n188\n143\n143\n"
            + "250\n250\n210\n32\n178\n170\n255\n250\n205\n160\n82\n45\n147\n112\n219\n0\n0\n128\n"
            + "165\n42\n42\n255\n255\n224\n", sb.toString());
  }

  @Test
  public void testAppendException() {
    try {
      Appendable a = new FailingAppendable();
      SaveUtils.savePPM(a, image);
      fail();
    } catch (IllegalStateException e) {
      assertEquals("Could not write to appendable", e.getMessage());
    }
  }

  @Test
  public void testSaveAndReloadSame() {
    try {
      File file = Files.createFile(Path.of("res/thingNew.png")).toFile();
      SaveUtils.saveFileImageIOCompatible(file, image, "png");
      StoredImage image2 = new ImageIOCompatibleLoader().load("res/thingNew.png");
      assertEquals(image, image2);

      assertTrue(file.delete());
    } catch (IOException e) {
      fail();
    }
  }
}