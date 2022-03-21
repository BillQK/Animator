package model.command;

import java.awt.*;

import model.shape.AShape;
import model.utils.RateOfChange;

public class ChangeColor extends ACommand {
  private final Color endColor;

  public ChangeColor(AShape shape, double startTime, double endTime, Color endColor) {
    super(shape, CommandType.CHANGE_COLOR, startTime, endTime);
    this.endColor = endColor;
  }

  @Override
  public void execute(double time) {
    double start = super.getStart();
    double end = super.getEnd();

    int currentR = shape.getColor().getRed();
    int currentG = shape.getColor().getGreen();
    int currentB = shape.getColor().getBlue();

    int destR = endColor.getRed();
    int destG = endColor.getGreen();
    int destB = endColor.getBlue();

    double rateOfChange = RateOfChange.findRate(time, start, end);

    double changeInRed = (destR - currentR) * rateOfChange;
    double changeInGreen = (destG - currentG) * rateOfChange;
    double changeInBlue = (destB - currentB) * rateOfChange;

    Color newColor = new Color((int) (currentR + changeInRed),
            (int) (currentG + changeInGreen),
            (int) (currentB + changeInBlue));

    shape.setColor(newColor);
  }

  @Override
  public String getEndsState() {
    String a = "";
    a += endTime
            + " " + shape.getPosition().toString()
            + shape.getWidth()
            + " " + shape.getHeight()
            + " " + endColor.getRed()
            + " " + endColor.getGreen()
            + " " + endColor.getBlue() + " ";
    return a;
  }
}
