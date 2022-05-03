package model;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import model.command.ICommandsState;
import model.shape.AShape;

/**
 * This interface represents all the state of the model.
 * The interface include all the method to get the status of the model state from the
 * List of Shape to the List of Commands. In which K represents our Shape type, the shape
 * that we are going to animate.
 */
public interface IAnimatorModelState {

  /**
   * A method to get the list of shape in the model.
   *
   * @return a List - a list of shape
   */
  List<AShape> getShapes();

  /**
   * A method to get the list of commands in the model.
   *
   * @param id - String id of the shape
   * @return a List - a list of command
   * @throws IllegalArgumentException If the id does not found
   */
  List<ICommandsState> getCommands(String id);


  /**
   * Return a new state of the given shape at a specific tick of the specific shape.
   *
   * @param time a double - represent the time
   * @param id   a String - represent the id of the shape
   * @return a updated state copy of the Shape
   */
  AShape getShapeAtTick(double time, String id);

  /**
   * A method to get the height of the canvas.
   *
   * @return int - the height of the canvas
   */
  int getHeight();

  /**
   * A method to get the width of the canvas.
   *
   * @return int - the width of the canvas
   */
  int getWidth();

  /**
   * A method to get the last end time of the whole animations.
   *
   * @return double - the last end time of the animation
   */
  double getLastTimeCommands();

  /**
   * A method to get the TreeSet of Integer of discrete time (start and end)
   * of the whole animations.
   *
   * @return TreeSet of Integer - a set of all start and end time of all animations and shapes
   */
  TreeSet<Integer> getDiscreteTimeInteger();

  /**
   * A method to get the slow-motion tempo at the given tick.
   *
   * @param tick the given tick
   * @return int - the slow-motion tempo or -1 if there are no slow-motion tempo at the tick
   */
  int getSlowMoTempoAt(int tick);

  //  TreeMap<Integer, Integer> getTimeInterval();

}
