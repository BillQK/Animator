package model.command;

import model.shape.AShape;

/**
 * Represent an emptyCommand class which represent a constant state of the shape which the shape
 * doesn't do anything.
 */
public class EmptyCommand extends ACommand {

  /**
   * A constructor for ACommand class.
   *
   * @param shape     the given shape to call the command on
   * @param type      the type of the command
   * @param startTime the start time of the command
   * @param endTime   the end time of the command
   */
  public EmptyCommand(AShape shape, CommandType type, double startTime, double endTime) {
    super(shape, type, startTime, endTime);
  }

  /**
   * A Copy Constructor.
   *
   * @param c ACommand command
   */
  public EmptyCommand(ACommand c) {
    super(c);
  }

  /**
   * A method to execute the command on the shape to move/changeDimension/changeColor.
   *
   * @param time the current time of the animation
   */
  @Override
  public void execute(double time) {
    // nothing
  }

  @Override
  public String getCommandString() {
    return "";
  }

  /**
   * Get the state of the shape after the command.
   *
   * @return a String with the shape's end time + end position + end width +
   *         end height + end color
   */
  @Override
  public String getEndsState() {
    String a = "";
    a += endTime
            + " " + shape.getPosition().toString()
            + " " + shape.getWidth()
            + " " + shape.getHeight()
            + " " + shape.getColor().getRed()
            + " " + shape.getColor().getGreen()
            + " " + shape.getColor().getBlue() + " ";
    return a;
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
    return shape;
  }

  /**
   * Get the svg String of the command.
   *
   * @param tempo a double - tempo
   * @return a String
   */
  @Override
  public String getSVG(double tempo) {
    return "";
  }
}
