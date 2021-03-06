package model.shape;

import java.awt.Color;
import java.util.Objects;

import model.command.ICommandsState;
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
  private final Time time;
  private Color col;
  private double w;
  private double h;
  private Posn pos;

  /**
   * A constructor for Ashape.
   *
   * @param name   the name of the shape
   * @param type   the type of the shape (Rectangle or Ellipse)
   * @param col    the color of the shape
   * @param posX   the x position of the shape
   * @param posY   the y position of the shape
   * @param width  the width of the shape
   * @param height the height of the shape
   * @param time   the Time of the shape
   */
  public AShape(String name, Shape type, Color col, double posX, double posY,
                double width, double height, Time time) {
    if (name == null || type == null || col == null || time == null) {
      throw new IllegalArgumentException("The given arguments cannot be null/empty/negative");
    }
    ArgumentsCheck.lessThanZero(width, height);
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
   * A method to get the specific new shape.
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
    return new Color(this.col.getRed(),
            this.col.getGreen(), this.col.getBlue());
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
   * A method to get the width of the shape.
   *
   * @return a double - the shape's width
   */
  public double getWidth() {
    return this.w;
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
   * A method to get the height of the shape.
   *
   * @return a double - the shape's height
   */
  public double getHeight() {
    return this.h;
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
   * A method to get the time of the shape.
   *
   * @return a Time - the shape's time
   */
  public Time getTime() {
    return this.time;
  }

  /**
   * A method to set the position of the shape to a new given position.
   *
   * @param pos a Posn - the new given Posn
   */
  public void setPosn(Posn pos) {
    if (pos == null) {
      throw new IllegalArgumentException("Position cannot be null");
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

  /**
   * Get a textual description of the shape images.
   *
   * @return a String - SVG shape description
   */
  public abstract String getSVG();

  /**
   * Get a textual description of the plus shape images second rectangle.
   *
   * @return a String - SVG shape description
   */
  public abstract String getSVG2ndRec();

  /**
   * Get a textual description of the shape's width/xRadius dimension.
   *
   * @return A String - SVG shape width/xRadius
   */
  public abstract String getSVGDstart();

  /**
   * Get a textual description of the shape's height/yRadius dimension.
   *
   * @return A String - SVG shape height/yRadius
   */
  public abstract String getSVGDend();

  /**
   * Get a textual description of the shape's x/cx position.
   *
   * @return A String - SVG shape x/cx position
   */
  public abstract String getSVGX();

  /**
   * Get a textual description of the shape's y/cy position.
   *
   * @return A String - SVG shape y/cy position
   */
  public abstract String getSVGY();

  /**
   * Get a textual description of the shape's type.
   *
   * @return A String - SVG shape type
   */
  public abstract String getSVGEndShape();

  /**
   * Update the copy shape based on the given command.
   *
   * @param com the given command
   * @return the updated copy shape based on the command
   */
  public AShape updateShape(ICommandsState com) {
    AShape shape = this.getTheShape();
    switch (com.getType()) {
      case MOVE:
        shape.setPosn(com.getNewPosn());
        return shape;
      case CHANGE_DIMENSION:
        shape.setHeight(com.getNewHeight());
        shape.setWidth(com.getNewWidth());
        return shape;
      case CHANGE_COLOR:
        shape.setColor(com.getNewColor());
        return shape;
      default:
        return shape;
    }
  }

  public abstract String getCommand();
}
