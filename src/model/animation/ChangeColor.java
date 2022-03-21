package model.animation;

import java.awt.*;

import model.shape.AShape;

public class ChangeColor extends AbstractAnimation {
  private final Color endColor;

  public ChangeColor(AShape shape, AnimationType type, double startTime, double endTime, Color endColor) {
    super(shape, type, startTime, endTime);
    this.endColor = endColor;
  }

  @Override
  public void animate(double time) {

  }

  @Override
  public String getEndsState() {
    return null;
  }
}
