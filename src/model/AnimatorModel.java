package model;

import java.awt.*;
import java.util.HashMap;

/**
 *
 */
public class AnimatorModel implements IAnimatorModel {
  private final HashMap<String, IShape> shape;
  private final HashMap<String, Animation> animation;
  private final int width;
  private final int height;
  private final Time time;

  public AnimatorModel(HashMap<String, IShape> shape, int width, int height, int start, int end) {
    this.shape = new HashMap<>();
    this.width = width;
    this.height = height;
    this.time = new Time(start, end);
    this.animation = new HashMap<String, Animation>();

  }
  // Create --> add a shape to the animator model
  // Appears, move, changes --> add an animation to the animation model
  // add animation need to operate on the added shape in the model.
  // Animation need to take in a start and end time that is within model's start and end time.
  // Model should have a start method that plays all the animation, or one of the animation.
  // Keep track of the time and check which animation should the model execute.
  // The animation class should have the appears, move, changes.
  /**
   * Decide when the object to appear.
   */
  @Override
  public void appears() {

  }

  /**
   * Create the object.
   */
  // add arguments
  // create object and put it into the HashMap
  @Override
  public void create(String id, Shape type, int x, int y, int width, int height, Color color) {
    if (id == "" || type == null || x < 0 || y < 0 || color == null || width < 0 || height < 0) {
      throw new IllegalArgumentException("Invalid arguments to create shape");
    }
    IShape object;
    switch (type) {
      case RECTANGLE:
        object = new Rectangle(type, color, x, y, width, height);
        shape.put(id, object);
        break;
      case ELLIPSE:
        object = new Ellipse(type, color, x, y, width, height);
        shape.put(id, object);
        break;
      default:
        throw new IllegalArgumentException("Something wrong");

    }


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
   * Get the position of the object.
   */
  @Override
  // add id to get which shape
  public Posn getPosition(String id) {
    IShape shape = this.shape.getOrDefault(id, null);
    if (shape == null) {
      throw new IllegalArgumentException("Invalid Shape id");
    }
    Posn posn = shape.getPosition();
    return posn;
  }

  /**
   * Get the width of the object.
   */
  @Override
  // add argument id to know which shape to get
  public int getWidth(String id) {
    IShape shape = this.shape.getOrDefault(id, null);
    if (shape == null) {
      throw new IllegalArgumentException("Invalid Shape id");
    }
    int width = shape.getWidth();
    return width;
  }

  /**
   * Get the height of the object.
   */
  @Override
  public int getHeight(String id) {
    IShape shape = this.shape.getOrDefault(id, null);
    if (shape == null) {
      throw new IllegalArgumentException("Invalid Shape id");
    }
    int height = shape.getHeight();
    return height;
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
  public Color getColor(String id) {
    IShape shape = this.shape.getOrDefault(id, null);
    if (shape == null) {
      throw new IllegalArgumentException("Invalid Shape id");
    }
    Color color = shape.getColor();
    return color;
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
