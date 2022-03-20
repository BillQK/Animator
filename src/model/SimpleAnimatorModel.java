package model;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.awt.Color;

import java.util.Objects;

public class SimpleAnimatorModel implements IAnimatorModel<IShape> {
  private final HashMap<String, IShape> shapes;
  private final HashMap<String, IAnimations> animations;

  private SimpleAnimatorModel(AnimatorModelBuilder<IShape> animatorModelBuilder) {
    this.shapes = animatorModelBuilder.shapes;
    this.animations = animatorModelBuilder.animations;
  }

  @Override
  public void addShape(IShape s) {

  }

  @Override
  public void addAnimations(IAnimations a) {

  }

  @Override
  public String getDescription() {
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

  public static class AnimatorModelBuilder<K> {
    private final HashMap<String, IShape> shapes;
    private final HashMap<String, IAnimations> animations;

    public AnimatorModelBuilder() {
      this.shapes = new HashMap<>();
      this.animations = new HashMap<>();
    }

    public IAnimatorModel<IShape> addRectangle(String id, int x, int y, int w, int h,
                                               int red, int green, int blue, Time time) {

      Color c = new Color(red, green, blue);
      IShape shape = new Rectangle(Shape.RECTANGLE, c, x, y, w, h);
    }
  }
}
