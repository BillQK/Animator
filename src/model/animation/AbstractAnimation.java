package model.animation;

import model.shape.AShape;
import model.utils.Time;

public abstract class AbstractAnimation implements IAnimations {
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
