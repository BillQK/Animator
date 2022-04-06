package model.command;

import model.shape.AShape;
import model.utils.RateOfChange;

/**
 * Represents the ChangeDimension command class called on a shape.
 */
public class ChangeDimension extends ACommand {
  private final double startW;
  private final double startH;
  private final double endW;
  private final double endH;

  /**
   * A constructor for ChangeDimension.
   *
   * @param shape     AShape - the shape to called the changeDimension command on
   * @param startTime the start time of the command
   * @param endTime   the end time of the command
   * @param startW    the given start width of the shape
   * @param startH    the given start height of the shape
   * @param endW      the given end width to change the shape's width to
   * @param endH      the given end height to change the shape's height to
   */
  public ChangeDimension(AShape shape, double startTime, double endTime,
                         double startW, double startH, double endW, double endH) {
    super(shape, CommandType.CHANGE_DIMENSION, startTime, endTime);
    this.startW = startW;
    this.startH = startH;
    this.endH = endH;
    this.endW = endW;
  }

  /**
   * A method to execute the command on the shape to move/changeDimension/changeColor.
   *
   * @param time the current time of the animation
   */
  @Override
  public void execute(double time) {
    double newW = calculateW(time);
    double newH = calculateH(time);


    this.shape.setWidth(newW);
    this.shape.setHeight(newH);
  }

  /**
   * Get the state of the shape after the command.
   *
   * @return a String with the shape's end time + end position + end width +
   * end height + end color
   */
  @Override
  public String getEndsState() {
    String a = "";
    a += endTime
            + " " + shape.getPosition().toString()
            + endW
            + " " + endH
            + " " + shape.getColor().getRed()
            + " " + shape.getColor().getGreen()
            + " " + shape.getColor().getBlue() + " ";
    return a;
  }

  /**
   * Get the svg String of the command.
   *
   * @param tempo a double - tempo
   * @return a String
   */
  @Override
  public String getSVG(double tempo) {
    double begin = (this.getStart() * 1000 / tempo);
    double end = (this.getEnd() * 1000 / tempo);
    double dur = end - begin;

    String details = "";

    details += "<animate attributeType=\"xml\" "
            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.shape.getSVGDstart() + "\" "
            + "from=\"" + this.startW
            + "\" to=\"" + this.endW + "\" fill=\"freeze\" /> \n";

    details += "<animate attributeType=\"xml\" "
            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.shape.getSVGDend() + "\" "
            + "from=\"" + this.startH
            + "\" to=\"" + this.endH + "\" fill=\"freeze\" />\n";

    return details;
  }

  /**
   * A method to calculate the new shape's width at the given time on the given shape.
   *
   * @param time the given time to know what the shape's width is
   * @return a double - new width at the time
   */
  private double calculateW(double time) {
    double start = super.getStart();
    double end = super.getEnd();

    double currentW = shape.getWidth();

    double rateOfChange = RateOfChange.findRate(time, start, end);

    double newW;
    if (rateOfChange == 0) {
      newW = this.shape.getWidth();
      return newW;
    }
    double changeInW = (endW - currentW) * rateOfChange;

    newW = currentW + changeInW;

    return newW;
  }

  /**
   * A method to calculate the new shape's height at the given time on the given shape.
   *
   * @param time the given time to know what the shape's height is
   * @return a double - new height at the time
   */
  private double calculateH(double time) {
    double start = super.getStart();
    double end = super.getEnd();

    double currentH = shape.getHeight();

    double rateOfChange = RateOfChange.findRate(time, start, end);
    double newH;

    if (rateOfChange == 0) {
      newH = this.shape.getHeight();
      return newH;
    }
    if (rateOfChange == -1) {
      newH = endH;
      return newH;
    }

    double changeInH = (endH - currentH) * rateOfChange;

    newH = currentH + changeInH;

    return newH;
  }

  /**
   * Get the old width in the Shape.
   *
   * @return a double - the old width of the shape
   */
  @Override
  public double getOldWidth() {
    return startW;
  }

  /**
   * Get the new width in the Shape.
   *
   * @return a double - the new width of the shape
   */
  @Override
  public double getNewWidth() {
    return endW;
  }

  /**
   * Get the old height in the Shape.
   *
   * @return a double - the old height of the shape
   */
  @Override
  public double getOldHeight() {
    return startH;
  }

  /**
   * Get the new height in the Shape.
   *
   * @return a double - the new height of the shape
   */
  @Override
  public double getNewHeight() {
    return endH;
  }

  /**
   * Return a new state of the given shape at a specific tick.
   *
   * @param time  a double - represent the time
   * @param shape a AShape - operating shape
   * @return a updated state copy of the Shape
   */
  @Override
  public AShape getShapeAtTick(double time, AShape shape) {

    shape.setWidth(calculateH(time));
    shape.setHeight(calculateH(time));
    return shape;
  }
}
