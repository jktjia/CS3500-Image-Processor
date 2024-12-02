package model.functions;

import model.SimpleRGBImage;
import model.StoredImage;

/**
 * A class to create an image that is another image flipped vertically.
 */
public class FlipVertical extends AbstractTransformImage {
  /**
   * Constructs a new FlipVertical given the original image name and the name of the new image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public FlipVertical(String imageName, String destName) {
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
      for (int j = 0; j < original.getWidth(); j++) {
        flippedR[i][j] = original.getRed(j, h - 1 - i);
        flippedG[i][j] = original.getGreen(j, h - 1 - i);
        flippedB[i][j] = original.getBlue(j, h - 1 - i);
      }
    }
    return new SimpleRGBImage(w, h, flippedR, flippedG, flippedB);
  }
}
