package model.functions;

/**
 * A class to create a new sepia version of the original image.
 */
public class Sepia extends AbstractColorTransformation {
  private static final double[][] MATRIX = {{0.393, 0.769, 0.189},
                                            {0.349, 0.686, 0.168},
                                            {0.272, 0.534, 0.131}};

  /**
   * Constructs a new Sepia given the original image name and the name of the new image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public Sepia(String imageName, String destName) {
    super(imageName, destName, MATRIX);
  }
}
