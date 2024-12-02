package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import model.ImageStorage;
import model.MappedStorage;
import model.functions.BlueComponent;
import model.functions.Brighten;
import model.functions.FlipHorizontal;
import model.functions.FlipVertical;
import model.functions.GreenComponent;
import model.functions.ImageFunction;
import model.functions.IntensityComponent;
import model.functions.Load;
import model.functions.LumaComponent;
import model.functions.RedComponent;
import model.functions.ValueComponent;
import model.functions.loadin.PPMLoader;

/**
 * A class to implement controller.ImageController with the ability to load from a single format
 * and save files in a PPM format. The load in format and save out format do not need to match.
 */
public class PPMTextImageController implements ImageController {
  // I renamed this class from ImageControllerImpl to PPMTextImageController
  private final Readable in;
  private final Appendable out;
  protected final ImageStorage storage;
  protected final Map<String, Function<Scanner, ImageFunction>> functionMap;
  private boolean quit;

  /**
   * Constructs a new PPMTextImageController that uses System for input and output.
   */
  public PPMTextImageController() {
    this(new InputStreamReader(System.in), System.out);
  }

  /**
   * Constructs a new PPMTextImageController using the given input and output.
   *
   * @param in  input to controller
   * @param out output from controller
   */
  public PPMTextImageController(Readable in, Appendable out) {
    this.in = Objects.requireNonNull(in);
    this.out = Objects.requireNonNull(out);
    this.storage = new MappedStorage();
    this.functionMap = new HashMap<>();

    this.functionMap.put("load", (s -> new Load(s.next(), s.next(), new PPMLoader())));
    this.functionMap.put("red-component", (s -> new RedComponent(s.next(), s.next())));
    this.functionMap.put("green-component", (s -> new GreenComponent(s.next(), s.next())));
    this.functionMap.put("blue-component", (s -> new BlueComponent(s.next(), s.next())));
    this.functionMap.put("value-component", (s -> new ValueComponent(s.next(), s.next())));
    this.functionMap.put("intensity-component", (s -> new IntensityComponent(s.next(), s.next())));
    this.functionMap.put("luma-component", (s -> new LumaComponent(s.next(), s.next())));
    this.functionMap.put("horizontal-flip", (s -> new FlipHorizontal(s.next(), s.next())));
    this.functionMap.put("vertical-flip", (s -> new FlipVertical(s.next(), s.next())));
    this.functionMap.put("brighten", (s -> new Brighten(s.nextInt(), s.next(), s.next())));
  }

  @Override
  public void run() {
    Scanner s = new Scanner(this.in);

    this.menu();

    while (!this.quit) {
      try {
        this.runCommand(s.next(), s);
      } catch (NoSuchElementException e) {
        // if the scanner ever runs out of stuff in it for whatever reason
        this.write("Unexpected end of input\nNow ending program\n");
        break;
      }
    }

    this.quitMessage();
  }

  private void runCommand(String command, Scanner s) {
    if (command.equals("save")) {
      this.save(s.next(), s.next());
    } else if (this.functionMap.containsKey(command)) {
      try {
        ImageFunction f = this.functionMap.get(command).apply(s);
        f.run(this.storage);
      } catch (InputMismatchException e) {
        // catches invalid input for command, skips invalid input, asks for new command
        this.write("Invalid input for command \"" + command + "\"\n"
                + "Please try again\n");
        s.next();
      } catch (IllegalArgumentException | IllegalStateException e) {
        this.write(e.getMessage() + "\n");
      }
    } else if (command.equalsIgnoreCase("q")
            || command.equalsIgnoreCase("quit")) {
      this.quit = true;
    } else if (command.equals("menu")) {
      this.menu();
    } else {
      this.write("Unknown command: " + command + "\n");
    }
  }

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
    try {
      FileWriter fw = new FileWriter(file);
      SaveUtils.savePPM(fw, this.storage.getImage(imageName));
      fw.close();
    } catch (IOException e) {
      throw new IllegalStateException("Could not save file");
    }
  }

  protected void menu() {
    this.write("The valid commands for this program are:\n");
    this.write("menu (shows all valid commands for this program)\n");
    this.write("q or quit (quits the program)\n");
    this.write("load image-path image-name (loads image from specified path)\n");
    this.write("save image-path image-name (saves image with given name to specified path)\n");
    this.write("red-component image-name dest-image-name (creates greyscale image with red "
            + "component of image with given name)\n");
    this.write("blue-component image-name dest-image-name (creates greyscale image with blue "
            + "component of image with given name)\n");
    this.write("green-component image-name dest-image-name (creates greyscale image with green "
            + "component of image with given name)\n");
    this.write("value-component image-name dest-image-name (creates greyscale image with value "
            + "component of image with given name)\n");
    this.write("luma-component image-name dest-image-name (creates greyscale image with luma "
            + "component of image with given name)\n");
    this.write("intensity-component image-name dest-image-name (creates greyscale image with "
            + "intensity-component of image with given name)\n");
    this.write("horizontal-flip image-name dest-image-name (flips an image horizontally to "
            + "create a new image)\n");
    this.write("vertical-flip image-name dest-image-name (flips an image vertically to create "
            + "a new image)\n");
    this.write("brighten increment image-name dest-image-name (brightens image by given "
            + "increment to create a new image)\n");
  }

  private void quitMessage() {
    this.write("Goodbye.");
  }

  protected void write(String s) {
    try {
      this.out.append(s);
    } catch (IOException e) {
      throw new IllegalStateException("Could not append to appendable");
    }
  }
}
