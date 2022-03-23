package model;

import java.util.List;

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
   * @param id a String - the id to map it with the shape.
   * @param s an AShape - the shape to add in the model
   */
  void addShape(String id, AShape s);

  /**
   * A method to add the specific List of commands to the model.
   *
   * @param id a String - the id to map it with the list.
   * @param a an AShape - the List of command to add in the model
   */
  void addCommands(String id, List<ICommands> a);
}
