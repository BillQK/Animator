package model.command;

import model.shape.AShape;
import model.utils.ArgumentsCheck;

public abstract class AbstractCommand implements ICommands {
  private final CommandType type;
  private final double startTime;
  protected final double endTime;
  protected AShape shape;

  public AbstractCommand(AShape shape,
                         CommandType type,
                         double startTime,
                         double endTime) {
    ArgumentsCheck.lessThanZero(startTime, endTime);
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

  @Override
  public abstract void execute(double time);

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
}
