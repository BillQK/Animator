package model.command;

import model.shape.AShape;

/**
 * This interface represents all the operations offered by the command.
 * The interface include method to execute a specific command on a shape
 * with the given current time.
 */
public interface ICommands extends ICommandsState, Comparable<ICommands> {
  /**
   * A method to execute the command on the shape to move/changeDimension/changeColor.
   *
   * @param time the current time of the animation
   */
  void execute(double time);

  /**
   * Method to get the Command String which get the command in text style.
   *
   * @return String - the command String
   */
  String getCommandString();

  /**
   * Set the shape of the command to the new given shape.
   *
   * @param s the new given shape.
   */
  void setShape(AShape s);

  /**
   * Get the shape of the command.
   *
   * @return AShape - the shape that have the command
   */
  AShape getShape();

}
