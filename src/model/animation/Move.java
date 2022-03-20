package model.animation;

import java.awt.*;

import model.shape.AShape;
import model.utils.Posn;

public class Move extends AbstractAnimation {
  private final Posn original;
  private final Posn destination;

  public Move(AShape shape, int startTime, int endTime, Color endColor, Posn original, Posn destination) {
    super(shape, AnimationType.MOVE, startTime, endTime, endColor);
    this.original = original;
    this.destination = destination;
  }

  @Override
  public void animate(double time) {
    AShape s = super.getShape();
    int start = super.getStart();
    int end = super.getEnd();

    double currentX = this.original.getX();
    double currentY = this.original.getY();

    double destX = this.destination.getX();
    double destY = this.destination.getY();

    double rateOfChange = (time - start) / (double) (end - start);

    double changeInX = Math.abs(destX - currentX) * rateOfChange;

    double changeInY = Math.abs(destY - currentY) * rateOfChange;

    Posn newPosn = new Posn((int) (currentX + changeInX), (int) ((int) currentY + changeInY));

    s.setPosn(newPosn);
  }

  @Override
  public String getEndsState() {
    return null;
  }
}
