package model;

import java.awt.*;

public class Ellipse extends AShape {

  public Ellipse(String name, Shape type, Color col, int posX, int posY, int width, int height) {
    super(name, type, col, posX, posY, width, height);

  }

  @Override
  protected Ellipse getTheShape() {
    return this;
  }

  @Override
  public Shape getType() {
    return Shape.ELLIPSE;
  }

}
