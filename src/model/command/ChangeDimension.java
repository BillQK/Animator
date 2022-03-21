package model.command;

import model.shape.AShape;
import model.utils.RateOfChange;

public class ChangeDimension extends ACommand {
  private final double endW;
  private final double endH;

  public ChangeDimension(AShape shape, CommandType type,
                         double startTime, double endTime, double endW, double endH) {
    super(shape, type, startTime, endTime);
    this.endH = endH;
    this.endW = endW;
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
