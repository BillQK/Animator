package model.animation;

import java.awt.*;

import model.shape.AShape;
import model.utils.ArgumentsCheck;
import model.utils.Posn;
import model.utils.Time;

public abstract class AbstractAnimation implements IAnimations {
  private final AnimationType type;
  private final double startTime;
  protected final double endTime;
  protected AShape shape;

  public AbstractAnimation(AShape shape,
                           AnimationType type,
                           double startTime,
                           double endTime,
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
  public abstract void animate(double time);

  @Override
  public String getBeginsState() {
    return shape.getName() + " "
            + shape.getTime().getStartTime() + " "
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
  public double getStart() {
    return startTime;
  }

  @Override
  public double getEnd() {
    return endTime;
  }
}
