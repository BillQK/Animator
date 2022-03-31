package model.command;

import model.shape.AShape;
import model.utils.Posn;
import model.utils.RateOfChange;

/**
 * Represents the Move command class called on a shape.
 */
public class Move extends ACommand {
  private final Posn destination;

  /**
   * A constructor for Move.
   *
   * @param shape       AShape - the shape to called the move command on
   * @param startTime   the start time of the command
   * @param endTime     the end time of the command
   * @param destination the destination position that the shape need to move to
   */
  public Move(AShape shape, double startTime, double endTime, Posn destination) {
    super(shape, CommandType.MOVE, startTime, endTime);
    this.destination = destination;
  }

  /**
   * A Copy Constructor.
   *
   * @param cm Move move command
   * @param c  ACommand command
   */
  public Move(Move cm, ACommand c) {
    super(c);
    this.destination = cm.destination;
  }

  @Override
  public void execute(double time) {
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
