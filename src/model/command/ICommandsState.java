package model.command;

import model.shape.AShape;

public interface ICommandsState {
  /**
   * Get the begin state before the animation.
   * @return a String with the shape's start time + name + start position + start height +
   * start width + start color
   */
  String getBeginsState();

  /**
   * Get the state of the shape after the animation.
   * @return a String with the shape's end time + name + end position + end height +
   * end width + end color
   */
  String getEndsState();

  /**
   * Get the state of the shape from the start to the end of the animation.
   * @return a String with the shape start state and the shape end state
   */
  String getState();

  /**
   * Get the state of the shape from the start to the end of the animation.
   * @return a String with the shape start state and the shape end state
   */
  AShape getTheShape();

  /**
   * Get the Animation type.
   * @return the animation that is being used on the shape.
   */
  CommandType getType();

  /**
   * Get the time when the Animation start.
   * @return the start time of the animation
   */
  double getStart();

  /**
   * Get the time when the Animation end.
   * @return the end time of the animation
   */
  double getEnd();

}
