package model.functions;

/**
 * A class to create a new blurred image based on another image using the Gaussian blur.
 */
public class Blur extends AbstractFilterImage {
  private static final double[][] ARRAY = {{0.0625, 0.125, 0.0625},
                                           {0.125, 0.25, 0.125},
                                           {0.0625, 0.125, 0.0625}};

  /**
   * Constructs a new Blur given the original image name and the name of the new image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public Blur(String imageName, String destName) {
    super(imageName, destName, ARRAY);
  }
}
