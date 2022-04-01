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

  /**
   * A constructor for ChangeColor class.
   *
   * @param shape     the given shape
   * @param startTime the start time of command
   * @param endTime   the end time of command
   * @param endColor  the destination color
   * @throws IllegalArgumentException if arguments outside of range
   */
  public ChangeColor(AShape shape, double startTime, double endTime, Color endColor) {
    super(shape, CommandType.CHANGE_COLOR, startTime, endTime);
    ArgumentsCheck.colorRange(endColor.getRed(), endColor.getGreen(), endColor.getBlue());
    this.endColor = endColor;
  }

  @Override
  public void execute(double time) {
    Color newColor = calculate(time);

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

  @Override
  public AShape getShapeAtTick(double time) {

    Color newColor = calculate(time);

    AShape s = this.getTheShape();
    s.setColor(newColor);
    return s;
  }

  @Override
  public String getSVG(double tempo) {
//    double begin = (this.getStart() / tempo) * 1000;
//    double end = (this.getEnd() / tempo) * 1000;
//    double dur = end - begin;
//
//    String details = "";
//
//    details += "<animate attributeType=\"xml\" "
//            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
//            + "rx" + "\" "
//            + "from=\"" + this.shape.getWidth()
//            + "\" to=\"" + this.endW + "\" fill=\"freeze\" /> \n";
//
//    details += "<animate attributeType=\"xml\" "
//            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
//            + "ry" + "\" "
//            + "from=\"" + this.shape.getHeight()
//            + "\" to=\"" + this.endH + "\" fill=\"freeze\" />\n";
//
//    return details;
    return "";
  }

  private Color calculate(double time) {
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
    return newColor;
  }
}
