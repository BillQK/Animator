package model.command;

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

  String getCommandString();

}
