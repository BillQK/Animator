package model;

import java.util.HashMap;

/**
 *
 */
public class AnimatorModel implements IAnimatorModel {
  private final HashMap<String, IShape> shape;

  private final int width;
  private final int height;

  public AnimatorModel(HashMap<String, AShape> shape, int width, int height) {
    this.shape = new HashMap<>();
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
  // TODO: is the user allow to configure how fast and how slow the object moves
  // TODO: How should we calculate the speed of change using tick. if the Time is only given argument.
  /**
   * Move the object.
   */
  @Override
  public void move() {

  }
  // TODO: is the user allow to configure how fast and how slow the object changes
  // TODO: How should we calculate the speed of change using tick. if the Time is only given argument.
  /**
   * Mutate the color/shape for the object.
   */
  @Override
  public void changes() {

  }
  // TODO: Question for tick.
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
  public IShape getShape(String id) {
    return this.shape.getOrDefault(id, null);
  }

  public static class AnimatorModelBuilder {
    private HashMap<String, IShape> shapes;
    private int width = 1000;
    private int height = 500;

    public AnimatorModelBuilder() {
      this.shapes = new HashMap<>();
    }

  }
}
