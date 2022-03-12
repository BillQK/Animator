package model;

/**
 * This is the interface of the Animator model. It is parameterized over the object/shape type,
 * i.e. when you implement it, you can substitute K with the implementation of the object/shape.
 */
public interface IAnimatorModel<K> extends IAnimatorModelState<K> {
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
