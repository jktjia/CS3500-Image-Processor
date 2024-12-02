package model.functions.loadin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.SimpleRGBImage;
import model.StoredImage;

/**
 * A class to load PPM files as SimpleRGBImages.
 */
public class PPMLoader implements ImageLoader {
  @Override
  public StoredImage load(String filename) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    int[][] r = new int[height][width];
    int[][] g = new int[height][width];
    int[][] b = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        r[i][j] = sc.nextInt() * 255 / maxValue;
        g[i][j] = sc.nextInt() * 255 / maxValue;
        b[i][j] = sc.nextInt() * 255 / maxValue;
      }
    }

    return new SimpleRGBImage(width, height, r, g, b);
  }
}
