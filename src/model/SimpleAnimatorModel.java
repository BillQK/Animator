package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleAnimatorModel implements IAnimatorModel<AShape> {
  private final HashMap<String, AShape> shapes;
  private final HashMap<String, IAnimations> animations;
  private final Time time;

  private SimpleAnimatorModel(AMBuilder amBuilder) {
    this.shapes = amBuilder.shapes;
    this.animations = amBuilder.animations;
    this.time = amBuilder.time;
  }

  @Override
  public void addShape(String id, AShape s) {
    this.shapes.put(id, s);
  }

  @Override
  public void addAnimations(String id, IAnimations a) {
    this.animations.put(id, a);
  }

  @Override
  public String getState() {
    return "";
  }

  @Override
  public List<AShape> getShapes() {
    List<String> myList = shapes.keySet().stream().collect(Collectors.toList());
    List<AShape> l = new ArrayList<>();
    for (String s : myList) {
      AShape shape = shapes.get(s).getTheShape();
      l.add(shape);
    }
    return l;
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
    private final HashMap<String, AShape> shapes;
    private final HashMap<String, IAnimations> animations;
    private Time time;

    public AMBuilder() {
      this.shapes = new HashMap<>();
      this.animations = new HashMap<>();
      this.time = new Time(0, 1000);
    }

    public AMBuilder setTime(int end) {
      ArgumentsCheck.lessThanZero(end);
      this.time = new Time(0, end);
      return this;
    }

    public AMBuilder addRectangle(String id, int x, int y, int w, int h,
                                  int red, int green, int blue, Time time) {
      ArgumentsCheck.lessThanZero(x, y, w, h, red, green, blue);
      if (time == null) {
        throw new IllegalArgumentException("Time cannot be null");
      }
      Color c = new Color(red, green, blue);
      AShape shape = new Rectangle(id, Shape.RECTANGLE, c, x, y, w, h, time);

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
      AShape shape = new Ellipse(id, Shape.ELLIPSE, c, x, y, w, h, time);

      this.shapes.put(id, shape);
      return this;
    }

    public SimpleAnimatorModel build() {
      return new SimpleAnimatorModel(this);
    }
  }
}
