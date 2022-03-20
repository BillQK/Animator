package model.animation;

import java.awt.*;

import model.shape.AShape;
import model.utils.Time;

public class ChangeColor extends AbstractAnimation {
  public ChangeColor(AShape shape, AnimationType type, int startTime, int endTime,
                     int endW, int endH, int endX, int endY, Color endColor) {
    super(shape, type, startTime, endTime, endW, endH, endX, endY, endColor);
  }

  @Override
  public void animate(Time time) {

  }
}
