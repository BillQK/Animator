package model.shape;

import java.awt.*;

import model.utils.Time;

/**
 * This class represents the specific Rectangle shape which have a Rectangle shapetype, color,
 * positions and dimensions.
 */
public class Rectangle extends AShape {

  /**
   * A constructor for Rectangle.
   *
   * @param name the name of the shape
   * @param type the type of the shape (Rectangle or Ellipse)
   * @param col the color of the shape
   * @param posX the x position of the shape
   * @param posY the y position of the shape
   * @param width the width of the shape
   * @param height the height of the shape
   * @param time the Time of the shape
   */
  public Rectangle(String name, Shape type, Color col, double posX, double posY,
                   double width, double height, Time time) {
    super(name, type, col, posX, posY, width, height, time);
  }

  @Override
  public Rectangle getTheShape() {
    return new Rectangle(this.getName(), this.getType(), this.getColor(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getWidth(), this.getHeight(), this.getTime());
  }

  @Override
  public Shape getType() {
    return Shape.RECTANGLE;
  }

}
