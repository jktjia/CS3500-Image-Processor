package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Features;

/**
 * A class for a panel containing buttons for commands for the controller to do something with.
 */
public class ButtonCommandPanel extends JPanel implements ActionInteracter {
  private JButton[] buttons;

  /**
   * Constructs a new ButtonCommandPanel given the relevant parameters.
   */
  public ButtonCommandPanel(int width, int minHeight) {
    this.setMinimumSize(new Dimension(width, minHeight));
    this.setMaximumSize(new Dimension(width, 900));
    this.setLayout(new GridLayout(0,1));
  }


  @Override
  public void refresh() {
    // the valid commands should not be changing, so this does not need to change
  }

  @Override
  public void setController(Features controller) {
    String[] commands = controller.validCommands();
    this.buttons = new JButton[commands.length];
    for (int i = 0 ; i < commands.length; i++) {
      String s = commands[i].toLowerCase();
      JButton b = new JButton(s.substring(0, 1).toUpperCase() + s.substring(1));
      b.setActionCommand(commands[i]);
      this.add(b);
      this.buttons[i] = b;
    }
  }

  @Override
  public void setActionListener(ActionListener listener) {
    for (JButton b : this.buttons) {
      b.addActionListener(listener);
    }
  }
}
