import model.functions.loadin.ImageIOCompatibleLoader;

/**
 * A class to test loading PNG files.
 */
public class LoadPNGTest extends AbstractImageLoaderTest {
  @Override
  public void initImage() {
    image = new ImageIOCompatibleLoader().load("res/thing.png");
  }
}
