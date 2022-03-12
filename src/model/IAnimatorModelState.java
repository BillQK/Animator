package model;

/**
 * A representation of the Animation Model State.
 */
public interface IAnimatorModelState {
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
