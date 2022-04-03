package model.command;

import model.shape.AShape;
import model.utils.RateOfChange;

/**
 * Represents the ChangeDimension command class called on a shape.
 */
public class ChangeDimension extends ACommand {
  private final double endW;
  private final double endH;

  /**
   * A constructor for ChangeDimension.
   *
   * @param shape     AShape - the shape to called the changeDimension command on
   * @param startTime the start time of the command
   * @param endTime   the end time of the command
   * @param endW      the given end width to change the shape's width to
   * @param endH      the given end height to change the shape's height to
   */
  public ChangeDimension(AShape shape, double startTime, double endTime,
                         double endW, double endH) {
    super(shape, CommandType.CHANGE_DIMENSION, startTime, endTime);
    this.endH = endH;
    this.endW = endW;
  }

  @Override
  public void execute(double time) {
    double newW = calculateW(time);
    double newH = calculateH(time);

    this.shape.setWidth(newW);
    this.shape.setHeight(newH);
  }

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

  @Override
  public String getSVG(double tempo) {
    double begin = (this.getStart() / tempo) * 1000;
    double end = (this.getEnd() / tempo) * 1000;
    double dur = end - begin;

    String details = "";

    details += "<animate attributeType=\"xml\" "
            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.shape.getSVGDstart() + "\" "
            + "from=\"" + this.shape.getWidth()
            + "\" to=\"" + this.endW + "\" fill=\"freeze\" /> \n";

    details += "<animate attributeType=\"xml\" "
            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.shape.getSVGDend() + "\" "
            + "from=\"" + this.shape.getHeight()
            + "\" to=\"" + this.endH + "\" fill=\"freeze\" />\n";

    return details;
  }

  private double calculateW(double time) {
    double start = super.getStart();
    double end = super.getEnd();

    double currentW = shape.getWidth();

    double rateOfChange = RateOfChange.findRate(time, start, end);

    double changeInW = (endW - currentW) * rateOfChange;

    double newW = currentW + changeInW;

    return newW;
  }

  private double calculateH(double time) {
    double start = super.getStart();
    double end = super.getEnd();

    double currentH = shape.getHeight();

    double rateOfChange = RateOfChange.findRate(time, start, end);

    double changeInH = (endH - currentH) * rateOfChange;

    double newH = currentH + changeInH;

    return newH;
  }

  public double getOldWidth() {
    return this.shape.getWidth();
  }

  public double getNewWidth() {
    return endW;
  }

  public double getOldHeight() {
    return this.shape.getHeight();
  }

  public double getNewHeight() {
    return endH;
  }

  @Override
  public AShape getShapeAtTick(double time, AShape shape) {

    shape.setWidth(calculateH(time));
    shape.setHeight(calculateH(time));
    return shape;
  }
}
