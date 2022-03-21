package model.animation;

import java.awt.*;

import model.shape.AShape;
import model.utils.Time;

public class ChangeDimension extends AbstractAnimation {

  public ChangeDimension(AShape shape, AnimationType type,
                         double startTime, double endTime, Color endColor) {
    super(shape, type, startTime, endTime, endColor);
  }

  @Override
  public void animate(double time) {

  }

  @Override
  public String getEndsState() {
    return null;
  }
}
