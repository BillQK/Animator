package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import model.command.ChangeColor;
import model.command.ChangeDimension;
import model.command.ICommands;
import model.command.Move;
import model.shape.AShape;
import model.shape.Ellipse;
import model.shape.Rectangle;
import model.shape.Shape;
import model.utils.ArgumentsCheck;
import model.utils.Posn;
import model.utils.Time;

/**
 * Represents the main model class which implemented the IAnimationModel<AShape> </AShape>.
 */
public class SimpleAnimatorModel implements IAnimatorModel<AShape> {
  private final HashMap<String, AShape> shapes;
  private final HashMap<String, List<ICommands>> commands;
  private final Time time;

  /**
   * A constructor for SimpleAnimationModel class with the given builder.
   *
   * @param amBuilder the builder pattern builder
   */
  private SimpleAnimatorModel(AMBuilder amBuilder) {
    this.shapes = amBuilder.shapes;
    this.commands = amBuilder.commands;
    this.time = amBuilder.time;
  }

  /**
   * A method to add the specific shape to the model.
   *
   * @param s an AShape - the shape to add in the model
   * @throws IllegalArgumentException if id has already been used
   */
  @Override
  public void addShape(AShape s) {
    String id = s.getName();
    if (shapes.containsKey(id)) {
      throw new IllegalArgumentException("Shape has already been set");
    }
    ArgumentsCheck.withinIntervalTime(time.getStartTime(), time.getEndTime(),
            s.getTime().getStartTime(), s.getTime().getEndTime());
    this.shapes.put(id, s);
  }

  /**
   * A method to add the specific List of commands to the model.
   *
   * @param c an ICommands - the command to be add into the model
   * @throws IllegalArgumentException if the ICommands in the list
   *                                  is not correlate with the shape inside the model
   */
  @Override
  public void addCommands(ICommands c) {
    if (!shapes.containsValue(c.getTheShape())) {
      throw new IllegalArgumentException("Cannot add command.");
    }
    this.commands.put(c.getTheShape().getName(), new ArrayList<>());
    this.commands.get(c.getTheShape().getName()).add(c);
  }

  /**
   * A method to delete a shape and its commands.
   *
   * @param id a String
   * @throws IllegalArgumentException if the id is not valid
   */
  @Override
  public void deleteShape(String id) {
    if (!this.shapes.containsKey(id)) {
      throw new IllegalArgumentException("Shapes does not exists.");
    }
    this.shapes.remove(id);
    this.commands.remove(id);
  }

  /**
   * A method to get the whole state of the model.
   *
   * @return a String - the model's current state
   */
  @Override
  public String getState() {
    String finalString = "";
    for (String s : this.shapes.keySet()) {
      finalString += "Shape: " + s + " " + this.shapes.get(s).getType().getShapeType() + "\n";

      finalString += "         START                                  END \n";
      //The order of what we print in one motion
      finalString += "motion " + s + " Time " + "X " + "Y " + "Width " + "Height " + "Red "
              + "Green " + "Blue "
              + "  " + "Time " + "X " + "Y " + "Width " + "Height " + "Red " + "Green " + "Blue\n";

//      this.commands.get(s).sort(Comparable::compareTo);
      Collections.sort(this.commands.get(s));
      for (int i = 0; i < this.commands.get(s).size(); i++) {
        ICommands com = this.commands.get(s).get(i);
        if (i != (this.commands.get(s).size() - 1)) {
          finalString += "motion " + com.getBeginsState() + "    " + com.getEndsState() + "\n";
          this.commands.get(s).get(i).execute(this.commands.get(s).get(i + 1).getStart());
        } else {
          finalString += "motion " + com.getBeginsState() + "    " + com.getEndsState() + "\n\n";
        }
      }

    }
    return finalString;
  }

  /**
   * A method to get the list of shape in the model.
   *
   * @return a List - a list of shape
   */
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
   * A method to get the list of list of commands in the model.
   *
   * @return a List of List - a list of list of command
   */
  @Override
  public List<List<ICommands>> getCommands() {
    List<List<ICommands>> answer = new ArrayList<>();
    for (String c : this.commands.keySet()) {
      Collections.sort(this.commands.get(c));
      answer.add(this.commands.get(c));
    }
    return answer;
  }

  /**
   * A method to get the time of the model.
   *
   * @return A time - the model's time
   */
  @Override
  public Time getTime() {
    return new Time(this.time);
  }

  /**
   * Represents the SimpleAnimationModel builder class.
   */
  public static class AMBuilder {
    private final HashMap<String, AShape> shapes;
    private final HashMap<String, List<ICommands>> commands;
    private Time time;

    /**
     * A constructor for SimpleAnimationModel builder class.
     */
    public AMBuilder() {
      this.shapes = new HashMap<>();
      this.commands = new HashMap<>();
    }

    /**
     * A method to set the end time of the model to the given end time.
     *
     * @param end the given end time
     * @return the builder with a new Time with the set new given end time
     */
    public AMBuilder setTime(int end) {
      // Arguments check
      ArgumentsCheck.lessThanZero(end);
      this.time = new Time(0, end);
      return this;
    }

    /**
     * A method to add a Rectangle shape to the model.
     *
     * @param id    the id to map it with this new shape
     * @param x     the shape start X position
     * @param y     the shape start Y position
     * @param w     the shape start width
     * @param h     the shape start height
     * @param red   the shape start red color
     * @param green the shape start green color
     * @param blue  the shape start blue color
     * @param time  the shape start time
     * @return the builder with an addRectangle shape
     */
    public AMBuilder addRectangle(String id, double x, double y, double w, double h,
                                  int red, int green, int blue, Time time) {
      // Argument and Object check
      if (this.time == null) {
        throw new IllegalArgumentException("Need to set time");
      }
      ArgumentsCheck.withinIntervalTime(this.time.getStartTime(), this.time.getEndTime(),
              time.getStartTime(), time.getEndTime());
      ArgumentsCheck.colorRange(red, green, blue);
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

    /**
     * A method to add an Ellipse shape to the model.
     *
     * @param id    the id to map it with this new shape
     * @param x     the shape start X position
     * @param y     the shape start Y position
     * @param w     the shape start width
     * @param h     the shape start height
     * @param red   the shape start red color
     * @param green the shape start green color
     * @param blue  the shape start blue color
     * @param time  the shape start time
     * @return the builder with an addEllipse shape
     */
    public AMBuilder addEllipse(String id, double x, double y, double w, double h,
                                int red, int green, int blue, Time time) {
      // Argument and Object check
      if (this.time == null) {
        throw new IllegalArgumentException("Need to set time");
      }
      ArgumentsCheck.withinIntervalTime(this.time.getStartTime(), this.time.getEndTime(),
              time.getStartTime(), time.getEndTime());
      ArgumentsCheck.colorRange(red, green, blue);
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

    private double highestEndTime(List<ICommands> commandsList) {
      List<Double> time = new ArrayList<>();
      for (ICommands c : commandsList) {
        time.add(c.getEnd());
      }
      return Collections.max(time);
    }


    private boolean overlap(double startTime, double endTime, List<ICommands> iCommands) {
      for (ICommands c : iCommands) {
        try {
          ArgumentsCheck.overlappingTime(c.getStart(), c.getEnd(), startTime, endTime);
        } catch (IllegalArgumentException e) {
          return true;
        }
      }
      return false;
    }

    /**
     * A method to add the Move command to the model.
     *
     * @param idShape   the shape to add the move method on
     * @param destX     the destination X position for the shape
     * @param destY     the destination Y position for the shape
     * @param startTime the start time of the command
     * @param endTime   the end time of the command
     * @return the builder with an addMove command
     */
    public AMBuilder addMove(String idShape,
                             double destX, double destY,
                             double startTime, double endTime) {
      if (!shapes.containsKey(idShape)) {
        throw new IllegalArgumentException("Invalid Shape");
      }

      if (this.commands.get(idShape).size() != 0) {
        double value = highestEndTime(this.commands.get(idShape));
        if (!(startTime == value)) {
          throw new IllegalArgumentException("Gap Error");
        }
      }

      if (overlap(startTime, endTime, this.commands.get(idShape))) {
        throw new IllegalArgumentException("Cannot add animation, the animation time is overlap");
      }
      AShape shape = shapes.get(idShape);

      ArgumentsCheck.lessThanZero(destX, destY, startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinIntervalTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new Move(shape, startTime, endTime, new Posn(destX, destY));

      this.commands.get(idShape).add(command);
      return this;
    }

    /**
     * A method to add the ChangeColor command to the model.
     *
     * @param idShape   the shape to add the ChangeColor method on
     * @param color     the new given color to set the shape to
     * @param startTime the start time of the command
     * @param endTime   the end time of the command
     * @return the builder with an addChangeColor command
     */
    public AMBuilder addChangeColor(String idShape,
                                    Color color, double startTime, double endTime) {

      if (!shapes.containsKey(idShape)) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      if (this.commands.get(idShape).size() != 0) {
        double value = highestEndTime(this.commands.get(idShape));
        if (!(startTime == value)) {
          throw new IllegalArgumentException("Gap Error");
        }
      }

      if (overlap(startTime, endTime, this.commands.get(idShape))) {
        throw new IllegalArgumentException("Cannot add animation, the animation time is overlap");
      }
      AShape shape = shapes.get(idShape);

      if (color == null) {
        throw new IllegalArgumentException("The given ending color cannot be null");
      }
      ArgumentsCheck.colorRange(color.getRed(), color.getGreen(), color.getBlue());
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

    /**
     * A method to add the ChangeDimension command to the model.
     *
     * @param idShape   the shape to add the ChangeDimension method on
     * @param endW      the given end width to set the shape width to
     * @param endH      the given end height to set the shape height to
     * @param startTime the start time of the command
     * @param endTime   the end time of the command
     * @return the builder with an addChangeDimension command
     */
    public AMBuilder addChangeDimension(String idShape,
                                        double endW, double endH, double startTime, double endTime) {
      if (!shapes.containsKey(idShape)) {
        throw new IllegalArgumentException("Invalid Shape");
      }
      if (this.commands.get(idShape).size() != 0) {
        double value = highestEndTime(this.commands.get(idShape));
        if (!(startTime == value)) {
          throw new IllegalArgumentException("Gap Error");
        }
      }

      if (overlap(startTime, endTime, this.commands.get(idShape))) {
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

    /**
     * A method to start build the builder.
     *
     * @return a SimpleAnimatorModel - the model
     */
    public SimpleAnimatorModel build() {
      if (this.time == null) {
        throw new IllegalArgumentException("Need to set time");
      }
      return new SimpleAnimatorModel(this);
    }
  }
}
