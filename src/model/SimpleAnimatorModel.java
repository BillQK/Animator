package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import model.command.ChangeColor;
import model.command.ChangeDimension;
import model.command.CommandType;
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
  private final HashMap<String, List<ICommands>> commands;
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
  public void addCommands(String id, List<ICommands> a) {
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
  public List<ICommands> getCommands() {
    return null;
  }

  @Override
  public Time getTime() {
    return null;
  }

  public static class AMBuilder {
    private final HashMap<String, AShape> shapes;
    private final HashMap<String, List<ICommands>> commands;
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

      List<ICommands> mtRL = new ArrayList<>();
      this.shapes.put(id, shape);
      this.commands.put(id, mtRL);
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

    private boolean overlap(CommandType type, double startTime, double endTime, List<ICommands> iCommands) {
      for (ICommands c : iCommands) {
        if (c.getType() == type) {
          try {
            ArgumentsCheck.withinShapeTime(c.getStart(), c.getEnd(), startTime, endTime);
          } catch (IllegalArgumentException e) {
            return false;
          }
        }
      }
      return true;
    }

    public AMBuilder addMove(String idShape,
                             double destX, double destY,
                             double startTime, double endTime) {

      if (!shapes.containsKey(idShape)) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      AShape shape = shapes.get(idShape);

      ArgumentsCheck.lessThanZero(destX, destY, startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinShapeTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new Move(shape, startTime, endTime, new Posn(destX, destY));
      //One method to check if any overlap
      //One method for sorting the list based on the times of animations.
      this.commands.get(idShape).add(command);
      return this;
    }

    public AMBuilder addChangeColor(String idShape,
                                    Color color, double startTime, double endTime) {
      if (!shapes.containsKey(idShape)) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      AShape shape = shapes.get(idShape);

      if (color == null) {
        throw new IllegalArgumentException("The given ending color cannot be null");
      }
      ArgumentsCheck.lessThanZero(color.getRed(), color.getGreen(), color.getBlue(),
              startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinShapeTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new ChangeColor(shape, startTime, endTime, color);
      //One method to check if any overlap
      //One method for sorting the list based on the times of animations.
      this.commands.get(idShape).add(command);
      return this;
    }

    public AMBuilder addChangeDimension(String idShape,
                                    double endW, double endH, double startTime, double endTime) {
      if (!shapes.containsKey(idShape)) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      if (overlap(CommandType.CHANGE_DIMENSION, startTime, endTime, this.commands.get(idShape))) {
        throw new IllegalArgumentException("Cannot add animation, since the animation is overlap");
      }

      AShape shape = shapes.get(idShape);
      ArgumentsCheck.lessThanZero(endW, endH, startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinShapeTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new ChangeDimension(shape, startTime, endTime, endW, endH);
      //One method to check if any overlap
      //One method for sorting the list based on the times of animations.
      this.commands.get(idShape).add(command);
      return this;
    }


    public SimpleAnimatorModel build() {
      return new SimpleAnimatorModel(this);
    }
  }
}
