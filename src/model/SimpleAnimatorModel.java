package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import model.command.ICommands;
import model.command.Move;
import model.shape.AShape;
import model.shape.Ellipse;
import model.shape.Rectangle;
import model.shape.Shape;
import model.utils.ArgumentsCheck;
import model.utils.Posn;
import model.utils.Time;

public class SimpleAnimatorModel implements IAnimatorModel<AShape> {
  private final HashMap<String, AShape> shapes;
  private final HashMap<String, ICommands> commands;
  private final Time time;


  private SimpleAnimatorModel(AMBuilder amBuilder) {
    this.shapes = amBuilder.shapes;
    this.commands = amBuilder.commands;
    this.time = amBuilder.time;
  }

  @Override
  public void addShape(String id, AShape s) {
    this.shapes.put(id, s);
  }

  @Override
  public void addAnimations(String id, ICommands a) {
    this.commands.put(id, a);
  }

  @Override
  public String getState() {
    return null;
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
  public List<ICommands> getAnimations() {
    return null;
  }

  @Override
  public Time getTime() {
    return null;
  }

  public static class AMBuilder {
    private final HashMap<String, AShape> shapes;
    private final HashMap<String, ICommands> commands;
    private Time time;

    public AMBuilder() {
      this.shapes = new HashMap<>();
      this.commands = new HashMap<>();
      this.time = new Time(0, 1000);
    }

    public AMBuilder setTime(int end) {
      ArgumentsCheck.lessThanZero(end);
      this.time = new Time(0, end);
      return this;
    }

    public AMBuilder addRectangle(String id, double x, double y, double w, double h,
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

    public AMBuilder addEllipse(String id, double x, double y, double w, double h,
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

    public AMBuilder addMove(String id, String idShape,
                             double destX, double destY,
                             double startTime, double endTime) {
      if (!shapes.containsKey(idShape)) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      AShape shape = shapes.get(idShape);
      ArgumentsCheck.lessThanZero(destX, destY, startTime, endTime);
      ICommands command = new Move(shape, startTime,endTime, new Posn(destX, destY));
      this.commands.put(id, command);
      return this;
    }

    public SimpleAnimatorModel build() {
      return new SimpleAnimatorModel(this);
    }
  }
}
