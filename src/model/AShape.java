package model;

import java.awt.*;

public abstract class AShape {
  private final String name;
  private final Shape type;
  private Color col;
  private int w;
  private int h;
  private final Time time;
  private Posn pos;

  public AShape(String name, Shape type, Color col, int posX, int posY,
                int width, int height, Time time) {
    if (name == null || type == null || col == null || time == null) {
      throw new IllegalArgumentException("The given arguments cannot be null/empty/negative");
    }
    ArgumentsCheck.lessThanZero(width, height, posX, posY);
    ArgumentsCheck.emptyString(name, type.getShapeType());
    this.name = name;
    this.type = type;
    this.col = col;
    this.pos = new Posn(posX, posY);
    this.w = width;
    this.h = height;
    this.time = time;
  }

  public abstract AShape getTheShape();

  public String getName() {
    return this.name;
  }

  public abstract Shape getType();

  public Posn getPosition() {
    return new Posn(this.pos);
  }

  public Color getColor() {
    return new Color(this.col.getRGB());
  }

  public int getWidth() {
    return this.w;
  }

  public int getHeight() {
    return this.h;
  }

  public Time getTime() {
    return this.time;
  }

  public void setWidth(int width) {
    if (width < 0) {
      throw new IllegalArgumentException("The width of the shape cannot be negative.");
    }
    this.w = width;
  }

  public void setHeight(int height) {
    if (height < 0) {
      throw new IllegalArgumentException("The height of the shape cannot be negative.");
    }
    this.h = height;
  }

  public void setColor(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("The color of the shape cannot be null.");
    }
    this.col = color;
  }

  public void setPosn(Posn pos) {
    if (pos == null || pos.getY() < 0 || pos.getX() < 0) {
      throw new IllegalArgumentException("The Posn of the shape cannot be null or the X and Y " +
              "cannot be negative.");
    }
    this.pos = pos;
  }

}
