package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
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

  //Not want to test yet
  @Override
  public void addCommands(String id, List<ICommands> a) {
    this.commands.put(id, a);
  }

  @Override
  public String getState() {
    String finalString = "";
    for (String s : this.shapes.keySet()) {
      finalString += "Shape: " + s + " " + this.shapes.get(s).getType().getShapeType() + "\n";

      //The order of what we print in one motion
      finalString += "motion " + s + " t " + "x " + "y " + "w " + "h " + "r " + "g " + "b "
              + "  " + "t " + "x " + "y " + "w " + "h " + "r " + "g " + "b\n";

      sortCommandList(this.commands.get(s));
      for (int i = 0; i < this.commands.get(s).size(); i++) {
        ICommands com = this.commands.get(s).get(i);
        if (i != (this.commands.get(s).size() - 1)) {
          finalString += "motion " + com.getBeginsState() + " " + com.getEndsState() + "\n";
        } else {
          finalString += "motion " + com.getBeginsState() + " " + com.getEndsState() + "\n\n";
        }
      }

    }
    return finalString;
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

  /**
   * Sorting method.
   * @param l the list of commands of a shape
   */
  public void sortCommandList(List<ICommands> l) {
    Collections.sort(l);
  }

  @Override
  public List<List<ICommands>> getCommands() {
    List<List<ICommands>> answer = new ArrayList<>();
    for (String c : this.commands.keySet()) {
      answer.add(this.commands.get(c));
    }
    return answer;
  }

  @Override
  public Time getTime() {
    return new Time(this.time);
  }

  public static class AMBuilder {
    private final HashMap<String, AShape> shapes;
    private final HashMap<String, List<ICommands>> commands;
    private Time time;

    public AMBuilder() {
      this.shapes = new HashMap<>();
      this.commands = new HashMap<>();
    }

    public AMBuilder setTime(int end) {
      // Arguments check
      ArgumentsCheck.lessThanZero(end);
      this.time = new Time(0, end);
      return this;
    }

    public AMBuilder addRectangle(String id, double x, double y, double w, double h,
                                  int red, int green, int blue, Time time) {
      // Argument and Object check
      if (this.time == null) {
        throw new IllegalArgumentException("Need to set time");
      }
      ArgumentsCheck.withinIntervalTime(this.time.getStartTime(), this.time.getEndTime(),
              time.getStartTime(), time.getEndTime());
      ArgumentsCheck.lessThanZero(x, y, w, h, red, green, blue);

      // Assign Variable
      Color c = new Color(red, green, blue);
      AShape shape = new Rectangle(id, Shape.RECTANGLE, c, x, y, w, h, time);

      // Add Variable into data structure
      List<ICommands> mtRL = new ArrayList<>();
      this.shapes.put(id, shape);
      this.commands.put(id, mtRL);

      return this;
    }

    public AMBuilder addEllipse(String id, double x, double y, double w, double h,
                                int red, int green, int blue, Time time) {
      // Argument and Object check
      if (this.time == null) {
        throw new IllegalArgumentException("Need to set time");
      }
      ArgumentsCheck.withinIntervalTime(this.time.getStartTime(), this.time.getEndTime(),
              time.getStartTime(), time.getEndTime());
      ArgumentsCheck.lessThanZero(x, y, w, h, red, green, blue);

      // Assign Variable
      Color c = new Color(red, green, blue);
      AShape shape = new Ellipse(id, Shape.ELLIPSE, c, x, y, w, h, time);

      // Add Variable into data structure
      List<ICommands> mtRL = new ArrayList<>();
      this.shapes.put(id, shape);
      this.commands.put(id, mtRL);

      return this;
    }


    private boolean overlap(CommandType type, double startTime,
                            double endTime, List<ICommands> iCommands) {
      for (ICommands c : iCommands) {
        if (c.getType() == type) {
          try {
            ArgumentsCheck.overlappingTime(c.getStart(), c.getEnd(), startTime, endTime);
          } catch (IllegalArgumentException e) {
            return true;
          }
        }
      }
      return false;
    }

    public AMBuilder addMove(String idShape,
                             double destX, double destY,
                             double startTime, double endTime) {
      if (!shapes.containsKey(idShape)) {
        throw new IllegalArgumentException("Invalid Shape");
      }

      if (overlap(CommandType.MOVE, startTime, endTime, this.commands.get(idShape))) {
        throw new IllegalArgumentException("Cannot add animation, the animation time is overlap");
      }
      AShape shape = shapes.get(idShape);

      ArgumentsCheck.lessThanZero(destX, destY, startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinIntervalTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new Move(shape, startTime, endTime, new Posn(destX, destY));
      //One method for sorting the list based on the times of animations.

      this.commands.get(idShape).add(command);
      return this;
    }


    public AMBuilder addChangeColor(String idShape,
                                    Color color, double startTime, double endTime) {

      if (!shapes.containsKey(idShape)) {
        throw new IllegalArgumentException("Invalid Shape");
      }

      if (overlap(CommandType.MOVE, startTime, endTime, this.commands.get(idShape))) {
        throw new IllegalArgumentException("Cannot add animation, the animation time is overlap");
      }
      AShape shape = shapes.get(idShape);

      if (color == null) {
        throw new IllegalArgumentException("The given ending color cannot be null");
      }
      ArgumentsCheck.lessThanZero(color.getRed(), color.getGreen(), color.getBlue(),
              startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinIntervalTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new ChangeColor(shape, startTime, endTime, color);
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
      ArgumentsCheck.withinIntervalTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new ChangeDimension(shape, startTime, endTime, endW, endH);


      this.commands.get(idShape).add(command);
      return this;
    }


    public SimpleAnimatorModel build() {
      if (this.time == null) {
        throw new IllegalArgumentException("Need to set time");
      }
      return new SimpleAnimatorModel(this);
    }
  }
}
