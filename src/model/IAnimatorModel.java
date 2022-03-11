package model;

/**
 * This is the interface of the Animator model.
 */
public interface IAnimatorModel extends IAnimatorModelState {
  /**
   * Decide when the object to appear.
   */
  void appears();

  /**
   * Create the object.
   */
  void create();

  /**
   * Move the object.
   */
  void move();

  /**
   * Mutate the color/shape for the object.
   */
  void changes();

  /**
   * Decide when the object to disappear.
   */
  void disappear();
}
