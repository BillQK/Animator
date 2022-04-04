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

  /**
   * A method to execute the command on the shape to move/changeDimension/changeColor.
   *
   * @param time the current time of the animation
   */
  @Override
  public void execute(double time) {
    Color newColor = calculate(time);

    shape.setColor(newColor);
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
            + shape.getWidth()
            + " " + shape.getHeight()
            + " " + endColor.getRed()
            + " " + endColor.getGreen()
            + " " + endColor.getBlue() + " ";
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
            + "rgb" + "\" "
            + "from=\"(" + this.shape.getColor().getRed() + ","
            + this.shape.getColor().getGreen() + ","
            + this.shape.getColor().getBlue()
            + ")\" to=\"(" + endColor.getRed() + ","
            + endColor.getGreen() + ","
            + endColor.getBlue() + ")\" fill=\"freeze\" /> \n";

    return details;
  }

  /**
   * A method to calculate the new shape's color at the given time on the given shape.
   *
   * @param time the given time to know where the shape color is
   * @return a Color - new color at the time
   */
  private Color calculate(double time) {
    double start = super.getStart();
    double end = super.getEnd();

    int currentR = this.shape.getColor().getRed();
    int currentG = this.shape.getColor().getGreen();
    int currentB = this.shape.getColor().getBlue();

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

  /**
   * Get the old Color in the Shape.
   *
   * @return a Color - the old Color of the shape
   */
  @Override
  public Color getOldColor() {
    return this.shape.getColor();
  }

  /**
   * Get the new Color in the Shape.
   *
   * @return a Color - the new Color of the shape
   */
  @Override
  public Color getNewColor() {
    return endColor;
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
    shape.setColor(calculate(time));
    return shape;
  }

}
