package model.animation;

import java.awt.*;

import model.shape.AShape;
import model.utils.Posn;
import model.utils.Time;

public class Move extends AbstractAnimation {
  private final Posn original;
  private final Posn destination;

  public Move(AShape shape, AnimationType type, int startTime, int endTime, Color endColor) {
    super(shape, type, startTime, endTime, endColor);

  }

  @Override
  public void animate(Time time) {

  }

  @Override
  public String getEndsState() {
    return null;
  }
}
