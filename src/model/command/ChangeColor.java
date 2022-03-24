package model.command;

import java.awt.*;

import model.shape.AShape;
import model.utils.ArgumentsCheck;
import model.utils.RateOfChange;

/**
 * Represents the ChangeColor command class called on a shape.
 */
public class ChangeColor extends ACommand {
  private final Color endColor;

  public ChangeColor(AShape shape, double startTime, double endTime, Color endColor) {
    super(shape, CommandType.CHANGE_COLOR, startTime, endTime);
    ArgumentsCheck.colorRange(endColor.getRed(), endColor.getGreen(), endColor.getBlue());
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

    int newR = (int) (currentR + changeInRed);
    int newG = (int) (currentG + changeInGreen);
    int newB = (int) (currentB + changeInBlue);

    ArgumentsCheck.colorRange(newR, newG, newB);
    Color newColor = new Color(newR, newG, newB);

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
