package model.functions.loadin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.SimpleRGBImage;
import model.StoredImage;

/**
 * A class to load JPG, BMP, and PNG files to new StoredImages.
 */
public class ImageIOCompatibleLoader implements ImageLoader {
  @Override
  public StoredImage load(String filename) throws IllegalArgumentException {
    try {
      BufferedImage image = ImageIO.read(new File(filename));
      int height = image.getHeight();
      int width = image.getWidth();

      int[][] r = new int[height][width];
      int[][] g = new int[height][width];
      int[][] b = new int[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color c = new Color(image.getRGB(j, i));
          r[i][j] = c.getRed();
          g[i][j] = c.getGreen();
          b[i][j] = c.getBlue();
        }
      }
      return new SimpleRGBImage(width, height, r, g, b);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not read image");
    }
  }
}
