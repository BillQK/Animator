package model;


import java.util.List;

import model.command.ICommands;
import model.shape.AShape;
import model.utils.Time;

/**
 * This interface represents all the state of the model.
 * The interface include all the method to get the status of the model state from the
 * List of Shape to the List of Commands.
 *
 * @param <K> The model type
 */
public interface IAnimatorModelState<K> {

  /**
   * A method to get the whole state of the model.
   *
   * @return a String - the model's current state
   */
  String getState();

  /**
   * A method to get the list of shape in the model.
   *
   * @return a List - a list of shape
   */
  List<AShape> getShapes();

  /**
   * A method to get the list of commands in the model.
   * @Param id - String id of the shape
   * @return a List - a list of command
   * @Throws
   */
  List<ICommands> getCommands(String id);

  /**
   * A method to get the time of the model.
   *
   * @return A time - the model's time
   */
  Time getTime();
}
