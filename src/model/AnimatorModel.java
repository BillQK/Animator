package model;

import java.util.List;

/**
 *
 */
public class AnimatorModel implements IAnimatorModel {
  private final List<AShape> Shape;
  private final int width;
  private final int height;

  public AnimatorModel(List<AShape> shape, int width, int height) {
    Shape = shape;
    this.width = width;
    this.height = height;
  }

  /**
   * Decide when the object to appear.
   */
  @Override
  public void appears() {

  }

  /**
   * Create the object.
   */
  @Override
  public void create() {

  }

  /**
   * Move the object.
   */
  @Override
  public void move() {

  }

  /**
   * Mutate the color/shape for the object.
   */
  @Override
  public void changes() {

  }

  /**
   * Decide when the object to disappear.
   */
  @Override
  public void disappear() {

  }


  //------------------------------ANIMATOR MODEL STATE METHOD--------------------------------//
  /**
   * Get the position of the pbject.
   */
  @Override
  public void getPosition() {

  }

  /**
   * Get the width of the object.
   */
  @Override
  public void getWidth() {

  }

  /**
   * Get the height of the object.
   */
  @Override
  public void getHeight() {

  }

  /**
   * Get the radius of the object.
   */
  @Override
  public void getRadius() {

  }

  /**
   * Get the tick at that moment.
   */
  @Override
  public void getTick() {

  }

  /**
   * Get the color of the object.
   */
  @Override
  public void getColor() {

  }

  /**
   * Get the time of the animation at that moment.
   */
  @Override
  public void getTime() {

  }

  /**
   * Get the shape of the object.
   */
  @Override
  public void getShape() {

  }
}
