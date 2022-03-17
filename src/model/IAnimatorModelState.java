package model;

import java.awt.*;

/**
 * This interface represents different operations that an animator model must support to return
 * various aspects/features of its state. This interface does not provide any operations to
 * mutate the state of an animator model.
 */
public interface IAnimatorModelState<K> {

  /**
   * Get the position of the pbject.
   */
  Posn getPosition(String id);

  /**
   * Get the width of the object.
   */
  int getWidth(String id);

  /**
   * Get the height of the object.
   */
  int getHeight(String id);

  /**
   * Get the radius of the object.
   */
   void getRadius();

  /**
   * Get the tick at that moment.
   */
   void getTick();

  /**
   * Get the color of the object.
   */
   Color getColor(String id);

  /**
   * Get the time of the animation at that moment.
   */
   void getTime();

  /**
   * Get the shape type of the object.
   */
   IShape getShape(String id);
}
