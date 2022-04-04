package model;


import java.util.List;

import model.command.ICommandsState;
import model.shape.AShape;

/**
 * This interface represents all the state of the model.
 * The interface include all the method to get the status of the model state from the
 * List of Shape to the List of Commands. In which K represents our Shape type, the shape
 * that we are going to animate.
 *
 * @param <K> The model type
 */
public interface IAnimatorModelState<K> {

  /**
   * A method to get the list of shape in the model.
   *
   * @return a List - a list of shape
   */
  List<AShape> getShapes();

  /**
   * A method to get the list of commands in the model.
   *
   * @return a List - a list of command
   * @Param id - String id of the shape
   * @Throws
   */
  List<ICommandsState> getCommands(String id);

  AShape getShapeAtTick(double time, String id);

  int getHeight();

  int getWidth();

}
