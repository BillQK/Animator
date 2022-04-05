package model.command;

import java.awt.Color;

import model.shape.AShape;
import model.utils.Posn;

/**
 * This interface represents all the state of the command.
 * The interface include all the method to get the status of the command state from
 * a string to represent their whole state to getting their start and end time of a command.
 */
public interface ICommandsState {

  /**
   * Get the begin state before the command.
   *
   * @return a String with the shape's start time + name + start position + start width +
   *         start height + start color
   */
  String getBeginsState();

  /**
   * Get the state of the shape after the command.
   *
   * @return a String with the shape's end time + end position + end width +
   *         end height + end color
   */
  String getEndsState();

  /**
   * Get the state of the shape from the start to the end of the command.
   *
   * @return a String with the shape start state and the shape end state
   */
  String getState();

  /**
   * A method to get the shape (Rectangle or Ellipse).
   *
   * @return a String with the shape start state and the shape end state
   */
  AShape getTheShape();

  /**
   * Get the Commands type (Move/ChangeDimension/ChangeColor).
   *
   * @return a CommandType - the command that is being used on the shape.
   */
  CommandType getType();

  /**
   * Get the time when the command start.
   *
   * @return a double - the start time of the command
   */
  double getStart();

  /**
   * Get the time when the Animation end.
   *
   * @return a double - the end time of the command
   */
  double getEnd();

  /**
   * Get the old posn in the Shape.
   *
   * @return a Posn - the old posn of the shape
   */
  Posn getOldPosn();


  /**
   * Get the new posn in the Shape.
   *
   * @return a Posn - the new posn of the shape
   */
  Posn getNewPosn();


  /**
   * Get the old width in the Shape.
   *
   * @return a double - the old width of the shape
   */
  double getOldWidth();

  /**
   * Get the new width in the Shape.
   *
   * @return a double - the new width of the shape
   */
  double getNewWidth();

  /**
   * Get the old height in the Shape.
   *
   * @return a double - the old height of the shape
   */
  double getOldHeight();

  /**
   * Get the new height in the Shape.
   *
   * @return a double - the new height of the shape
   */
  double getNewHeight();

  /**
   * Get the old Color in the Shape.
   *
   * @return a Color - the old Color of the shape
   */
  Color getOldColor();

  /**
   * Get the new Color in the Shape.
   *
   * @return a Color - the new Color of the shape
   */
  Color getNewColor();


  /**
   * Return a new state of the given shape at a specific tick.
   *
   * @param time  a double - represent the time
   * @param shape a AShape - operating shape
   * @return a updated state copy of the Shape
   */
  AShape getShapeAtTick(double time, AShape shape);

  /**
   * Get the svg String of the command.
   *
   * @param tempo a double - tempo
   * @return a String
   */
  String getSVG(double tempo);
}
