package model.command;

import model.shape.AShape;
import model.utils.Posn;
import model.utils.RateOfChange;

/**
 * Represents the Move command class called on a shape.
 */
public class Move extends ACommand {
  private final Posn origin;
  private final Posn destination;

  /**
   * A constructor for Move.
   *  @param shape       AShape - the shape to called the move command on
   * @param startTime   the start time of the command
   * @param endTime     the end time of the command
   * @param origin
   * @param destination the destination position that the shape need to move to
   */
  public Move(AShape shape, double startTime, double endTime, Posn origin, Posn destination) {
    super(shape, CommandType.MOVE, startTime, endTime);
    this.origin = origin;
    this.destination = destination;
  }

  /**
   * A method to execute the command on the shape to move/changeDimension/changeColor.
   *
   * @param time the current time of the animation
   */
  @Override
  public void execute(double time) {
    Posn newPosn = calculate(time, this.shape);

    this.shape.setPosn(newPosn);
  }

  /**
   * Get the svg String of the command.
   *
   * @param tempo a double - tempo
   * @return a String
   */
  @Override
  public String getSVG(double tempo) {
    double begin = (this.getStart() * 1000 / tempo);
    double end = (this.getEnd() * 1000 / tempo);
    double dur = end - begin;
    String details = "";

    details += "<animate attributeType=\"xml\" "
            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.shape.getSVGX() + "\" "
            + "from=\"" + this.getOldPosn().getX()
            + "\" to=\"" + this.destination.getX() + "\" fill=\"freeze\" /> \n";

    details += "<animate attributeType=\"xml\" "
            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.shape.getSVGY() + "\" "
            + "from=\"" + this.getOldPosn().getY()
            + "\" to=\"" + this.destination.getY() + "\" fill=\"freeze\" />\n";

    return details;
  }

  /**
   * Get the state of the shape after the command.
   *
   * @return a String with the shape's end time + end position + end width +
   *         end height + end color
   */
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

  /**
   * A method to calculate the new shape's position at the given time on the given shape.
   *
   * @param time  the given time to know where the shape at
   * @param shape the given shape to return a new position
   * @return a Posn - new position at the time
   */
  private Posn calculate(double time, AShape shape) {
    double start = super.getStart();
    double end = super.getEnd();

    double currentX = shape.getPosition().getX();
    double currentY = shape.getPosition().getY();

    double destX = this.destination.getX();
    double destY = this.destination.getY();

    double rateOfChange = RateOfChange.findRate(time, start, end);
    
    Posn newPosn; 
    if (rateOfChange == 0) {
      newPosn = destination; 
      return newPosn;
    }

    double changeInX = (destX - currentX) * rateOfChange;
    double changeInY = (destY - currentY) * rateOfChange;

    newPosn = new Posn(currentX + changeInX, currentY + changeInY);
    return newPosn;
  }

  /**
   * Get the old posn in the Shape.
   *
   * @return a Posn - the old posn of the shape
   */
  @Override
  public Posn getOldPosn() {
    return origin;
  }

  /**
   * Get the new posn in the Shape.
   *
   * @return a Posn - the new posn of the shape
   */
  @Override
  public Posn getNewPosn() {
    return destination;
  }

  /**
   * Return a new state of the given shape at a specific tick.
   *
   * @param time  a double - represent the time
   * @param shape a AShape - operating shape
   * @return a updated state copy of the Shape
   */
  @Override
  public AShape getShapeAtTick(double time, AShape shape) {

    shape.setPosn(calculate(time, shape));
    return shape;
  }

}
