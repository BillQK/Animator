package model.command;

import model.shape.AShape;

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

  @Override
  public void execute(double time) {
    // nothing
  }

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

  @Override
  public AShape getShapeAtTick(double time) {
    return shape.getTheShape();
  }

  @Override
  public String getSVG(double tempo) {
    return "";
  }
}
