package controller;

import model.ImageStorage;
import model.functions.Mosaic;
import view.GuiView;

public class GuiControllerV2 extends GuiController{

  /**
   * Constructs a new GuiController given the storage and view.
   *
   * @param storage place to store images used in this program
   * @param guiView view for user interaction
   */
  public GuiControllerV2(ImageStorage storage, GuiView guiView) {
    super(storage, guiView);

    this.actions.put("mosaic", (() -> {
      int increment = this.view.requestInteger("Number of seeds for mosaic");
      String newImage = this.view.requestImageName();
      this.runFunction(
              new Mosaic(increment, this.currentImage, newImage));
      this.currentImage = newImage;
    }));
  }
}
