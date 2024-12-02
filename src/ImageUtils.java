import java.util.Random;

import model.SimpleRGBImage;
import model.StoredImage;

public class ImageUtils {
  public static StoredImage randomImage(int width, int height) {
    int[][] r = new int[height][width];
    int[][] g = new int[height][width];
    int[][] b = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < height; j++) {
        r[i][j] = new Random().nextInt(256);
        g[i][j] = new Random().nextInt(256);
        b[i][j] = new Random().nextInt(256);
      }
    }
    return new SimpleRGBImage(width, height, r, g, b);
  }
}
