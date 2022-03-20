package model.shape;

import java.awt.*;

import model.utils.Time;

/**
 * This class represents the specific Rectangle shape which have a Rectangle shapetype, color,
 * positions and dimensions.
 */
public class Rectangle extends AShape {

  public Rectangle(String name, Shape type, Color col, double posX, double posY,
                   int width, int height, Time time) {
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
