package model.functions;

/**
 * A class to create a new grayscale version of the original image using the luma value of each
 * pixel.
 */
public class Grayscale extends AbstractColorTransformation {
  private static final double[][] MATRIX = {{0.2126, 0.7152, 0.0722},
                                            {0.2126, 0.7152, 0.0722},
                                            {0.2126, 0.7152, 0.0722}};

  /**
   * Constructs a new Grayscale given the original image name and the name of the new image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public Grayscale(String imageName, String destName) {
    super(imageName, destName, MATRIX);
  }
}
