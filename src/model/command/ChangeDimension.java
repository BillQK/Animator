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

  /**
   * A Copy Constructor.
   *
   * @param cd ChangeDimension change dimension command
   * @param c  ACommand command
   */
  public ChangeDimension(ChangeDimension cd, ACommand c) {
    super(c);
    this.endH = cd.endH;
    this.endW = cd.endW;
  }

  @Override
  public void execute(double time) {
    double start = super.getStart();
    double end = super.getEnd();

    double currentW = shape.getWidth();
    double currentH = shape.getHeight();

    double rateOfChange = RateOfChange.findRate(time, start, end);

    double changeInW = (endW - currentW) * rateOfChange;
    double changeInH = (endH - currentH) * rateOfChange;

    double newW = currentW + changeInW;
    double newH = currentH + changeInH;

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
}
