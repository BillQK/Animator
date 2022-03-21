package model.animation;

import java.awt.*;

import model.shape.AShape;
import model.utils.Posn;
import model.utils.RateOfChange;

public class Move extends AbstractAnimation {
  private final Posn destination;

  public Move(AShape shape, double startTime, double endTime, Posn original, Posn destination) {
    super(shape, AnimationType.MOVE, startTime, endTime);
    this.destination = destination;
  }

  @Override
  public void animate(double time) {
    double start = super.getStart();
    double end = super.getEnd();

    double currentX = this.shape.getPosition().getX();
    double currentY = this.shape.getPosition().getY();

    double destX = this.destination.getX();
    double destY = this.destination.getY();

    double rateOfChange = RateOfChange.findRate(time, start, end);

    double changeInX = (destX - currentX) * rateOfChange;
    double changeInY = (destY - currentY) * rateOfChange;

    Posn newPosn = new Posn(currentX + changeInX, currentY + changeInY);

    this.shape.setPosn(newPosn);
  }

  @Override
  public String getEndsState() {
    String a = "";
    a += endTime
            + " " + destination.toString()
            + shape.getWidth()
            + " " + shape.getHeight()
            + " " + shape.getColor().getRed()
            + " " + shape.getColor().getGreen()
            + " " + shape.getColor().getBlue() + " ";
    return a;
  }
}
