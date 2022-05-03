package model.shape;

import java.awt.Color;

import model.utils.Time;

/**
 * This class represents the specific Rectangle shape which have a Rectangle shapetype, color,
 * positions and dimensions.
 */
public class Rectangle extends AShape {

  /**
   * A constructor for Rectangle.
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
  public Rectangle(String name, Shape type, Color col, double posX, double posY,
                   double width, double height, Time time) {
    super(name, type, col, posX, posY, width, height, time);
  }

  /**
   * A method to get the specific new shape.
   *
   * @return the specific shape
   */
  public Rectangle getTheShape() {
    return new Rectangle(this.getName(), this.getType(), this.getColor(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getWidth(), this.getHeight(), this.getTime());
  }

  /**
   * A method to get the shape type (Rectangle or Ellipse).
   *
   * @return an Enum - the shape's type
   */
  public Shape getType() {
    return Shape.RECTANGLE;
  }

  /**
   * Get a textual description of the shape images.
   *
   * @return a String - SVG shape description
   */
  public String getSVG() {
    return "<rect id=\"" + this.getName() + "\" x=\"" + this.getPosition().getX()
            + "\" y=\"" + this.getPosition().getY() + "\" width=\"" + this.getWidth()
            + "\" height=\"" + this.getHeight() + "\" fill=\"rgb(" + this.getColor().getRed()
            + "," + this.getColor().getGreen() + "," + this.getColor().getBlue()
            + ")\" visibility=\"visible\" >\n";
  }

  /**
   * Get a textual description of the plus shape images second rectangle.
   *
   * @return a String - SVG shape description
   */
  public String getSVG2ndRec() {
    throw new UnsupportedOperationException("This class doesn't support this method");
  }

  /**
   * Get a textual description of the shape's width/xRadius dimension.
   *
   * @return A String - SVG shape width/xRadius
   */
  public String getSVGDstart() {
    return "width";
  }

  /**
   * Get a textual description of the shape's height/yRadius dimension.
   *
   * @return A String - SVG shape height/yRadius
   */
  public String getSVGDend() {
    return "height";
  }

  /**
   * Get a textual description of the shape's x/cx position.
   *
   * @return A String - SVG shape x/cx position
   */
  public String getSVGX() {
    return "x";
  }

  /**
   * Get a textual description of the shape's y/cy position.
   *
   * @return A String - SVG shape y/cy position
   */
  public String getSVGY() {
    return "y";
  }

  /**
   * Get a textual description of the shape's type.
   *
   * @return A String - SVG shape type
   */
  public String getSVGEndShape() {
    return "</rect>";
  }

  // rectangle name R min-x 200 min-y 200 width 50 height 100 color 1
  @Override
  public String getCommand() {
    return "rectangle name " + this.getName() + " min-x " + this.getPosition().getX()
            + " min-y " + this.getPosition().getY() + " width " + this.getWidth() + " height "
            + this.getHeight() + " color " + (double) this.getColor().getRed() / 10 + " "
            + (double) this.getColor().getGreen() / 10 + " "
            + (double) this.getColor().getBlue() / 10 + " from "
            + (int) this.getTime().getStartTime() + " to " + (int) this.getTime().getEndTime();
  }

}
