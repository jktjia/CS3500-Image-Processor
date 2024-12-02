package model;

public class MaskImage extends SimpleRGBImage {
  /**
   * Creates a new storage. SimpleRGBImage given 2D arrays of the colors. The max value is 255, the
   * min is 0.
   *
   * @param maskedImage the image being masked with this MaskImage
   * @param mask        an array showing which pixels are included in the mask
   */
  public MaskImage(StoredImage maskedImage, boolean[][] mask) {
    super(maskedImage.getWidth(), maskedImage.getHeight(), mask, mask, mask);
  }

  private int[][] maskToColor(boolean[][] mask) {
    int[][] toReturn = new int[mask.length][mask[0].length];
    return
  }
}
