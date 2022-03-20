package model;

import java.awt.*;

/**
 * This class represents the specific Rectangle shape which have a Rectangle shapetype, color,
 * positions and dimensions.
 */
public class Rectangle extends AShape {

  public Rectangle(String name, Shape type, Color col, int posX, int posY, int width, int height) {
    super(name, type, col, posX, posY, width, height);
  }

  @Override
  protected Rectangle getTheShape() {
    return new Rectangle(this.getName(), this.getType(), this.getColor(),
            this.getPosition().getX(), this.getPosition().getY(), this.getWidth(), this.getHeight());
  }

  @Override
  public Shape getType() {
    return Shape.RECTANGLE;
  }

}
