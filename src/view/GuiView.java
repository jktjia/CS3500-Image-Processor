package view;

/**
 * This interface represents a GUI view for image processing using this program.
 */
public interface GuiView extends ActionInteracter {
  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void makeVisible();

  /**
   * Gets the location tp load from.
   *
   * @return path to load location as a string
   */
  String requestLoadLoc();

  /**
   * Gets the location for a save.
   *
   * @return path to save location as a string
   */
  String requestSaveLoc();

  /**
   * Gets a name to save a new image under.
   *
   * @return name for new image
   */
  String requestImageName();

  /**
   * Asks the user for an integer to use in some action.
   *
   * @param message message for user about what integer is for
   * @return integer entered by user
   */
  int requestInteger(String message);

  /**
   * Creates a pop-up window with the given message.
   *
   * @param message message written in pop-up window
   */
  void errorPopUp(String message);
}
