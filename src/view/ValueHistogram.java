package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.BorderFactory;

/**
 * A class for a panel that shows a histogram representing the values of the pixels in an image.
 */
public class ValueHistogram extends JPanel {
  int maxHeight = 130;
  int barWidth = 10;
  private final int[] values;
  private final Color color;

  /**
   * Constructs a new ValueHistogram given the color of the bars and the data for the graph.
   *
   * @param values data for the graph
   * @param color  color of the bars in the graph
   */
  public ValueHistogram(int[] values, Color color) {
    this.setMinimumSize(new Dimension(barWidth * (values.length + 1), maxHeight + 25));
    this.setBackground(Color.WHITE);
    this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    this.values = values;
    this.color = color;

    this.repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    int maxValue = Arrays.stream(values).max().orElse(0);

    for (int i = 0; i < values.length; i++) {
      int barHeight = values[i] * this.maxHeight / maxValue;
      int x = (i + 1) * this.barWidth;
      int y = this.maxHeight - barHeight + 5;
      g.setColor(this.color);
      g.fillRect(x, y, this.barWidth, barHeight);
      g.setColor(Color.black);
      g.drawRect(x, y, this.barWidth, barHeight);
    }
    g.drawString("0", (this.barWidth / 2), this.maxHeight + 20);
    g.drawString("255",
                 (this.barWidth * this.values.length),
                 this.maxHeight + 20);
  }
}
