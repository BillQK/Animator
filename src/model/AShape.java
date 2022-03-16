package model;

import java.awt.*;

public abstract class AShape implements IShape {
  Shape type;
  Color col;
  int w;
  int h;
  Posn pos;


  public AShape(Shape type, Color col, int posX, int posY, int width, int height) {
    this.type = type;
    this.col = col;
    this.pos = new Posn(posX, posY);
    this.w = width;
    this.h = height;
  }


  @Override
  public abstract Shape getType();

  @Override
  public Posn getPosition() {
    return new Posn(this.pos);
  }

  @Override
  public Color getColor() {
    return new Color(this.col.getRGB());

  }

  @Override
  public void getTick() {

  }

  @Override
  public int getWidth() {
    return this.w;
  }

  @Override
  public int getHeight() {
    return this.h;
  }

}
