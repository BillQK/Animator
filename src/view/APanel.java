package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.shape.AShape;
import model.shape.Shape;

/**
 * Represent the APanel class which extends JPanel, which is where we paint out visual view
 * according to what we put in.
 */
public class APanel extends JPanel {
  private List<AShape> shapes;

  /**
   * Constructor for APanel class.
   */
  public APanel() {
    super();
    shapes = new ArrayList<>();
    this.setBackground(Color.WHITE);
  }

  /**
   * A method to paint our visual view which present the view.
   *
   * @param g the given graphics tools to paint the view
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    AffineTransform transform = ((Graphics2D) g).getTransform();

    for (AShape s : shapes) {
      int x = (int) s.getPosition().getX();
      int y = (int) s.getPosition().getY();
      int w = (int) s.getWidth();
      int h = (int) s.getHeight();
      Color c = s.getColor();

      if (s.getType() == Shape.RECTANGLE) {
        g2.setColor(c);
        g2.fillRect(x,y,w,h);
        g2.drawRect(x,y,w,h);
      } else if (s.getType() == Shape.ELLIPSE) {
        g2.setColor(c);
        g2.fillOval(x,y,w,h);
        g2.drawOval(x,y,w,h);
      }
    }
    g2.setTransform(transform);

  }

  /**
   * A method to set the list of shapes field to the given list of shape arguments.
   *
   * @param s the given list of Shapes
   */
  public List<AShape> setShapes(List<AShape> s) {
    if (s == null) {
      throw new IllegalArgumentException("The list of shapes cannot be null");
    }
    return this.shapes = s;
  }

}
