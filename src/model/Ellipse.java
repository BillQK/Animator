package model;

import java.awt.*;

public class Ellipse extends AShape {

  public Ellipse(String name, Shape type, Color col, int posX, int posY,
                 int width, int height, Time time) {
    super(name, type, col, posX, posY, width, height, time);

  }

  @Override
  public Ellipse getTheShape() {
    return new Ellipse(this.getName(), this.getType(), this.getColor(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getWidth(), this.getHeight(), this.getTime());
  }

  @Override
  public Shape getType() {
    return Shape.ELLIPSE;
  }

}
