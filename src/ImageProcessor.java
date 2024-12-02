import java.io.FileNotFoundException;
import java.io.FileReader;

import controller.GuiControllerV3;
import controller.ImageController;
import controller.TextImageControllerV2;
import model.ImageStorage;
import model.MappedStorage;
import view.GuiView;
import view.SwingGuiView;

/**
 * A class to run the main program of using a default controller (that uses PPM, PNG, JPG, and BMP
 * files).
 */
public class ImageProcessor {
  /**
   * Runs a new GuiController, a new TextImageController with default settings (loads and saves
   * PPM, PNG, JPG, and BMP files and uses System.in and System.out for input and output), or a new
   * TextImageController that uses a given file as its input and does not show any output.
   *
   * @param args command line arguments for main
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      ImageStorage storage = new MappedStorage();
      GuiView view = new SwingGuiView();

      ImageController controller = new GuiControllerV3(storage, view);
      controller.run();
    } else {
      String s = args[0];
      switch (s) {
        case "-text":
          ImageController controller = new TextImageControllerV2();
          controller.run();
          break;
        case "-file":
          try {
            String fileName = args[1];
            if (!fileName.contains(".txt")) {
              throw new IllegalArgumentException("File given is not a txt file");
            }
            try {
              FileReader fr = new FileReader(fileName);
              controller = new TextImageControllerV2(fr, System.out);
              controller.run();
            } catch (FileNotFoundException e) {
              System.out.print("File " + fileName + " not found");
            }
          } catch (IndexOutOfBoundsException e) {
            System.out.print("No file given");
          }
          break;
        default:
          System.out.print("Unrecognized command");
      }
    }
  }
}
