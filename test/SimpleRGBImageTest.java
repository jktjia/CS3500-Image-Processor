import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import model.functions.loadin.PPMLoader;
import model.SimpleRGBImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * A class to test SimpleRGBImage and makePPM, which makes a new SimpleRGBImage from a PPM file.
 * Also, this tests PPMLoader.
 */
public class SimpleRGBImageTest extends AbstractImageLoaderTest {
  @Override
  @Before
  public void initImage() {
    image = new PPMLoader().load("res/thing.ppm");
  }

  @Test
  public void testInvalidRedArrayH() {
    try {
      int[][] r = new int[2][1];
      int[][] g = new int[1][1];
      int[][] b = new int[1][1];
      new SimpleRGBImage(1, 1, r, g, b);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Red array does not match height", e.getMessage());
    }
  }

  @Test
  public void testInvalidGreenArrayH() {
    try {
      int[][] r = new int[1][1];
      int[][] g = new int[2][1];
      int[][] b = new int[1][1];
      new SimpleRGBImage(1, 1, r, g, b);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Green array does not match height", e.getMessage());
    }
  }

  @Test
  public void testInvalidBlueArrayH() {
    try {
      int[][] r = new int[1][1];
      int[][] g = new int[1][1];
      int[][] b = new int[2][1];
      new SimpleRGBImage(1, 1, r, g, b);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Blue array does not match height", e.getMessage());
    }
  }

  @Test
  public void testInvalidRedArrayW() {
    try {
      int[][] r = new int[1][2];
      int[][] g = new int[1][1];
      int[][] b = new int[1][1];
      new SimpleRGBImage(1, 1, r, g, b);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Red array does not match width", e.getMessage());
    }
  }

  @Test
  public void testInvalidGreenArrayW() {
    try {
      int[][] r = new int[1][1];
      int[][] g = new int[1][2];
      int[][] b = new int[1][1];
      new SimpleRGBImage(1, 1, r, g, b);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Green array does not match width", e.getMessage());
    }
  }

  @Test
  public void testInvalidBlueArrayW() {
    try {
      int[][] r = new int[1][1];
      int[][] g = new int[1][1];
      int[][] b = new int[1][2];
      new SimpleRGBImage(1, 1, r, g, b);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Blue array does not match width", e.getMessage());
    }
  }

  @Test
  public void testInvalidRedValueTooBig() {
    try {
      int[][] r = new int[1][1];
      int[][] g = new int[1][1];
      int[][] b = new int[1][1];
      r[0][0] = 256;
      new SimpleRGBImage(1, 1, r, g, b);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid red value at (0,0)", e.getMessage());
    }
  }

  @Test
  public void testInvalidGreenValueTooBig() {
    try {
      int[][] r = new int[1][1];
      int[][] g = new int[1][1];
      int[][] b = new int[1][1];
      g[0][0] = 256;
      new SimpleRGBImage(1, 1, r, g, b);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid green value at (0,0)", e.getMessage());
    }
  }

  @Test
  public void testInvalidBlueValueTooBig() {
    try {
      int[][] r = new int[1][1];
      int[][] g = new int[1][1];
      int[][] b = new int[1][1];
      b[0][0] = 256;
      new SimpleRGBImage(1, 1, r, g, b);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid blue value at (0,0)", e.getMessage());
    }
  }

  @Test
  public void testInvalidRedValueNegative() {
    try {
      int[][] r = new int[1][1];
      int[][] g = new int[1][1];
      int[][] b = new int[1][1];
      r[0][0] = -1;
      new SimpleRGBImage(1, 1, r, g, b);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid red value at (0,0)", e.getMessage());
    }
  }

  @Test
  public void testInvalidGreenValueNegative() {
    try {
      int[][] r = new int[1][1];
      int[][] g = new int[1][1];
      int[][] b = new int[1][1];
      g[0][0] = -1;
      new SimpleRGBImage(1, 1, r, g, b);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid green value at (0,0)", e.getMessage());
    }
  }

  @Test
  public void testInvalidBlueValueNegative() {
    try {
      int[][] r = new int[1][1];
      int[][] g = new int[1][1];
      int[][] b = new int[1][1];
      b[0][0] = -1;
      new SimpleRGBImage(1, 1, r, g, b);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid blue value at (0,0)", e.getMessage());
    }
  }

  @Test
  public void testLoadPPMFileNotFound() {
    try {
      new PPMLoader().load("res/banana.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("File res/banana.ppm not found", e.getMessage());
    }
  }

  @Test
  public void testLoadPPMInvalidPPM() {
    try {
      new PPMLoader().load("res/something.ppm");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid PPM file: plain RAW file should begin with P3",
              e.getMessage());
    }
  }

  @Test
  public void testGetWidth() {
    assertEquals(4, image.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(3, image.getHeight());
  }

  @Test
  public void testEquals() {
    assertNotEquals(new PPMLoader().load("res/red.ppm"), image);
    assertEquals(new PPMLoader().load("res/thing.ppm"), image);
  }

  @Test
  public void testRedHistogram() {
    int[] redArr = new int[]{2, 0, 1, 0,
                             0, 0, 0, 0,
                             0, 1, 2, 1,
                             0, 0, 1, 4};
    int[] redHist = image.redHistogram(16);
    for (int i = 0; i < 16; i++) {
      assertEquals(redArr[i], redHist[i]);
    }
    assertEquals(12, Arrays.stream(redHist).sum());
  }

  @Test
  public void testGreenHistogram() {
    int[] greenArr = new int[]{1, 0, 1, 0,
                               0, 1, 0, 1,
                               1, 0, 0, 1,
                               0, 0, 1, 5};
    int[] greenHist = image.greenHistogram(16);
    for (int i = 0; i < 16; i++) {
      assertEquals(greenArr[i], greenHist[i]);
    }
    assertEquals(12, Arrays.stream(greenHist).sum());
  }

  @Test
  public void testBlueHistogram() {
    int[] blueArr = new int[]{0, 0, 2, 0,
                              0, 0, 0, 0,
                              2, 0, 1, 0,
                              1, 3, 1, 2};
    int[] blueHist = image.blueHistogram(16);
    for (int i = 0; i < 16; i++) {
      assertEquals(blueArr[i], blueHist[i]);
    }
    assertEquals(12, Arrays.stream(blueHist).sum());
  }

  @Test
  public void testIntensityHistogram() {
    int[] intensityArr = new int[]{0, 0, 1, 0,
                                   0, 2, 0, 1,
                                   0, 2, 1, 0,
                                   0, 0, 3, 2};
    int[] intensityHist = image.intensityHistogram(16);
    for (int i = 0; i < 16; i++) {
      assertEquals(intensityArr[i], intensityHist[i]);
    }
    assertEquals(12, Arrays.stream(intensityHist).sum());
  }
}