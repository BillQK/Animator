package model.shape;

import java.awt.*;

import model.utils.Time;

public class Ellipse extends AShape {

  public Ellipse(String name, model.shape.Shape type, Color col, double posX, double posY,
                 double width, double height, Time time) {
    super(name, type, col, posX, posY, width, height, time);

  }

  @Override
  public Ellipse getTheShape() {
    return new Ellipse(this.getName(), this.getType(), this.getColor(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getWidth(), this.getHeight(), this.getTime());
  }

  @Override
  public model.shape.Shape getType() {
    return Shape.ELLIPSE;
  }

}
