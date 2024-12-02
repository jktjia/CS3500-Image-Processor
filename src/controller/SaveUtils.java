package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.StoredImage;

/**
 * A utils class for saving StoredImages to files.
 */
public class SaveUtils {
  /**
   * Saves a storedImage in PPM file format to the given appendable.
   *
   * @param appendable appendable to save to
   * @param image      image being saved
   * @throws IllegalStateException if appendable cannot be written to
   */
  public static void savePPM(Appendable appendable, StoredImage image)
          throws IllegalStateException {
    try {
      appendable.append("P3\n");
      appendable.append(image.getWidth() + " " + image.getHeight() + "\n");
      appendable.append(255 + "\n");

      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          appendable.append(image.getRed(j, i) + "\n");
          appendable.append(image.getGreen(j, i) + "\n");
          appendable.append(image.getBlue(j, i) + "\n");
        }
      }

    } catch (IOException e) {
      throw new IllegalStateException("Could not write to appendable");
    }
  }

  /**
   * Saves an image in ppm format to the given file given the image to save.
   *
   * @param file  file to save to
   * @param image image to save
   * @throws IllegalStateException if writing to the file fails
   */
  public static void saveFilePPM(File file, StoredImage image) throws IllegalStateException {
    try {
      FileWriter fw = new FileWriter(file);
      savePPM(fw, image);
      fw.close();
    } catch (IOException e) {
      throw new IllegalStateException("Could not write to file");
    }
  }

  /**
   * Saves an image in an ImageIO compatible (jpg, bmp, png) format to the given file given the
   * image to save.
   *
   * @param file           file to save to
   * @param image          image to save
   * @param imageExtension file extension denoting what format the image should be saved as
   * @throws IllegalStateException if writing to the file fails
   */
  public static void saveFileImageIOCompatible(File file, StoredImage image, String imageExtension)
          throws IllegalStateException {
    try {
      ImageIO.write(image.toBufferedImage(), imageExtension, file);
    } catch (IOException e) {
      throw new IllegalStateException("Could not write to file");
    }
  }
}
