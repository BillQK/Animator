package model.command;

import java.awt.*;

import model.shape.AShape;
import model.utils.ArgumentsCheck;
import model.utils.Posn;

/**
 * Represents an Abstract command class which is being implemented
 * by Move, ChangeDimension and ChangeColor command class.
 */
public abstract class ACommand implements ICommands {
  private final CommandType type;
  private final double startTime;
  protected final double endTime;
  protected AShape shape;

  /**
   * A constructor for ACommand class.
   *
   * @param shape     the given shape to call the command on
   * @param type      the type of the command
   * @param startTime the start time of the command
   * @param endTime   the end time of the command
   */
  public ACommand(AShape shape,
                  CommandType type,
                  double startTime,
                  double endTime) {
    ArgumentsCheck.lessThanZero(startTime, endTime);
    if (startTime > endTime) {
      throw new IllegalArgumentException("Invalid time");
    }
    if (type == null || shape == null) {
      throw new IllegalArgumentException("Invalid Arguments: Cannot be null");
    }
    this.shape = shape;
    this.type = type;
    if (shape.getTime().getStartTime() < startTime || shape.getTime().getEndTime() > endTime) {
      this.startTime = startTime;
      this.endTime = endTime;
    } else {
      throw new IllegalArgumentException("Time out of AShape Range");
    }
  }

  /**
   * A Copy Constructor.
   *
   * @param c ACommand command
   */
  public ACommand(ACommand c) {
    this.type = c.type;
    this.startTime = c.startTime;
    this.endTime = c.endTime;
    this.shape = c.shape;
  }


  @Override
  public int compareTo(ICommands o) {
    if (startTime > o.getStart()) {
      return 1;
    } else if (startTime == o.getStart()) {
      return Double.compare(endTime, o.getEnd());
    } else {
      return -1;
    }
  }

  @Override
  public abstract void execute(double time);

  @Override
  public void setShape(AShape s) {
    this.shape = s;
  }

  public AShape getShape() {
    return this.shape;
  }

  /**
   * Get the begin state before the command.
   *
   * @return a String with the shape's start time + name + start position + start width +
   * start height + start color
   */
  @Override
  public String getBeginsState() {
    return shape.getName() + " "
            + startTime + " "
            + shape.getPosition().toString()
            + shape.getWidth() + " "
            + shape.getHeight() + " "
            + shape.getColor().getRed() + " "
            + shape.getColor().getGreen() + " "
            + shape.getColor().getBlue() + " ";
  }

  /**
   * Get the state of the shape after the command.
   *
   * @return a String with the shape's end time + end position + end width +
   * end height + end color
   */
  @Override
  public abstract String getEndsState();

  /**
   * Get the state of the shape from the start to the end of the command.
   *
   * @return a String with the shape start state and the shape end state
   */
  @Override
  public String getState() {
    return this.getBeginsState() + this.getEndsState();
  }

  /**
   * A method to get the shape (Rectangle or Ellipse).
   *
   * @return a String with the shape start state and the shape end state
   */
  @Override
  public AShape getTheShape() {
    return shape.getTheShape();
  }

  /**
   * Get the Commands type (Move/ChangeDimension/ChangeColor).
   *
   * @return a CommandType - the command that is being used on the shape.
   */
  @Override
  public CommandType getType() {
    return this.type;
  }

  /**
   * Get the time when the command start.
   *
   * @return a double - the start time of the command
   */
  @Override
  public double getStart() {
    return startTime;
  }

  /**
   * Get the time when the Animation end.
   *
   * @return a double - the end time of the command
   */
  @Override
  public double getEnd() {
    return endTime;
  }

  /**
   * Get the old posn in the Shape.
   *
   * @return a Posn - the old posn of the shape
   */
  @Override
  public Posn getOldPosn() {
    return null;
  }

  /**
   * Get the new posn in the Shape.
   *
   * @return a Posn - the new posn of the shape
   */
  @Override
  public Posn getNewPosn() {
    return null;
  }

  /**
   * Get the old width in the Shape.
   *
   * @return a double - the old width of the shape
   */
  @Override
  public double getOldWidth() {
    return -1;
  }

  /**
   * Get the new width in the Shape.
   *
   * @return a double - the new width of the shape
   */
  @Override
  public double getNewWidth() {
    return -1;
  }

  /**
   * Get the old height in the Shape.
   *
   * @return a double - the old height of the shape
   */
  @Override
  public double getOldHeight() {
    return -1;
  }

  /**
   * Get the new height in the Shape.
   *
   * @return a double - the new height of the shape
   */
  @Override
  public double getNewHeight() {
    return -1;
  }

  /**
   * Get the old Color in the Shape.
   *
   * @return a Color - the old Color of the shape
   */
  @Override
  public Color getOldColor() {
    return null;
  }

  /**
   * Get the new Color in the Shape.
   *
   * @return a Color - the new Color of the shape
   */
  @Override
  public Color getNewColor() {
    return null;
  }

  /**
   * Return a new state of the given shape at a specific tick.
   *
   * @param time  a double - represent the time
   * @param shape a AShape - operating shape
   * @return a updated state copy of the Shape
   */
  @Override
  public abstract AShape getShapeAtTick(double time, AShape shape);

}
