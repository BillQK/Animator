package model.shape;

import java.awt.Color;

import model.utils.Time;

/**
 * This class represents the specific Ellipse shape which have an Ellipse shapetype, color,
 * positions and dimensions.
 */
public class Ellipse extends AShape {

  /**
   * A constructor for Ellipse.
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
  public Ellipse(String name, model.shape.Shape type, Color col, double posX, double posY,
                 double width, double height, Time time) {
    super(name, type, col, posX, posY, width, height, time);
  }

  /**
   * A method to get the specific new shape.
   *
   * @return the specific shape
   */
  public Ellipse getTheShape() {
    return new Ellipse(this.getName(), this.getType(), this.getColor(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getWidth(), this.getHeight(), this.getTime());
  }

  /**
   * A method to get the shape type (Rectangle or Ellipse).
   *
   * @return an Enum - the shape's type
   */
  public model.shape.Shape getType() {
    return Shape.ELLIPSE;
  }

  /**
   * Get a textual description of the shape images.
   *
   * @return a String - SVG shape description
   */
  public String getSVG() {
    return "<ellipse id=\"" + this.getName() + "\" cx=\"" + this.getPosition().getX() + "\" cy=\""
            + this.getPosition().getY() + "\" rx=\"" + this.getWidth() + "\" ry=\""
            + this.getHeight() + "\" fill=\"rgb(" + this.getColor().getRed() + ","
            + this.getColor().getGreen() + "," + this.getColor().getBlue()
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
    return "xR";
  }

  /**
   * Get a textual description of the shape's height/yRadius dimension.
   *
   * @return A String - SVG shape height/yRadius
   */
  public String getSVGDend() {
    return "yR";
  }

  /**
   * Get a textual description of the shape's x/cx position.
   *
   * @return A String - SVG shape x/cx position
   */
  public String getSVGX() {
    return "cx";
  }

  /**
   * Get a textual description of the shape's y/cy position.
   *
   * @return A String - SVG shape y/cy position
   */
  public String getSVGY() {
    return "cy";
  }

  /**
   * Get a textual description of the shape's type.
   *
   * @return A String - SVG shape type
   */
  public String getSVGEndShape() {
    return "</ellipse>";
  }

  @Override
  public String getCommand() {
    return "oval name " + this.getName() + " center-x " + this.getPosition().getX()
            + " center-y " + this.getPosition().getY() + " x-radius " + this.getWidth()
            + " y-radius " + this.getHeight() + " color " + this.getColor().getRed() + " "
            + this.getColor().getGreen() + " " + this.getColor().getBlue() + " from "
            + (int) this.getTime().getStartTime() + " to " + (int) this.getTime().getEndTime();
  }

}
