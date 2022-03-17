package model;

import java.awt.*;

public class Ellipse extends AShape {

  public Ellipse(Shape type, Color col, int posX, int posY, int width, int height) {
    super(type, col, posX, posY, width, height);

  }

  @Override
  public Shape getType() {
    return Shape.ELLIPSE;
  }

}
