package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.shape.AShape;


/**
 * Represent the APanel class which extends APanel, which is where we paint out visual view
 * according to what we put in.
 */
public class APanel extends JPanel {
  private List<AShape> shapes;
  private boolean isOutline;
  private boolean isSlowMo;


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

      if (!isOutline) {
        switch (s.getType()) {
          case RECTANGLE:
            g2.setColor(c);
            g2.fillRect(x, y, w, h);
            g2.drawRect(x, y, w, h);
            break;
          case ELLIPSE:
            g2.setColor(c);
            g2.fillOval(x, y, w, h);
            g2.drawOval(x, y, w, h);
            break;
          case PLUS:
            g2.setColor(c);
            g2.fillRect(x + (w / 4), y, w / 2, h);
            g2.fillRect(x, y + (h / 4), w, h / 2);
            g2.drawRect(x + (w / 4), y, w / 2, h);
            g2.drawRect(x, y + (h / 4), w, h / 2);

            break;
          default:
            break;
        }
      }
      if (isOutline) {
        switch (s.getType()) {
          case RECTANGLE:
            g2.setColor(c);
            g2.drawRect(x, y, w, h);
            break;
          case ELLIPSE:
            g2.setColor(c);
            g2.drawOval(x, y, w, h);
            break;
          case PLUS:
            g2.setColor(c);

            //North Center
            g2.drawLine(x + (w / 4), y, x + (w * 3 / 4), y);
            //North Right
            g2.drawLine(x + (w * 3 / 4), y, x + (w * 3 / 4), y + (h / 4));
            //North Left
            g2.drawLine(x + (w / 4), y, x + (w / 4), y + (h / 4));

            //West Center
            g2.drawLine(x, y + (h / 4), x, y + (h * 3 / 4));
            //West Right
            g2.drawLine(x, y + (h / 4), x + (w / 4), y + (h / 4));
            //West Left
            g2.drawLine(x, y + (h * 3 / 4), x + (w / 4), y + (h * 3 / 4));

            //South Center
            g2.drawLine(x + (w / 4), y + h, x + (w * 3 / 4), y + h);
            //South Left
            g2.drawLine(x + (w / 4), y + (h * 3 / 4), x + (w / 4), y + h);
            //South Right
            g2.drawLine(x + (w * 3 / 4), y + (h * 3 / 4), x + (w * 3 / 4), y + h);

            //East Center
            g2.drawLine(x + w, y + (h / 4), x + w, y + (h * 3 / 4));
            //East Left
            g2.drawLine(x + (w * 3 / 4), y + (h / 4), x + w, y + (h / 4));
            //East Right
            g2.drawLine(x + (w * 3 / 4), y + (h * 3 / 4), x + w, y + (h * 3 / 4));

            break;
          default:
            break;
        }
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

  /**
   * A method to set the IsOutline to the given boolean.
   *
   * @param outline the given boolean
   */
  public void setIsOutline(boolean outline) {
    this.isOutline = outline;
  }

  /**
   * A method to set the IsDiscreteT to the given boolean.
   *
   * @param discreteT the given boolean
   */
  public void setIsDiscreteT(boolean discreteT) {
  }

}
