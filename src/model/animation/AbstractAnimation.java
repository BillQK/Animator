package model.animation;

import java.awt.*;

import model.shape.AShape;
import model.utils.ArgumentsCheck;
import model.utils.Time;

public abstract class AbstractAnimation implements IAnimations {
  private final AShape shape;
  private final AnimationType type;
  private final int startTime;
  private final int endTime;
  private final int endW;
  private final int endH;
  private final int endX;
  private final int endY;
  private final Color endColor;

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
    this.startTime = startTime;
    this.endTime = endTime;
    this.endW = endW;
    this.endH = endH;
    this.endX = endX;
    this.endY = endY;
    this.endColor = endColor;
  }

  @Override
  public void animate(Time time) {

  }

  @Override
  public String getBeginsState() {
    return null;
  }

  @Override
  public String getEndsState() {
    return null;
  }

  @Override
  public String getState() {
    return null;
  }

  @Override
  public AShape getShape() {
    return null;
  }

  @Override
  public Time getTime() {
    return null;
  }

  @Override
  public AnimationType getType() {
    return null;
  }

  @Override
  public int getStart() {
    return 0;
  }

  @Override
  public int getEnd() {
    return 0;
  }
}
