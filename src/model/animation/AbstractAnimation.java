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
  private final int endW;
  private final int endH;
  private final int endX;
  private final int endY;
  private final Color endColor;
  private AShape shape;

  public AbstractAnimation(AShape shape,
                           AnimationType type,
                           int startTime,
                           int endTime,
                           int endW,
                           int endH,
                           int endX,
                           int endY,
                           Color endColor) {
    ArgumentsCheck.lessThanZero(endH, endW, endX, endY, startTime, endTime);
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

    this.endW = endW;
    this.endH = endH;
    this.endX = endX;
    this.endY = endY;
    this.endColor = endColor;
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
  public String getEndsState() {
    return shape.getTime().getEndTime()
            + shape.getName()
            + new Posn(endX, endY)
            + endH
            + endW
            + endColor.toString();
  }

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
    return new Time(this.getTime());
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
