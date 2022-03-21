package model.animation;

import java.awt.*;

import model.shape.AShape;
import model.utils.Time;

public class ChangeDimension extends AbstractAnimation {
  private final double endW;
  private final double endH;

  public ChangeDimension(AShape shape, AnimationType type,
                         double startTime, double endTime, double endW, double endH) {
    super(shape, type, startTime, endTime);
    this.endH = endH;
    this.endW = endW;
  }

  @Override
  public void animate(double time) {

  }

  @Override
  public String getEndsState() {
    return null;
  }
}
