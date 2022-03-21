package model.shape;

import java.awt.*;
import java.util.Objects;

import model.utils.ArgumentsCheck;
import model.utils.Posn;
import model.utils.Time;

public abstract class AShape {
  private final String name;
  private final Shape type;
  private Color col;
  private double w;
  private double h;
  private final Time time;
  private Posn pos;

  public AShape(String name, Shape type, Color col, double posX, double posY,
                double width, double height, Time time) {
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

  public double getWidth() {
    return this.w;
  }

  public double getHeight() {
    return this.h;
  }

  public Time getTime() {
    return this.time;
  }

  public void setWidth(double width) {
    if (width < 0) {
      throw new IllegalArgumentException("The width of the shape cannot be negative.");
    }
    this.w = width;
  }

  public void setHeight(double height) {
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

  public boolean equals(Object shape) {
    if (this == shape) {
      return true;
    } else if (shape == null) {
      return false;
    } else {
      AShape shapes = (AShape) shape;
      return Objects.equals(this.name, shapes.name) && Objects.equals(this.type, shapes.type)
              && Objects.equals(this.col, shapes.col) && Objects.equals(this.w, shapes.w)
              && Objects.equals(this.h, shapes.h) && Objects.equals(this.time, shapes.time)
              && Objects.equals(this.pos, shapes.pos);
    }
  }

  public int hashCode() {
    return Objects.hash(this.name, this.type, this.col,
            this.w, this.h, this.time, this.pos);
  }

}
