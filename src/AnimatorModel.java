/**
 * An interface of an Animator.
 */
public interface AnimatorModel extends AnimatorModelState {
  /**
   * Decide when the object to appear.
   */
  public void appears();

  /**
   * Create the object.
   */
  public void create();

  /**
   * Move the object.
   */
  public void move();

  /**
   * Mutate the color/shape for the object.
   */
  public void changes();

  /**
   * Decide when the object to disappear.
   */
  public void disappear();
}
