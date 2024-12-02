import org.junit.Test;

import java.io.File;
import java.io.StringReader;

import controller.ImageController;
import model.StoredImage;
import model.functions.loadin.PPMLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * An abstraction of shared test for text controllers that have the functions.
 */
public abstract class AbstractTextControllerTest {
  String quit = "Goodbye.";

  /**
   * Returns expected menu for the text controller.
   *
   * @return expected menu for the text controller
   */
  protected abstract String menu();

  @Test(expected = NullPointerException.class)
  public void testNullIn() {
    makeController(null, new StringBuilder());
  }

  @Test(expected = NullPointerException.class)
  public void testNullOut() {
    makeController(new StringReader(""), null);
  }

  @Test
  public void testBadAppendable() {
    try {
      ImageController c = makeController(new StringReader(""),
              new FailingAppendable());
      c.run();
      fail();
    } catch (IllegalStateException e) {
      assertEquals("Could not append to appendable", e.getMessage());
    }
  }

  @Test
  public void testStartAndQuit() {
    Readable input = new StringReader("q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();
    assertEquals(menu() + quit, dest.toString());
  }

  @Test
  public void testInvalidCommand() {
    Readable input = new StringReader("do-laundry q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();
    assertEquals(menu() + "Unknown command: do-laundry\n" + quit, dest.toString());
  }


  @Test
  public void testInvalidCommand2() {
    Readable input = new StringReader("load res/thing.ppm thing brighten cat q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();
    assertEquals(menu() + "Invalid input for command \"brighten\"\nPlease try again\n"
            + quit, dest.toString());
  }

  @Test
  public void testInvalidCommand3() {
    Readable input = new StringReader("load res/thing.ppm thing brighten 10");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();
    assertEquals(menu() + "Unexpected end of input\nNow ending program\n"
            + quit, dest.toString());
  }

  @Test
  public void testInputEndsEarly() {
    Readable input = new StringReader("load res/thing.ppm");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();
    assertEquals(menu() + "Unexpected end of input\nNow ending program\n"
            + quit, dest.toString());
  }

  @Test
  public void testImageNotFound() {
    Readable input = new StringReader("red-component thing red q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();
    assertEquals(menu() + "Name \"thing\" does not correspond to any image in storage\n"
            + quit, dest.toString());
  }

  @Test
  public void testFileNotFound() {
    Readable input = new StringReader("load res/banana.ppm banana q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();
    assertEquals(menu() + "File res/banana.ppm not found\n"
            + quit, dest.toString());
  }

  @Test
  public void testInvalidPPM() {
    Readable input = new StringReader("load res/something.ppm something q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();
    assertEquals(menu() + "Invalid PPM file: plain RAW file should begin with P3\n"
            + quit, dest.toString());
  }

  @Test
  public void testMenu() {
    Readable input = new StringReader("menu q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();
    assertEquals(menu() + menu() + quit, dest.toString());
  }

  @Test
  public void testRedComponent() {
    Readable input = new StringReader("load res/thing.ppm thing red-component thing " +
            "red save res/red2.ppm red q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage red1 = new PPMLoader().load("res/red.ppm");
    StoredImage red2 = new PPMLoader().load("res/red2.ppm");
    assertEquals(red1, red2);

    assertTrue(new File("res/red2.ppm").delete());
  }

  @Test
  public void testBlueComponent() {
    Readable input = new StringReader("load res/thing.ppm thing blue-component thing " +
            "blue save res/blue2.ppm blue q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage blue1 = new PPMLoader().load("res/blue.ppm");
    StoredImage blue2 = new PPMLoader().load("res/blue2.ppm");
    assertEquals(blue1, blue2);

    assertTrue(new File("res/blue2.ppm").delete());
  }

  @Test
  public void testGreenComponent() {
    Readable input = new StringReader("load res/thing.ppm thing green-component thing " +
            "green save res/green2.ppm green q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage green1 = new PPMLoader().load("res/green.ppm");
    StoredImage green2 = new PPMLoader().load("res/green2.ppm");
    assertEquals(green1, green2);

    assertTrue(new File("res/green2.ppm").delete());

  }

  @Test
  public void testValueComponent() {
    Readable input = new StringReader("load res/thing.ppm thing value-component thing " +
            "value save res/value2.ppm value q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage value = new PPMLoader().load("res/value.ppm");
    StoredImage value2 = new PPMLoader().load("res/value2.ppm");
    assertEquals(value, value2);

    assertTrue(new File("res/value2.ppm").delete());
  }

  @Test
  public void testLumaComponent() {
    Readable input = new StringReader("load res/thing.ppm thing luma-component thing " +
            "luma save res/luma2.ppm luma q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage luma = new PPMLoader().load("res/luma.ppm");
    StoredImage luma2 = new PPMLoader().load("res/luma2.ppm");
    assertEquals(luma, luma2);

    assertTrue(new File("res/luma2.ppm").delete());
  }

  @Test
  public void testIntensity() {
    Readable input = new StringReader("load res/thing.ppm thing intensity-component thing " +
            "intensity save res/intensity2.ppm intensity q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage intensity = new PPMLoader().load("res/intensity.ppm");
    StoredImage intensity2 = new PPMLoader().load("res/intensity2.ppm");
    assertEquals(intensity, intensity2);

    assertTrue(new File("res/intensity2.ppm").delete());
  }

  @Test
  public void testHorizontal() {
    Readable input = new StringReader("load res/thing.ppm thing horizontal-flip thing " +
            "horizontal-flip save res/horizontal2.ppm horizontal-flip q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage horizontal = new PPMLoader().load("res/horizontal.ppm");
    StoredImage horizontal2 = new PPMLoader().load("res/horizontal2.ppm");
    assertEquals(horizontal, horizontal2);

    assertTrue(new File("res/horizontal2.ppm").delete());
  }

  @Test
  public void testVertical() {
    Readable input = new StringReader("load res/thing.ppm thing vertical-flip thing " +
            "vertical-flip save res/vertical2.ppm vertical-flip q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage vertical = new PPMLoader().load("res/vertical.ppm");
    StoredImage vertical2 = new PPMLoader().load("res/vertical2.ppm");
    assertEquals(vertical, vertical2);

    assertTrue(new File("res/vertical2.ppm").delete());
  }

  @Test
  public void testBrighten() {
    Readable input = new StringReader("load res/thing.ppm thing brighten 10 thing " +
            "brightened save res/brightened2.ppm brightened q");
    StringBuilder dest = new StringBuilder();
    ImageController test = makeController(input, dest);
    test.run();

    StoredImage brighten1 = new PPMLoader().load("res/brighter.ppm");
    StoredImage brighten2 = new PPMLoader().load("res/brightened2.ppm");
    assertEquals(brighten1, brighten2);

    assertTrue(new File("res/brightened2.ppm").delete());
  }

  /**
   * Constructs a new text controller given the input and output.
   *
   * @param input input to controller
   * @param output output from controller
   * @return new controller
   */
  protected abstract ImageController makeController(Readable input, Appendable output);
}
