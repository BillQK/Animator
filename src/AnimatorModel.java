/**
 * An interface of an Animator.
 */
public interface AnimatorModel extends AnimatorModelState {
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
