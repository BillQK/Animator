package model.shape;

import java.awt.*;
import java.util.Objects;

import model.utils.ArgumentsCheck;
import model.utils.Posn;
import model.utils.Time;

/**
 * Represents an Abstract shape class which is being implemented
 * by Rectangle and Ellipse shape class.
 */
public abstract class AShape {
  private final String name;
  private final Shape type;
  private Color col;
  private double w;
  private double h;
  private final Time time;
  private Posn pos;

  /**
   * A constructor for Ashape.
   *
   * @param name the name of the shape
   * @param type the type of the shape (Rectangle or Ellipse)
   * @param col the color of the shape
   * @param posX the x position of the shape
   * @param posY the y position of the shape
   * @param width the width of the shape
   * @param height the height of the shape
   * @param time the Time of the shape
   */
  public AShape(String name, Shape type, Color col, double posX, double posY,
                double width, double height, Time time) {
    if (name == null || type == null || col == null || time == null) {
      throw new IllegalArgumentException("The given arguments cannot be null/empty/negative");
    }
    ArgumentsCheck.lessThanZero(width, height, posX, posY);
    ArgumentsCheck.emptyString(name, type.getShapeType());
    ArgumentsCheck.colorRange(col.getRed(), col.getGreen(), col.getBlue());
    this.name = name;
    this.type = type;
    this.col = col;
    this.pos = new Posn(posX, posY);
    this.w = width;
    this.h = height;
    this.time = time;
  }

  /**
   * A method to get the specific whole shape.
   *
   * @return the specific shape
   */
  public abstract AShape getTheShape();

  /**
   * A method to get the name of the shape.
   *
   * @return a String - the shape's name
   */
  public String getName() {
    return this.name;
  }

  /**
   * A method to get the shape type (Rectangle or Ellipse).
   *
   * @return an Enum - the shape's type
   */
  public abstract Shape getType();

  /**
   * A method to get the position of the shape.
   *
   * @return a Posn - the shape's position
   */
  public Posn getPosition() {
    return new Posn(this.pos);
  }

  /**
   * A method to get the color of the shape.
   *
   * @return a Color - the shape's color
   */
  public Color getColor() {
    return new Color(this.col.getRGB());
  }

  /**
   * A method to get the width of the shape.
   *
   * @return a double - the shape's width
   */
  public double getWidth() {
    return this.w;
  }

  /**
   * A method to get the height of the shape.
   *
   * @return a double - the shape's height
   */
  public double getHeight() {
    return this.h;
  }


  /**
   * A method to get the time of the shape.
   *
   * @return a Time - the shape's time
   */
  public Time getTime() {
    return this.time;
  }

  /**
   * A method to set the width of the shape to a new given width.
   *
   * @param width a double - the new given width
   */
  public void setWidth(double width) {
    if (width < 0) {
      throw new IllegalArgumentException("The width of the shape cannot be negative.");
    }
    this.w = width;
  }

  /**
   * A method to set the height of the shape to a new given height.
   *
   * @param height a double - the new given height
   */
  public void setHeight(double height) {
    if (height < 0) {
      throw new IllegalArgumentException("The height of the shape cannot be negative.");
    }
    this.h = height;
  }

  /**
   * A method to set the color of the shape to a new given color.
   *
   * @param color a Color - the new given Color
   */
  public void setColor(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("The color of the shape cannot be null.");
    }
    this.col = color;
  }

  /**
   * A method to set the position of the shape to a new given position.
   *
   * @param pos a Posn - the new given Posn
   */
  public void setPosn(Posn pos) {
    if (pos == null || pos.getY() < 0 || pos.getX() < 0) {
      throw new IllegalArgumentException("The Posn of the shape cannot be null or the X and Y " +
              "cannot be negative.");
    }
    this.pos = pos;
  }

  @Override
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
