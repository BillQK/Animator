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
  protected final AShape shape;

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
  public abstract void execute(double time);

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

  @Override
  public abstract String getEndsState();

  @Override
  public String getState() {
    return this.getBeginsState() + this.getEndsState();
  }

  @Override
  public AShape getTheShape() {
    return shape.getTheShape();
  }

  @Override
  public CommandType getType() {
    return this.type;
  }

  @Override
  public double getStart() {
    return startTime;
  }

  @Override
  public double getEnd() {
    return endTime;
  }

  @Override
  public Posn getPosn() {
    return shape.getPosition();
  }

  @Override
  public double getHeight() {
    return shape.getHeight();
  }

  @Override
  public double getWidth() {
    return shape.getWidth();
  }

  @Override
  public Color getColor() {
    return shape.getColor();
  }
}
