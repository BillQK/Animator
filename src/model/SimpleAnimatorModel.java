package model;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class SimpleAnimatorModel implements IAnimatorModel<IShape> {
  private final HashMap<String, IShape> shapes;
  private final HashMap<String, IAnimations> animations;

  private SimpleAnimatorModel(AMBuilder amBuilder) {
    this.shapes = amBuilder.shapes;
    this.animations = amBuilder.animations;
  }

  @Override
  public void addShape(String id, IShape s) {
    this.shapes.put(id, s);
  }

  @Override
  public void addAnimations(String id, IAnimations a) {
    this.animations.put(id, a);
  }

  @Override
  public String getState() {
    return null;
  }

  @Override
  public List<IShape> getShapes() {
    return null;
  }

  @Override
  public List<IAnimations> getAnimations() {
    return null;
  }

  @Override
  public Time getTime() {
    return null;
  }

  public static class AMBuilder {
    private final HashMap<String, IShape> shapes;
    private final HashMap<String, IAnimations> animations;

    public AMBuilder() {
      this.shapes = new HashMap<>();
      this.animations = new HashMap<>();
    }

    public AMBuilder addRectangle(String id, int x, int y, int w, int h,
                                  int red, int green, int blue, Time time) {
      ArgumentsCheck.lessThanZero(x, y, w, h, red, green, blue);
      if (time == null) {
        throw new IllegalArgumentException("Time cannot be null");
      }
      Color c = new Color(red, green, blue);
      IShape shape = new Rectangle(id, Shape.RECTANGLE, c, x, y, w, h);

      this.shapes.put(id, shape);
      return this;
    }

    public AMBuilder addEllipse(String id, int x, int y, int w, int h,
                                int red, int green, int blue, Time time) {
      ArgumentsCheck.lessThanZero(x, y, w, h, red, green, blue);
      if (time == null) {
        throw new IllegalArgumentException("Time cannot be null");
      }
      Color c = new Color(red, green, blue);
      IShape shape = new Rectangle(id, Shape.ELLIPSE, c, x, y, w, h);

      this.shapes.put(id, shape);
      return this;
    }

    public SimpleAnimatorModel build() {
      return new SimpleAnimatorModel(this);
    }
  }
}
