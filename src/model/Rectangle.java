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
    return this;
  }

  @Override
  public Shape getType() {
    return Shape.RECTANGLE;
  }

}
