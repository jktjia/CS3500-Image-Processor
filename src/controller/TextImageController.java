package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import model.StoredImage;
import model.functions.Blur;
import model.functions.Grayscale;
import model.functions.Load;
import model.functions.Sepia;
import model.functions.Sharpen;
import model.functions.loadin.ImageIOCompatibleLoader;
import model.functions.loadin.ImageLoader;
import model.functions.loadin.PPMLoader;

/**
 * A controller that uses text inputs and outputs and supports ppm, png, jpg, and bmp file formats.
 */
public class TextImageController extends PPMTextImageController {
  private final Map<String, ImageLoader> supportedFileLoaders;

  /**
   * Constructs a new controller.TextImageController that uses System for input and output.
   */
  public TextImageController() {
    this(new InputStreamReader(System.in), System.out);
  }

  /**
   * Constructs a new controller.TextImageController using the given input and output.
   *
   * @param in  input to controller
   * @param out output from controller
   */
  public TextImageController(Readable in, Appendable out) {
    super(in, out);
    this.functionMap.put("load", (s -> loadFromFileName(s.next(), s.next())));
    this.functionMap.put("grayscale", (s -> new Grayscale(s.next(), s.next())));
    this.functionMap.put("sepia", (s -> new Sepia(s.next(), s.next())));
    this.functionMap.put("blur", (s -> new Blur(s.next(), s.next())));
    this.functionMap.put("sharpen", (s -> new Sharpen(s.next(), s.next())));

    this.supportedFileLoaders = new HashMap<>();
    this.supportedFileLoaders.put("ppm", new PPMLoader());
    this.supportedFileLoaders.put("jpg", new ImageIOCompatibleLoader());
    this.supportedFileLoaders.put("png", new ImageIOCompatibleLoader());
    this.supportedFileLoaders.put("bmp", new ImageIOCompatibleLoader());
  }

  private Load loadFromFileName(String imagePath, String imageName) {
    String[] arr = imagePath.split("[.]");
    String extension = arr[arr.length - 1];
    return new Load(imagePath, imageName, this.supportedFileLoaders.get(extension));
  }

  @Override
  protected void save(String imagePath, String imageName) {
    // this will overwrite a file if the imagePath leads to an existing file
    Path path = Path.of(imagePath);
    if (!Files.exists(path)) {
      try {
        Files.createFile(path);
      } catch (IOException e) {
        throw new IllegalStateException("Unable to create file");
      }
    }
    File file = new File(imagePath);
    StoredImage image = this.storage.getImage(imageName);
    String[] arr = imagePath.split("[.]");
    String extension = arr[arr.length - 1];
    if (extension.equals("ppm")) {
      SaveUtils.saveFilePPM(file, image);
    } else if (extension.equals("jpg") || extension.equals("png") || extension.equals("bmp")) {
      SaveUtils.saveFileImageIOCompatible(file, image, extension);
    } else {
      throw new IllegalArgumentException("Unknown file type");
    }
  }

  @Override
  protected void menu() {
    super.menu();
    this.write("sharpen image-name dest-image-name (sharpens image)\n");
    this.write("blur image-name dest-image-name (blurs image)\n");
    this.write("grayscale image-name dest-image-name (creates a grayscale image based on luma"
            + " of image with given name)\n");
    this.write("sepia image-name dest-image-name (creates a sepia version of image with "
            + "given name)\n");
  }
}
