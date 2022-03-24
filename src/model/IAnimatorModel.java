package model;

import model.command.ICommands;
import model.shape.AShape;

/**
 * This interface represents all the operations offered by the model.
 * The interface include all the method to add the specific given shape to add the specific given
 * command.
 *
 * @param <K> The model type
 */
public interface IAnimatorModel<K> extends IAnimatorModelState<K> {

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

}
