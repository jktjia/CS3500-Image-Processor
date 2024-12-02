import controller.ImageController;
import controller.PPMTextImageController;

/**
 * A class to test controller.SingleFormatTextImageController.
 */
public class PPMTextImageControllerTest extends AbstractTextControllerTest {
  @Override
  protected String menu() {
    return "The valid commands for this program are:\nmenu (shows all valid commands for this "
            + "program)\nq or quit (quits the program)\nload image-path image-name (loads image "
            + "from specified path)\nsave image-path image-name (saves image with given name to "
            + "specified path)\nred-component image-name dest-image-name (creates greyscale image "
            + "with red component of image with given name)\nblue-component image-name dest-image-"
            + "name (creates greyscale image with blue component of image with given name)\ngreen-"
            + "component image-name dest-image-name (creates greyscale image with green component "
            + "of image with given name)\nvalue-component image-name dest-image-name (creates "
            + "greyscale image with value component of image with given name)\nluma-component "
            + "image-name dest-image-name (creates greyscale image with luma component of image "
            + "with given name)\nintensity-component image-name dest-image-name (creates greyscale "
            + "image with intensity-component of image with given name)\nhorizontal-flip image-name"
            + " dest-image-name (flips an image horizontally to create a new image)\nvertical-flip "
            + "image-name dest-image-name (flips an image vertically to create a new image)\n"
            + "brighten increment image-name dest-image-name (brightens image by given increment to"
            + " create a new image)\n";
  }

  @Override
  protected ImageController makeController(Readable input, Appendable output) {
    return new PPMTextImageController(input, output);
  }
}