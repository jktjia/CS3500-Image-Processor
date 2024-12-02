import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.functions.BlueComponent;
import model.functions.Blur;
import model.functions.Brighten;
import model.functions.Downscale;
import model.functions.FlipHorizontal;
import model.functions.FlipVertical;
import model.functions.Grayscale;
import model.functions.GreenComponent;
import model.functions.ImageFunction;
import model.functions.IntensityComponent;
import model.functions.Load;
import model.functions.LumaComponent;
import model.functions.Mosaic;
import model.functions.RedComponent;
import model.functions.Sepia;
import model.functions.Sharpen;
import model.functions.ValueComponent;
import model.functions.loadin.PPMLoader;
import model.ImageStorage;
import model.MappedStorage;
import model.StoredImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A class to test all the image functions in the functions package.
 */
public class ImageFunctionTest {
  ImageStorage images;
  StoredImage thing;

  @Before
  public void addThingToImages() {
    images = new MappedStorage();
    thing = ImageUtils.randomImage(4, 3);
    images.add("thing", thing);
  }

  @Test
  public void testRedComponent() {
    ImageFunction f = new RedComponent("thing", "red");
    f.run(images);
    StoredImage image = images.getImage("red");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(thing.getRed(j, i), image.getRed(j, i));
        assertEquals(thing.getRed(j, i), image.getGreen(j, i));
        assertEquals(thing.getRed(j, i), image.getBlue(j, i));
      }
    }
  }

  @Test
  public void testGreenComponent() {
    ImageFunction f = new GreenComponent("thing", "green");
    f.run(images);
    StoredImage image = images.getImage("green");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(thing.getGreen(j, i), image.getRed(j, i));
        assertEquals(thing.getGreen(j, i), image.getGreen(j, i));
        assertEquals(thing.getGreen(j, i), image.getBlue(j, i));
      }
    }
  }

  @Test
  public void testBlueComponent() {
    ImageFunction f = new BlueComponent("thing", "blue");
    f.run(images);
    StoredImage image = images.getImage("blue");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(thing.getBlue(j, i), image.getRed(j, i));
        assertEquals(thing.getBlue(j, i), image.getGreen(j, i));
        assertEquals(thing.getBlue(j, i), image.getBlue(j, i));
      }
    }
  }

  @Test
  public void testValueComponent() {
    thing = new PPMLoader().load("res/thing.ppm");
    images.add("thing", thing);
    ImageFunction f = new ValueComponent("thing", "value");
    f.run(images);
    StoredImage image = images.getImage("value");

    assertEquals(255, image.getRed(0, 0));
    assertEquals(255, image.getRed(1, 0));
    assertEquals(255, image.getRed(2, 0));
    assertEquals(188, image.getRed(3, 0));
    assertEquals(250, image.getRed(0, 1));
    assertEquals(178, image.getRed(1, 1));
    assertEquals(255, image.getRed(2, 1));
    assertEquals(160, image.getRed(3, 1));
    assertEquals(219, image.getRed(0, 2));
    assertEquals(128, image.getRed(1, 2));
    assertEquals(165, image.getRed(2, 2));
    assertEquals(255, image.getRed(3, 2));

    assertEquals(255, image.getGreen(0, 0));
    assertEquals(255, image.getGreen(1, 0));
    assertEquals(255, image.getGreen(2, 0));
    assertEquals(188, image.getGreen(3, 0));
    assertEquals(250, image.getGreen(0, 1));
    assertEquals(178, image.getGreen(1, 1));
    assertEquals(255, image.getGreen(2, 1));
    assertEquals(160, image.getGreen(3, 1));
    assertEquals(219, image.getGreen(0, 2));
    assertEquals(128, image.getGreen(1, 2));
    assertEquals(165, image.getGreen(2, 2));
    assertEquals(255, image.getGreen(3, 2));

    assertEquals(255, image.getBlue(0, 0));
    assertEquals(255, image.getBlue(1, 0));
    assertEquals(255, image.getBlue(2, 0));
    assertEquals(188, image.getBlue(3, 0));
    assertEquals(250, image.getBlue(0, 1));
    assertEquals(178, image.getBlue(1, 1));
    assertEquals(255, image.getBlue(2, 1));
    assertEquals(160, image.getBlue(3, 1));
    assertEquals(219, image.getBlue(0, 2));
    assertEquals(128, image.getBlue(1, 2));
    assertEquals(165, image.getBlue(2, 2));
    assertEquals(255, image.getBlue(3, 2));
  }

  @Test
  public void testIntensityComponent() {
    thing = new PPMLoader().load("res/thing.ppm");
    images.add("thing", thing);
    ImageFunction f = new IntensityComponent("thing", "intensity");
    f.run(images);
    StoredImage image = images.getImage("intensity");

    assertEquals(235, image.getRed(0, 0));
    assertEquals(170, image.getRed(1, 0));
    assertEquals(244, image.getRed(2, 0));
    assertEquals(158, image.getRed(3, 0));
    assertEquals(236, image.getRed(0, 1));
    assertEquals(126, image.getRed(1, 1));
    assertEquals(236, image.getRed(2, 1));
    assertEquals(95, image.getRed(3, 1));
    assertEquals(159, image.getRed(0, 2));
    assertEquals(42, image.getRed(1, 2));
    assertEquals(83, image.getRed(2, 2));
    assertEquals(244, image.getRed(3, 2));

    assertEquals(235, image.getGreen(0, 0));
    assertEquals(170, image.getGreen(1, 0));
    assertEquals(244, image.getGreen(2, 0));
    assertEquals(158, image.getGreen(3, 0));
    assertEquals(236, image.getGreen(0, 1));
    assertEquals(126, image.getGreen(1, 1));
    assertEquals(236, image.getGreen(2, 1));
    assertEquals(95, image.getGreen(3, 1));
    assertEquals(159, image.getGreen(0, 2));
    assertEquals(42, image.getGreen(1, 2));
    assertEquals(83, image.getGreen(2, 2));
    assertEquals(244, image.getGreen(3, 2));

    assertEquals(235, image.getBlue(0, 0));
    assertEquals(170, image.getBlue(1, 0));
    assertEquals(244, image.getBlue(2, 0));
    assertEquals(158, image.getBlue(3, 0));
    assertEquals(236, image.getBlue(0, 1));
    assertEquals(126, image.getBlue(1, 1));
    assertEquals(236, image.getBlue(2, 1));
    assertEquals(95, image.getBlue(3, 1));
    assertEquals(159, image.getBlue(0, 2));
    assertEquals(42, image.getBlue(1, 2));
    assertEquals(83, image.getBlue(2, 2));
    assertEquals(244, image.getBlue(3, 2));
  }

  @Test
  public void testLumaComponent() {
    thing = new PPMLoader().load("res/thing.ppm");
    images.add("thing", thing);
    ImageFunction f = new LumaComponent("thing", "luma");
    f.run(images);
    StoredImage image = images.getImage("luma");

    assertEquals(240, image.getRed(0, 0));
    assertEquals(200, image.getRed(1, 0));
    assertEquals(248, image.getRed(2, 0));
    assertEquals(152, image.getRed(3, 0));
    assertEquals(247, image.getRed(0, 1));
    assertEquals(146, image.getRed(1, 1));
    assertEquals(247, image.getRed(2, 1));
    assertEquals(95, image.getRed(3, 1));
    assertEquals(127, image.getRed(0, 2));
    assertEquals(9, image.getRed(1, 2));
    assertEquals(68, image.getRed(2, 2));
    assertEquals(252, image.getRed(3, 2));

    assertEquals(240, image.getGreen(0, 0));
    assertEquals(200, image.getGreen(1, 0));
    assertEquals(248, image.getGreen(2, 0));
    assertEquals(152, image.getGreen(3, 0));
    assertEquals(247, image.getGreen(0, 1));
    assertEquals(146, image.getGreen(1, 1));
    assertEquals(247, image.getGreen(2, 1));
    assertEquals(95, image.getGreen(3, 1));
    assertEquals(127, image.getGreen(0, 2));
    assertEquals(9, image.getGreen(1, 2));
    assertEquals(68, image.getGreen(2, 2));
    assertEquals(252, image.getGreen(3, 2));

    assertEquals(240, image.getBlue(0, 0));
    assertEquals(200, image.getBlue(1, 0));
    assertEquals(248, image.getBlue(2, 0));
    assertEquals(152, image.getBlue(3, 0));
    assertEquals(247, image.getBlue(0, 1));
    assertEquals(146, image.getBlue(1, 1));
    assertEquals(247, image.getBlue(2, 1));
    assertEquals(95, image.getBlue(3, 1));
    assertEquals(127, image.getBlue(0, 2));
    assertEquals(9, image.getBlue(1, 2));
    assertEquals(68, image.getBlue(2, 2));
    assertEquals(252, image.getBlue(3, 2));
  }

  @Test
  public void testBlur() {
    thing = new PPMLoader().load("res/thing.ppm");
    images.add("thing", thing);
    ImageFunction f = new Blur("thing", "blur");
    f.run(images);
    StoredImage image = images.getImage("blur");

    assertEquals(97, image.getRed(0, 0));
    assertEquals(95, image.getRed(1, 0));
    assertEquals(89, image.getRed(2, 0));
    assertEquals(43, image.getRed(3, 0));
    assertEquals(98, image.getRed(0, 1));
    assertEquals(101, image.getRed(1, 1));
    assertEquals(95, image.getRed(2, 1));
    assertEquals(45, image.getRed(3, 1));
    assertEquals(33, image.getRed(0, 2));
    assertEquals(35, image.getRed(1, 2));
    assertEquals(33, image.getRed(2, 2));
    assertEquals(15, image.getRed(3, 2));

    assertEquals(134, image.getGreen(0, 0));
    assertEquals(179, image.getGreen(1, 0));
    assertEquals(138, image.getGreen(2, 0));
    assertEquals(47, image.getGreen(3, 0));
    assertEquals(130, image.getGreen(0, 1));
    assertEquals(169, image.getGreen(1, 1));
    assertEquals(132, image.getGreen(2, 1));
    assertEquals(47, image.getGreen(3, 1));
    assertEquals(42, image.getGreen(0, 2));
    assertEquals(53, image.getGreen(1, 2));
    assertEquals(42, image.getGreen(2, 2));
    assertEquals(15, image.getGreen(3, 2));

    assertEquals(122, image.getBlue(0, 0));
    assertEquals(169, image.getBlue(1, 0));
    assertEquals(131, image.getBlue(2, 0));
    assertEquals(44, image.getBlue(3, 0));
    assertEquals(116, image.getBlue(0, 1));
    assertEquals(155, image.getBlue(1, 1));
    assertEquals(120, image.getBlue(2, 1));
    assertEquals(41, image.getBlue(3, 1));
    assertEquals(36, image.getBlue(0, 2));
    assertEquals(47, image.getBlue(1, 2));
    assertEquals(36, image.getBlue(2, 2));
    assertEquals(12, image.getBlue(3, 2));
  }

  @Test
  public void testSharpen() {
    thing = new PPMLoader().load("res/thing.ppm");
    images.add("thing", thing);
    ImageFunction f = new Sharpen("thing", "sharpen");
    f.run(images);
    StoredImage image = images.getImage("sharpen");

    assertEquals(255, image.getRed(0, 0));
    assertEquals(254, image.getRed(1, 0));
    assertEquals(232, image.getRed(2, 0));
    assertEquals(115, image.getRed(3, 0));
    assertEquals(255, image.getRed(0, 1));
    assertEquals(255, image.getRed(1, 1));
    assertEquals(255, image.getRed(2, 1));
    assertEquals(115, image.getRed(3, 1));
    assertEquals(0, image.getRed(0, 2));
    assertEquals(74, image.getRed(1, 2));
    assertEquals(0, image.getRed(2, 2));
    assertEquals(31, image.getRed(3, 2));

    assertEquals(255, image.getGreen(0, 0));
    assertEquals(255, image.getGreen(1, 0));
    assertEquals(255, image.getGreen(2, 0));
    assertEquals(72, image.getGreen(3, 0));
    assertEquals(255, image.getGreen(0, 1));
    assertEquals(255, image.getGreen(1, 1));
    assertEquals(255, image.getGreen(2, 1));
    assertEquals(72, image.getGreen(3, 1));
    assertEquals(0, image.getGreen(0, 2));
    assertEquals(75, image.getGreen(1, 2));
    assertEquals(0, image.getGreen(2, 2));
    assertEquals(0, image.getGreen(3, 2));

    assertEquals(255, image.getBlue(0, 0));
    assertEquals(255, image.getBlue(1, 0));
    assertEquals(255, image.getBlue(2, 0));
    assertEquals(61, image.getBlue(3, 0));
    assertEquals(255, image.getBlue(0, 1));
    assertEquals(255, image.getBlue(1, 1));
    assertEquals(255, image.getBlue(2, 1));
    assertEquals(61, image.getBlue(3, 1));
    assertEquals(0, image.getBlue(0, 2));
    assertEquals(55, image.getBlue(1, 2));
    assertEquals(0, image.getBlue(2, 2));
    assertEquals(0, image.getBlue(3, 2));
  }

  @Test
  public void testSepia() {
    thing = new PPMLoader().load("res/thing.ppm");
    images.add("thing", thing);
    ImageFunction f = new Sepia("thing", "sepia");
    f.run(images);
    StoredImage image = images.getImage("sepia");

    assertEquals(255, image.getRed(0, 0));
    assertEquals(244, image.getRed(1, 0));
    assertEquals(255, image.getRed(2, 0));
    assertEquals(210, image.getRed(3, 0));
    assertEquals(255, image.getRed(0, 1));
    assertEquals(181, image.getRed(1, 1));
    assertEquals(255, image.getRed(2, 1));
    assertEquals(134, image.getRed(3, 1));
    assertEquals(185, image.getRed(0, 2));
    assertEquals(24, image.getRed(1, 2));
    assertEquals(105, image.getRed(2, 2));
    assertEquals(255, image.getRed(3, 2));

    assertEquals(255, image.getGreen(0, 0));
    assertEquals(217, image.getGreen(1, 0));
    assertEquals(255, image.getGreen(2, 0));
    assertEquals(187, image.getGreen(3, 0));
    assertEquals(255, image.getGreen(0, 1));
    assertEquals(161, image.getGreen(1, 1));
    assertEquals(255, image.getGreen(2, 1));
    assertEquals(119, image.getGreen(3, 1));
    assertEquals(164, image.getGreen(0, 2));
    assertEquals(21, image.getGreen(1, 2));
    assertEquals(93, image.getGreen(2, 2));
    assertEquals(255, image.getGreen(3, 2));

    assertEquals(224, image.getBlue(0, 0));
    assertEquals(169, image.getBlue(1, 0));
    assertEquals(230, image.getBlue(2, 0));
    assertEquals(146, image.getBlue(3, 0));
    assertEquals(229, image.getBlue(0, 1));
    assertEquals(126, image.getBlue(1, 1));
    assertEquals(229, image.getBlue(2, 1));
    assertEquals(93, image.getBlue(3, 1));
    assertEquals(128, image.getBlue(0, 2));
    assertEquals(16, image.getBlue(1, 2));
    assertEquals(72, image.getBlue(2, 2));
    assertEquals(234, image.getBlue(3, 2));
  }

  @Test
  public void testTransformGrayscale() {
    thing = new PPMLoader().load("res/thing.ppm");
    images.add("thing", thing);
    ImageFunction f = new Grayscale("thing", "grayscale");
    f.run(images);
    StoredImage image = images.getImage("grayscale");

    assertEquals(240, image.getRed(0, 0));
    assertEquals(200, image.getRed(1, 0));
    assertEquals(248, image.getRed(2, 0));
    assertEquals(152, image.getRed(3, 0));
    assertEquals(247, image.getRed(0, 1));
    assertEquals(146, image.getRed(1, 1));
    assertEquals(247, image.getRed(2, 1));
    assertEquals(95, image.getRed(3, 1));
    assertEquals(127, image.getRed(0, 2));
    assertEquals(9, image.getRed(1, 2));
    assertEquals(68, image.getRed(2, 2));
    assertEquals(252, image.getRed(3, 2));

    assertEquals(240, image.getGreen(0, 0));
    assertEquals(200, image.getGreen(1, 0));
    assertEquals(248, image.getGreen(2, 0));
    assertEquals(152, image.getGreen(3, 0));
    assertEquals(247, image.getGreen(0, 1));
    assertEquals(146, image.getGreen(1, 1));
    assertEquals(247, image.getGreen(2, 1));
    assertEquals(95, image.getGreen(3, 1));
    assertEquals(127, image.getGreen(0, 2));
    assertEquals(9, image.getGreen(1, 2));
    assertEquals(68, image.getGreen(2, 2));
    assertEquals(252, image.getGreen(3, 2));

    assertEquals(240, image.getBlue(0, 0));
    assertEquals(200, image.getBlue(1, 0));
    assertEquals(248, image.getBlue(2, 0));
    assertEquals(152, image.getBlue(3, 0));
    assertEquals(247, image.getBlue(0, 1));
    assertEquals(146, image.getBlue(1, 1));
    assertEquals(247, image.getBlue(2, 1));
    assertEquals(95, image.getBlue(3, 1));
    assertEquals(127, image.getBlue(0, 2));
    assertEquals(9, image.getBlue(1, 2));
    assertEquals(68, image.getBlue(2, 2));
    assertEquals(252, image.getBlue(3, 2));
  }

  @Test
  public void testLoadImage() {
    this.images = new MappedStorage();
    ImageFunction f = new Load("res/thing.ppm", "thing", new PPMLoader());
    f.run(images);
    StoredImage thing = images.getImage("thing");
    assertEquals(new PPMLoader().load("res/thing.ppm"), thing);
  }

  @Test
  public void testFlipHorizontal() {
    ImageFunction f = new FlipHorizontal("thing", "horizontal");
    f.run(images);
    StoredImage image = images.getImage("horizontal");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(thing.getRed(3 - j, i), image.getRed(j, i));
        assertEquals(thing.getGreen(3 - j, i), image.getGreen(j, i));
        assertEquals(thing.getBlue(3 - j, i), image.getBlue(j, i));
      }
    }
  }

  @Test
  public void testFlipVertical() {
    ImageFunction f = new FlipVertical("thing", "vertical");
    f.run(images);
    StoredImage image = images.getImage("vertical");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(thing.getRed(j, 2 - i), image.getRed(j, i));
        assertEquals(thing.getGreen(j, 2 - i), image.getGreen(j, i));
        assertEquals(thing.getBlue(j, 2 - i), image.getBlue(j, i));
      }
    }
  }

  @Test
  public void testBrightenUp() {
    ImageFunction f = new Brighten(10, "thing", "brighten");
    f.run(images);
    StoredImage image = images.getImage("brighten");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(Math.min(255, thing.getRed(j, i) + 10), image.getRed(j, i));
        assertEquals(Math.min(255, thing.getGreen(j, i) + 10), image.getGreen(j, i));
        assertEquals(Math.min(255, thing.getBlue(j, i) + 10), image.getBlue(j, i));
      }
    }
  }

  @Test
  public void testBrightenDown() {
    ImageFunction f = new Brighten(-10, "thing", "brighten");
    f.run(images);
    StoredImage image = images.getImage("brighten");

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(Math.max(0, thing.getRed(j, i) - 10), image.getRed(j, i));
        assertEquals(Math.max(0, thing.getGreen(j, i) - 10), image.getGreen(j, i));
        assertEquals(Math.max(0, thing.getBlue(j, i) - 10), image.getBlue(j, i));
      }
    }
  }

  @Test
  public void testMosaicError1() {
    try {
      int nonPositive = -Math.abs(new Random().nextInt());
      new Mosaic(nonPositive, "thing", "mosaic");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Non-positive number of seeds", e.getMessage());
    }
  }

  @Test
  public void testMosaicError2() {
    try {
      ImageFunction mosaic = new Mosaic(500, "thing", "mosaic");
      mosaic.run(images);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Number of seeds is greater than number of pixels", e.getMessage());
    }
  }

  @Test
  public void testMosaic() {
    int w = 20;
    int h = 20;
    thing = ImageUtils.randomImage(w, h);
    images.add("thing", thing);
    ImageFunction mosaic = new Mosaic(40, "thing", "mosaic");
    mosaic.run(images);
    StoredImage image = images.getImage("mosaic");

    List<Integer>[] clusters = new List[40];
    Map<Color, Integer> colorToCluster = new HashMap<>();
    int clustersSoFar = 0;
    for (int i = 0; i < h * w; i++) {
      int x = i % w;
      int y = i / w;
      Color color = new Color(image.getRed(x, y), image.getGreen(x, y), image.getBlue(x, y));
      if (colorToCluster.containsKey(color)) {
        int idx = colorToCluster.get(color);
        clusters[idx].add(i);
      } else {
        colorToCluster.put(color, clustersSoFar);
        clusters[clustersSoFar] = new ArrayList<>();
        clusters[clustersSoFar].add(i);
        clustersSoFar++;
      }
    }

    for (List<Integer> ls : clusters) {
      for (int pix : ls) {
        int x = pix % w;
        int y = pix / w;
        assertTrue(ls.stream()
                .anyMatch(p -> p != pix && Math.abs((p % w) - x) <= 1
                        && Math.abs((p / w) - y) <= 1));
      }
      int r = ls.stream()
              .mapToInt(p -> thing.getRed(p % w, p / w))
              .sum() / ls.size();
      int g = ls.stream()
              .mapToInt(p -> thing.getGreen(p % w, p / w))
              .sum() / ls.size();
      int b = ls.stream()
              .mapToInt(p -> thing.getBlue(p % w, p / w))
              .sum() / ls.size();
      assertEquals(r, image.getRed(ls.get(0) % w, ls.get(0) / w));
      assertEquals(g, image.getGreen(ls.get(0) % w, ls.get(0) / w));
      assertEquals(b, image.getBlue(ls.get(0) % w, ls.get(0) / w));
    }
  }

  @Test
  public void testDownscaleError1Width() {
    try {
      int nonPositive = -Math.abs(new Random().nextInt());
      int positive = Math.abs(new Random().nextInt());
      new Downscale(nonPositive, positive, "thing", "mosaic");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Width and height must be positive", e.getMessage());
    }
  }

  @Test
  public void testDownscaleError1Height() {
    try {
      int nonPositive = -Math.abs(new Random().nextInt());
      int positive = Math.abs(new Random().nextInt());
      new Downscale(positive, nonPositive, "thing", "mosaic");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Width and height must be positive", e.getMessage());
    }
  }

  @Test
  public void testDownscaleError2Width() {
    try {
      int width = Math.abs(new Random().nextInt()) + 5;
      int height = Math.abs(new Random().nextInt(3)) + 1;
      ImageFunction downscale = new Downscale(width, height, "thing", "mosaic");
      downscale.run(images);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Downscaled image must be smaller than original image", e.getMessage());
    }
  }

  @Test
  public void testDownscaleError2Height() {
    try {
      int width = Math.abs(new Random().nextInt(3)) + 1;
      int height = Math.abs(new Random().nextInt()) + 5;
      ImageFunction downscale = new Downscale(width, height, "thing", "mosaic");
      downscale.run(images);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Downscaled image must be smaller than original image", e.getMessage());
    }
  }

  @Test
  public void testDownscale() {
    thing = new PPMLoader().load("res/thing.ppm");
    images.add("thing", thing);
    ImageFunction f = new Downscale(3, 2, "thing", "downscale");
    f.run(images);
    StoredImage image = images.getImage("downscale");

    assertEquals(255, image.getRed(0, 0));
    assertEquals(74, image.getRed(1, 0));
    assertEquals(200, image.getRed(2, 0));
    assertEquals(198, image.getRed(0, 1));
    assertEquals(80, image.getRed(1, 1));
    assertEquals(208, image.getRed(2, 1));

    assertEquals(239, image.getGreen(0, 0));
    assertEquals(255, image.getGreen(1, 0));
    assertEquals(180, image.getGreen(2, 0));
    assertEquals(181, image.getGreen(0, 1));
    assertEquals(108, image.getGreen(1, 1));
    assertEquals(161, image.getGreen(2, 1));

    assertEquals(213, image.getBlue(0, 0));
    assertEquals(255, image.getBlue(1, 0));
    assertEquals(180, image.getBlue(2, 0));
    assertEquals(214, image.getBlue(0, 1));
    assertEquals(140, image.getBlue(1, 1));
    assertEquals(130, image.getBlue(2, 1));
  }
}