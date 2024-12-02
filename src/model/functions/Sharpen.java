package model.functions;

/**
 * A class to create a new sharpened image based on another image.
 */
public class Sharpen extends AbstractFilterImage {
  private static final double[][] ARRAY = {{-0.125, -0.125, -0.125, -0.125, -0.125},
                                           {-0.125, 0.25, 0.25, 0.25, -0.125},
                                           {-0.125, 0.25, 1, 0.25, -0.125},
                                           {-0.125, 0.25, 0.25, 0.25, -0.125},
                                           {-0.125, -0.125, -0.125, -0.125, -0.125}};

  /**
   * Constructs a new AbstractFilterImage given the original image name and the name of the new
   * image.
   *
   * @param imageName name of original image
   * @param destName  name of new image
   */
  public Sharpen(String imageName, String destName) {
    super(imageName, destName, ARRAY);
  }
}
