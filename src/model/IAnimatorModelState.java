package model;

/**
 * This interface represents different operations that an animator model must support to return
 * various aspects/features of its state. This interface does not provide any operations to
 * mutate the state of an animator model.
 */
public interface IAnimatorModelState<K> {

  /**
   * Get the position of the pbject.
   */
  void getPosition();

  /**
   * Get the width of the object.
   */
  void getWidth();

  /**
   * Get the height of the object.
   */
  void getHeight();

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
   void getColor();

  /**
   * Get the time of the animation at that moment.
   */
   void getTime();

  /**
   * Get the shape type of the object.
   */
   void getShape();
}
