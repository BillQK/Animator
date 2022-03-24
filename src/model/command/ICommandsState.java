package model.command;

import model.shape.AShape;

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
   * start height + start color
   */
  String getBeginsState();

  /**
   * Get the state of the shape after the command.
   *
   * @return a String with the shape's end time + end position + end width +
   * end height + end color
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

}
