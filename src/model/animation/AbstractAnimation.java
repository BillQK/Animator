package model.animation;

import java.awt.*;

import model.shape.AShape;
import model.utils.ArgumentsCheck;
import model.utils.Posn;
import model.utils.Time;

public abstract class AbstractAnimation implements IAnimations {
  private final AnimationType type;
  private final int startTime;
  private final int endTime;
  private AShape shape;

  public AbstractAnimation(AShape shape,
                           AnimationType type,
                           int startTime,
                           int endTime,
                           Color endColor) {
    ArgumentsCheck.lessThanZero(startTime, endTime);
    if (endColor == null || type == null || shape == null) {
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
  public abstract void animate(Time time);

  @Override
  public String getBeginsState() {
    return shape.getTime().getStartTime()
            + shape.getName()
            + shape.getPosition().toString()
            + shape.getHeight()
            + shape.getWidth()
            + shape.getColor().toString();
  }

  @Override
  public abstract String getEndsState();

  @Override
  public String getState() {
    return this.getBeginsState() + this.getEndsState();
  }

  @Override
  public AShape getShape() {
    return shape.getTheShape();
  }

  @Override
  public Time getTime() {
    return new Time(this.shape.getTime());
  }

  @Override
  public AnimationType getType() {
    return this.type;
  }

  @Override
  public int getStart() {
    return startTime;
  }

  @Override
  public int getEnd() {
    return endTime;
  }
}
