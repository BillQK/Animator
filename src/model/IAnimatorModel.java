package model;

import java.util.List;

import model.command.ICommands;
import model.shape.AShape;

/**
 * This interface represents all the operations offered by the model.
 * The interface include all the method to add the specific given shape to add the specific given
 * command.
 */
public interface IAnimatorModel extends IAnimatorModelState {

  /**
   * A method to add the specific shape to the model.
   *
   * @param s an AShape - the shape to add in the model
   * @throws IllegalArgumentException if id has already been used
   */
  void addShape(AShape s);

  /**
   * A method to add the specific List of commands to the model.
   *
   * @param c an ICommands - the command to add in the model
   * @throws IllegalArgumentException if the ICommands
   *                                  is not correlate with the shape inside the model
   */
  void addCommands(ICommands c);

  /**
   * A method to delete a shape and its commands.
   *
   * @param id a String
   * @throws IllegalArgumentException if the id is not valid
   */
  void deleteShape(String id);


  /**
   * A method to delete a specific command of a shape.
   *
   * @param id a String
   * @param orderOfCommands the order of the commands need to be delete in the list of command of
   *                        the shape (it is not an index, it is the order in the list starting
   *                        from 1)
   * @throws IllegalArgumentException if the id and the orderOfCommands is not valid
   */
  void deleteCommands(String id, int orderOfCommands);

  /**
   * A Method to get the List of ICommands of the specific shape's id.
   *
   * @param id the given string to get the list of command for the specific shape.
   * @return List of Commands - the list of commands
   */
  List<ICommands> getExecutableCommand(String id);

}
