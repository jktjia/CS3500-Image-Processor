import model.functions.loadin.ImageIOCompatibleLoader;

/**
 * A class to test loading BMP files.
 */
public class LoadBMPTest extends AbstractImageLoaderTest {
  @Override
  public void initImage() {
    image = new ImageIOCompatibleLoader().load("res/thing.bmp");
  }
}
