package model.animation;

import java.awt.*;

import model.shape.AShape;
import model.utils.Posn;
import model.utils.RateOfChange;

public class Move extends AbstractAnimation {
  private final Posn original;
  private final Posn destination;

  public Move(AShape shape, double startTime, double endTime,
              Color endColor, Posn original, Posn destination) {
    super(shape, AnimationType.MOVE, startTime, endTime, endColor);
    this.original = original;
    this.destination = destination;
  }

  @Override
  public void animate(double time) {
    AShape s = super.getShape();
    double start = super.getStart();
    double end = super.getEnd();

    double currentX = this.original.getX();
    double currentY = this.original.getY();

    double destX = this.destination.getX();
    double destY = this.destination.getY();

    double rateOfChange = RateOfChange.findRate(time, start, end);

    double changeInX = (destX - currentX) * rateOfChange;

    double changeInY = (destY - currentY) * rateOfChange;

    Posn newPosn = new Posn(currentX + changeInX, currentY + changeInY);

    s.setPosn(newPosn);
  }

  @Override
  public String getEndsState() {
    String a = "";
    a += endTime
            + " " + shape.getPosition().toString()
            + shape.getWidth()
            + " " + shape.getHeight()
            + " " + shape.getColor().getRed()
            + " " + shape.getColor().getGreen()
            + " " + shape.getColor().getBlue() + " ";
    return a;
  }
}
