package model.functions;

import model.SimpleRGBImage;
import model.StoredImage;

/**
 * A class to create an image that is another image flipped horizontally.
 */
public class FlipHorizontal extends AbstractTransformImage {
  /**
   * Constructs a new FlipHorizontal given the original image name and the name of the new image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public FlipHorizontal(String imageName, String destName) {
    super(imageName, destName);
  }

  @Override
  protected StoredImage generateImage(StoredImage original) {
    int[][] flippedR = new int[original.getHeight()][original.getWidth()];
    int[][] flippedG = new int[original.getHeight()][original.getWidth()];
    int[][] flippedB = new int[original.getHeight()][original.getWidth()];
    int h = original.getHeight();
    int w = original.getWidth();
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        flippedR[i][j] = original.getRed(w - 1 - j, i);
        flippedG[i][j] = original.getGreen(w - 1 - j, i);
        flippedB[i][j] = original.getBlue(w - 1 - j, i);
      }
    }
    return new SimpleRGBImage(w, h, flippedR, flippedG, flippedB);
  }
}
