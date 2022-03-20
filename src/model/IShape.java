package model;

import java.awt.*;

/**
 * Represents a Shape interface which provides 6 methods which efficiently allow user to get
 * the type of the shape, the position of the shape, the color of the shape, the height of the
 * shape, the width of the shape, and the specific time at the moment.
 */
public interface IShape {

  /**
   * Get the name of the shape.
   * @return the specific name of the according Shape
   */
  String getName();

  /**
   * Get the type of the shape.
   * @return the specific type of the according Shape
   */
  Shape getType();

  /**
   * Get the position of the shape.
   * @return the specific Posn of the according Shape
   */
  Posn getPosition();

  /**
   * Get the color of the shape.
   * @return the specific color (RGB) of the according Shape
   */
  Color getColor();

  /**
   * Get the height of the shape.
   * @return the specific height of the according Shape
   */
  int getHeight();

  /**
   * Get the width of the shape.
   * @return the specific width of the according Shape
   */
  int getWidth();

  /**
   * Set the width of the shape to the given width.
   * @param width the given width
   */
  void setWidth(int width);

  /**
   * Set the height of the shape to the given height.
   * @param height the given height
   */
  void setHeight(int height);

  /**
   * Set the color of the shape to the given color.
   * @param color the given color;
   */
  void setColor(Color color);

  /**
   * Set the position of the shape to the given position.
   * @param pos the given position
   */
  void setPosn(Posn pos);
}
