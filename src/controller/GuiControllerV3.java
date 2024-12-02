package controller;

import model.ImageStorage;
import model.functions.Downscale;
import model.functions.Mosaic;
import view.GuiView;

public class GuiControllerV3 extends GuiControllerV2 {

  /**
   * Constructs a new GuiController given the storage and view.
   *
   * @param storage place to store images used in this program
   * @param guiView view for user interaction
   */
  public GuiControllerV3(ImageStorage storage, GuiView guiView) {
    super(storage, guiView);

    this.actions.put("downscale", (() -> {
      int width = this.view.requestInteger("Width of downscaled image");
      int height = this.view.requestInteger("Height of downscaled image");
      String newImage = this.view.requestImageName();
      this.runFunction(
              new Downscale(width, height, this.currentImage, newImage));
      this.currentImage = newImage;
    }));
  }
}
