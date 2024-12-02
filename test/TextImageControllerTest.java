import org.junit.Test;

import java.io.File;
import java.io.StringReader;

import controller.ImageController;
import controller.TextImageController;
import model.StoredImage;
import model.functions.loadin.ImageIOCompatibleLoader;
import model.functions.loadin.PPMLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A class to test TextImageController.
 */
public class TextImageControllerTest extends AbstractTextControllerTest {
  @Override
  protected String menu() {
    return "The valid commands for this program are:\nmenu (shows all valid commands for this "
            + "program)\nq or quit (quits the program)\nload image-path image-name (loads image "
            + "from specified path)\nsave image-path image-name (saves image with given name to "
            + "specified path)\nred-component image-name dest-image-name (creates greyscale image "
            + "with red component of image with given name)\nblue-component image-name "
            + "dest-image-name (creates greyscale image with blue component of image with given "
            + "name)\ngreen-component image-name dest-image-name (creates greyscale image with "
            + "green component of image with given name)\nvalue-component image-name "
            + "dest-image-name (creates greyscale image with value component of image with given "
            + "name)\nluma-component image-name dest-image-name (creates greyscale image with luma "
            + "component of image with given name)\nintensity-component image-name dest-image-name"
            + " (creates greyscale image with intensity-component of image with given name)\n"
            + "horizontal-flip image-name dest-image-name (flips an image horizontally to "
            + "create a new image)\nvertical-flip image-name dest-image-name (flips an image "
            + "vertically to create a new image)\nbrighten increment image-name dest-image-name "
            + "(brightens image by given increment to create a new image)\nsharpen image-name "
            + "dest-image-name (sharpens image)\nblur image-name dest-image-name (blurs image)\n"
            + "grayscale image-name dest-image-name (creates a grayscale image based on luma"
            + " of image with given name)\nsepia image-name dest-image-name (creates a sepia "
            + "version of image with given name)\n";
  }

  @Test
  public void testBlur() {
    Readable input = new StringReader("load res/thing.png thing blur thing " +
            "blurred save res/blurred.ppm blurred q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage blur = new ImageIOCompatibleLoader().load("res/blurred.png");
    StoredImage blur2 = new PPMLoader().load("res/blurred.ppm");
    assertEquals(blur, blur2);

    assertTrue(new File("res/blurred.ppm").delete());
  }

  @Test
  public void testSharpen() {
    Readable input = new StringReader("load res/thing.png thing sharpen thing " +
            "sharp save res/sharpened.ppm sharp q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage sharp = new ImageIOCompatibleLoader().load("res/sharpened.png");
    StoredImage sharp2 = new PPMLoader().load("res/sharpened.ppm");
    assertEquals(sharp, sharp2);

    assertTrue(new File("res/sharpened.ppm").delete());
  }

  @Test
  public void testSepiaAndLoadPNGSaveBMP() {
    Readable input = new StringReader("load res/thing.png thing sepia thing " +
            "sepia save res/sepia.bmp sepia q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage sepia = new ImageIOCompatibleLoader().load("res/sepia.png");
    StoredImage sepia2 = new ImageIOCompatibleLoader().load("res/sepia.bmp");
    assertEquals(sepia, sepia2);

    assertTrue(new File("res/sepia.bmp").delete());
  }

  @Test
  public void testGrayScaleAndLoadBMPSavePNG() {
    Readable input = new StringReader("load res/thing.png thing grayscale thing " +
            "gs save res/gs.png gs q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage gs = new ImageIOCompatibleLoader().load("res/grayscale.png");
    StoredImage gs2 = new ImageIOCompatibleLoader().load("res/gs.png");
    assertEquals(gs, gs2);

    assertTrue(new File("res/gs.png").delete());
  }

  @Override
  protected ImageController makeController(Readable input, Appendable output) {
    return new TextImageController(input, output);
  }
}
