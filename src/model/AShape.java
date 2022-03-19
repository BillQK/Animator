package model;

import java.awt.*;

public abstract class AShape implements IShape {
  Shape type;
  Color col;
  int w;
  int h;
  Posn pos;

  public AShape(Shape type, Color col, int posX, int posY, int width, int height) {
    if (type == null || type.getShapeType().equals("") || col == null || width < 0 || height < 0
            || posX < 0 || posY < 0) {
      throw new IllegalArgumentException("The given arguments cannot be null/empty/negative");
    }
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
  public int getWidth() {
    return this.w;
  }

  @Override
  public int getHeight() {
    return this.h;
  }

  @Override
  public void setWidth(int width) {
    if (width < 0) {
      throw new IllegalArgumentException("The width of the shape cannot be negative.");
    }
    this.w = width;
  }

  @Override
  public void setHeight(int height) {
    if (height < 0) {
      throw new IllegalArgumentException("The height of the shape cannot be negative.");
    }
    this.h = height;
  }

  @Override
  public void setColor(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("The color of the shape cannot be null.");
    }
    this.col = color;
  }

  @Override
  public void setPosn(Posn pos) {
    if (pos == null || pos.getY() < 0 || pos.getX() < 0) {
      throw new IllegalArgumentException("The Posn of the shape cannot be null or the X and Y " +
              "cannot be negative.");
    }
    this.pos = pos;
  }

}
