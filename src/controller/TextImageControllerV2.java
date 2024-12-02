package controller;

import java.io.InputStreamReader;

import model.functions.Downscale;
import model.functions.Mosaic;

/**
 * A controller that uses text inputs and outputs and supports ppm, png, jpg, and bmp file formats,
 * and supports everything TextImageController and has mosaics and downscaling images.
 */
public class TextImageControllerV2 extends TextImageController {
  /**
   * Constructs a new TextImageController that uses System for input and output.
   */
  public TextImageControllerV2() {
    this(new InputStreamReader(System.in), System.out);
  }

  /**
   * Constructs a new TextImageController using the given input and output.
   *
   * @param in  input to controller
   * @param out output from controller
   */
  public TextImageControllerV2(Readable in, Appendable out) {
    super(in, out);
    this.functionMap.put("mosaic", (s -> new Mosaic(s.nextInt(), s.next(), s.next())));
    this.functionMap.put("downscale",
            (s -> new Downscale(s.nextInt(), s.nextInt(), s.next(), s.next())));
  }

  @Override
  public void menu() {
    super.menu();
    this.write("mosaic num-seeds image-name dest-image-name (creates a mosaic version of an "
            + "image given the number of seeds)\n");
    this.write("downscale width height image-name dest-image-name (downscales the image to the "
            + "given width and height)\n");
  }
}
