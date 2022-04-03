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

  @Override
  public void execute(double time) {
    Posn newPosn = calculate(time, this.shape);

    this.shape.setPosn(newPosn);
  }

  @Override
  public String getSVG(double tempo) {
    double begin = (this.getStart() / tempo) * 1000;
    double end = (this.getEnd() / tempo) * 1000;
    double dur = end - begin;

    String details = "";

    details += "<animate attributeType=\"xml\" "
            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.shape.getSVGX() + "\" "
            + "from=\"" + this.shape.getPosition().getX()
            + "\" to=\"" + this.destination.getX() + "\" fill=\"freeze\" /> \n";

    details += "<animate attributeType=\"xml\" "
            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.shape.getSVGY() + "\" "
            + "from=\"" + this.shape.getPosition().getY()
            + "\" to=\"" + this.destination.getY() + "\" fill=\"freeze\" />\n";

    return details;
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


  private Posn calculate(double time, AShape shape) {
    double start = super.getStart();
    double end = super.getEnd();

    double currentX = shape.getPosition().getX();
    double currentY = shape.getPosition().getY();

    double destX = this.destination.getX();
    double destY = this.destination.getY();

    double rateOfChange = RateOfChange.findRate(time, start, end);

    double changeInX = (destX - currentX) * rateOfChange;
    double changeInY = (destY - currentY) * rateOfChange;

    Posn newPosn = new Posn(currentX + changeInX, currentY + changeInY);
    return newPosn;
  }

  public Posn getOldPosn() {
    return this.shape.getPosition();
  }

  public Posn getNewPosn() {
    return destination;
  }

  @Override
  public AShape getShapeAtTick(double time, AShape shape) {

    shape.setPosn(calculate(time, shape));
    return shape;
  }

}
