package view;

import java.awt.event.ActionListener;

/**
 * This interface represents a panel class that interacts with the controller and requires an
 * action listener.
 */
public interface ActionInteracter extends ControllerInteracter {
  /**
   * Sets the listener for this object to the given ActionListener.
   *
   * @param listener listener to listen to this object
   */
  void setActionListener(ActionListener listener);
}
