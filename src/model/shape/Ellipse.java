package model.shape;

import java.awt.*;

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

  @Override
  public String getSVG() {
    return "<ellipse id=\"" + this.getName() + "\" cx=\"" + this.getPosition().getX() + "\" cy=\""
            + this.getPosition().getY() + "\" rx=\"" + this.getWidth() + "\" ry=\""
            + this.getHeight() + "\" fill=\"rgb(" + this.getColor().getRed() + ","
            + this.getColor().getGreen() + "," + this.getColor().getBlue()
            + ")\" visibility=\"visible\" >\n";
  }

  @Override
  public String getSVGDstart() {
    return "xR";
  }

  @Override
  public String getSVGDend() {
    return "yR";
  }

  @Override
  public String getSVGX() {
    return "cx";
  }

  @Override
  public String getSVGY() {
    return "cy";
  }

  @Override
  public String getSVGEndShape() {
    return "</ellipse>";
  }

}
