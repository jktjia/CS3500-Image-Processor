package view;

import controller.Features;

/**
 * Represents a panel of the gui view that interacts with the controller.
 */
public interface ControllerInteracter {
  /**
   * Refresh the screen. This is called when the something on the screen is updated, and therefore
   * it must be redrawn.
   */
  void refresh();

  /**
   * Takes in a GuiController for the ControllerPanel to interact with.
   *
   * @param controller controller for the image processor being viewed
   */
  void setController(Features controller);
}
