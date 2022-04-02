package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import model.command.ChangeColor;
import model.command.ChangeDimension;
import model.command.CommandType;
import model.command.EmptyCommand;
import model.command.ICommands;
import model.command.ICommandsState;
import model.command.Move;
import model.io.TweenModelBuilder;
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
  private final LinkedHashMap<String, AShape> shapes;
  private final LinkedHashMap<String, List<ICommands>> commands;
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
   * A constructor for SimpleAnimationModel class with the SimpleAnimator ModelBuilder builder.
   *
   * @param tweenBuilder the builder pattern builder
   */
  private SimpleAnimatorModel(TweenBuilder tweenBuilder) {
    this.shapes = tweenBuilder.shapes;
    this.commands = tweenBuilder.commands;
    this.time = tweenBuilder.time;
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
    this.commands.put(id, new ArrayList<>());

  }

  /**
   * A method to add the specific List of commands to the model.
   *
   * @param c an ICommands - the command to be added into the model
   * @throws IllegalArgumentException if the commands is not correlate with the shape
   *                                  inside the model, if the command interval create a gap.
   */
  @Override
  public void addCommands(ICommands c) {
    if (!shapes.containsValue(c.getTheShape())) {
      throw new IllegalArgumentException("Cannot add command.");
    }

    if (this.commands.get(c.getTheShape().getName()).size() != 0) {
      double value = biggestEndTime(this.commands.get(c.getTheShape().getName()));
      if (c.getStart() != value) {
        throw new IllegalArgumentException("Gap Error");
      }
    }
    this.commands.put(c.getTheShape().getName(), new ArrayList<>());
    this.commands.get(c.getTheShape().getName()).add(c);
  }

  private double biggestEndTime(List<ICommands> commandsList) {
    List<Double> time = new ArrayList<>();
    for (ICommands c : commandsList) {
      time.add(c.getEnd());
    }
    return Collections.max(time);
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
    StringBuilder finalString = new StringBuilder();
    for (String s : this.shapes.keySet()) {
      finalString.append("Shape: ").append(s).append(" ")
              .append(this.shapes.get(s).getType().getShapeType()).append("\n");

      finalString.append("         START                                  END \n");
      //The order of what we print in one motion
      finalString.append("motion ").append(s).append(" Time ")
              .append("X ").append("Y ").append("Width ")
              .append("Height ").append("Red ")
              .append("Green ").append("Blue ")
              .append("  ").append("Time ")
              .append("X ").append("Y ")
              .append("Width ")
              .append("Height ")
              .append("Red ").append("Green ").append("Blue\n");

      Collections.sort(this.commands.get(s));
      for (int i = 0; i < this.commands.get(s).size(); i++) {
        ICommands com = this.commands.get(s).get(i);
        if (i != (this.commands.get(s).size() - 1)) {
          finalString.append("motion ").append(com.getBeginsState())
                  .append("    ").append(com.getEndsState()).append("\n");
          this.commands.get(s).get(i).getShapeAtTick(this.commands.get(s).get(i + 1).getStart(), new ArrayList<>());
        } else {
          finalString.append("motion ").append(com.getBeginsState())
                  .append("    ").append(com.getEndsState()).append("\n\n");
        }
      }
    }
    return finalString.toString();
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
   * A method to get the list of commands in the model.
   *
   * @param id - String id of the shape
   * @return a List - a list of command
   */
  @Override
  public List<ICommandsState> getCommands(String id) {
    if (this.commands.get(id) == null) {
      throw new IllegalArgumentException("Illegal Shape");
    }
    return this.commands.get(id);
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
    private final LinkedHashMap<String, AShape> shapes;
    private final LinkedHashMap<String, List<ICommands>> commands;
    private Time time;

    /**
     * A constructor for SimpleAnimationModel builder class.
     */
    public AMBuilder() {
      this.shapes = new LinkedHashMap<>();
      this.commands = new LinkedHashMap<>();
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
      IdCheck(idShape);

      if (overlap(startTime, endTime, this.commands.get(idShape))) {
        throw new IllegalArgumentException("Cannot add animation, the animation time is overlap");
      }
      AShape shape = shapes.get(idShape);

      addEmptyCommands(idShape, startTime);

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

      IdCheck(idShape);

      if (overlap(startTime, endTime, this.commands.get(idShape))) {
        throw new IllegalArgumentException("Cannot add animation, the animation time is overlap");
      }
      AShape shape = shapes.get(idShape);
      addEmptyCommands(idShape, startTime);
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
                                        double endW, double endH,
                                        double startTime, double endTime) {
      IdCheck(idShape);

      if (overlap(startTime, endTime, this.commands.get(idShape))) {
        throw new IllegalArgumentException("Cannot add animation, since the animation is overlap");
      }
      addEmptyCommands(idShape, startTime);
      AShape shape = shapes.get(idShape);

      ArgumentsCheck.lessThanZero(endW, endH, startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinIntervalTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new ChangeDimension(shape, startTime, endTime, endW, endH);


      this.commands.get(idShape).add(command);
      return this;
    }

    private void addEmptyCommands(String idShape, double startTime) {
      if (this.commands.get(idShape).size() != 0) {
        double value = highestEndTime(this.commands.get(idShape));
        if (startTime > value) {
          List<ICommands> l = this.commands.get(idShape);
          AShape s = this.shapes.get(idShape);
          ICommands emptyCommand = new EmptyCommand(s, CommandType.EMPTY, value, startTime);
          l.add(emptyCommand);
        }
      }
    }

    private void IdCheck(String idShape) {
      if (!shapes.containsKey(idShape)) {
        throw new IllegalArgumentException("Invalid Shape");
      }
    }

    /**
     * A method to start build the builder.
     *
     * @return a SimpleAnimatorModel - the model
     */
    public IAnimatorModel<AShape> build() {
      if (this.time == null) {
        throw new IllegalArgumentException("Need to set time");
      }
      return new SimpleAnimatorModel(this);
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

  }

  public static class TweenBuilder implements TweenModelBuilder<IAnimatorModel<AShape>> {

    private final LinkedHashMap<String, AShape> shapes;
    private final LinkedHashMap<String, List<ICommands>> commands;
    private Time time;

    public TweenBuilder(LinkedHashMap<String, AShape> shapes,
                        LinkedHashMap<String, List<ICommands>> commands) {
      this.shapes = shapes;
      this.commands = commands;
      this.time = new Time(1000);
    }


    private void addEmptyCommands(String idShape, double startTime) {
      if (this.commands.get(idShape).size() != 0) {
        double value = highestEndTime(this.commands.get(idShape));
        if (startTime > value) {
          List<ICommands> l = this.commands.get(idShape);
          AShape s = this.shapes.get(idShape);
          ICommands emptyCommand = new EmptyCommand(s, CommandType.EMPTY, value, startTime);
          l.add(emptyCommand);
        }
      }
    }

    private void IdCheck(String idShape) {
      if (!shapes.containsKey(idShape)) {
        throw new IllegalArgumentException("Invalid Shape");
      }
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
     * Set the bounds of the canvas for the animation.
     *
     * @param width  the width in pixels of the canvas
     * @param height the height in pixels of the canvas
     */
    @Override
    public TweenModelBuilder<IAnimatorModel<AShape>> setBounds(int width, int height) {
      return null;
    }

    /**
     * Add a new oval to the model with the given specifications.
     *
     * @param name        the unique name given to this shape
     * @param cx          the x-coordinate of the center of the oval
     * @param cy          the y-coordinate of the center of the oval
     * @param xRadius     the x-radius of the oval
     * @param yRadius     the y-radius of the oval
     * @param red         the red component of the color of the oval
     * @param green       the green component of the color of the oval
     * @param blue        the blue component of the color of the oval
     * @param startOfLife the time tick at which this oval appears
     * @param endOfLife   the time tick at which this oval disappears
     * @return the builder object
     */
    @Override
    public TweenModelBuilder<IAnimatorModel<AShape>> addOval(String name, float cx, float cy,
                                                             float xRadius, float yRadius,
                                                             float red, float green, float blue,
                                                             int startOfLife, int endOfLife) {
      if (this.time == null) {
        throw new IllegalArgumentException("Need to set time");
      }
      ArgumentsCheck.withinIntervalTime(this.time.getStartTime(), this.time.getEndTime(),
              time.getStartTime(), time.getEndTime());
      ArgumentsCheck.colorRange((int) red, (int) green, (int) blue);
      ArgumentsCheck.lessThanZero(cx, cy, xRadius, yRadius, red, green, blue);

      // Assign Variable
      Color c = new Color((int) red, (int) green, (int) blue);
      AShape shape = new Ellipse(name, Shape.ELLIPSE, c, cx, cy, xRadius, yRadius, time);

      // Add Variable into data structure
      List<ICommands> mtRL = new ArrayList<>();
      this.shapes.put(name, shape);
      this.commands.put(name, mtRL);
      return this;
    }

    /**
     * Add a new rectangle to the model with the given specifications.
     *
     * @param name        the unique name given to this shape
     * @param lx          the minimum x-coordinate of a corner of the
     *                    rectangle
     * @param ly          the minimum y-coordinate of a corner of the
     *                    rectangle
     * @param width       the width of the rectangle
     * @param height      the height of the rectangle
     * @param red         the red component of the color of the rectangle
     * @param green       the green component of the color of the rectangle
     * @param blue        the blue component of the color of the rectangle
     * @param startOfLife the time tick at which this rectangle appears
     * @param endOfLife   the time tick at which this rectangle disappears
     * @return the builder object
     */
    @Override
    public TweenModelBuilder<IAnimatorModel<AShape>> addRectangle(String name, float lx, float ly,
                                                                  float width, float height,
                                                                  float red, float green, float blue,
                                                                  int startOfLife, int endOfLife) {
      // Argument and Object check
      if (this.time == null) {
        throw new IllegalArgumentException("Need to set time");
      }
      ArgumentsCheck.withinIntervalTime(this.time.getStartTime(), this.time.getEndTime(),
              time.getStartTime(), time.getEndTime());
      ArgumentsCheck.colorRange((int) red, (int) green, (int) blue);
      ArgumentsCheck.lessThanZero(lx, ly, width, height, red, green, blue);

      // Assign Variable
      Color c = new Color((int) red, (int) green, (int) blue);
      AShape shape = new Rectangle(name, Shape.RECTANGLE, c, lx, ly, width, height, time);

      // Add Variable into data structure
      List<ICommands> mtRL = new ArrayList<>();
      this.shapes.put(name, shape);
      this.commands.put(name, mtRL);

      return this;
    }

    /**
     * Move the specified shape to the given position during the given time
     * interval.
     *
     * @param name      the unique name of the shape to be moved
     * @param moveFromX the x-coordinate of the initial position of this shape.
     *                  What this x-coordinate represents depends on the shape.
     * @param moveFromY the y-coordinate of the initial position of this shape.
     *                  what this y-coordinate represents depends on the shape.
     * @param moveToX   the x-coordinate of the final position of this shape. What
     *                  this x-coordinate represents depends on the shape.
     * @param moveToY   the y-coordinate of the final position of this shape. what
     *                  this y-coordinate represents depends on the shape.
     * @param startTime the time tick at which this movement should start
     * @param endTime   the time tick at which this movement should end
     */
    @Override
    public TweenModelBuilder<IAnimatorModel<AShape>> addMove(String name,
                                                             float moveFromX, float moveFromY,
                                                             float moveToX, float moveToY,
                                                             int startTime, int endTime) {
      IdCheck(name);

      if (overlap(startTime, endTime, this.commands.get(name))) {
        throw new IllegalArgumentException("Cannot add animation, the animation time is overlap");
      }
      AShape shape = shapes.get(name);

      addEmptyCommands(name, startTime);

      ArgumentsCheck.lessThanZero(moveToX, moveToY, startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinIntervalTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new Move(shape, startTime, endTime, new Posn(moveToX, moveToY));

      this.commands.get(name).add(command);
      return this;
    }

    /**
     * Change the color of the specified shape to the new specified color in the
     * specified time interval.
     *
     * @param name      the unique name of the shape whose color is to be changed
     * @param oldR      the r-component of the old color
     * @param oldG      the g-component of the old color
     * @param oldB      the b-component of the old color
     * @param newR      the r-component of the new color
     * @param newG      the g-component of the new color
     * @param newB      the b-component of the new color
     * @param startTime the time tick at which this color change should start
     * @param endTime   the time tick at which this color change should end
     */
    @Override
    public TweenModelBuilder<IAnimatorModel<AShape>> addColorChange(String name,
                                                                    float oldR, float oldG, float oldB,
                                                                    float newR, float newG, float newB,
                                                                    int startTime, int endTime) {
      IdCheck(name);

      if (overlap(startTime, endTime, this.commands.get(name))) {
        throw new IllegalArgumentException("Cannot add animation, the animation time is overlap");
      }
      AShape shape = shapes.get(name);
      addEmptyCommands(name, startTime);

      ArgumentsCheck.colorRange((int) newR, (int) newG, (int) newB);
      ArgumentsCheck.lessThanZero((int) newR, (int) newG, (int) newB,
              startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinIntervalTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new ChangeColor(shape, startTime, endTime,
              new Color((int) newR, (int) newG, (int) newB));
      //One method for sorting the list based on the times of animations.

      this.commands.get(name).add(command);
      return this;
    }

    /**
     * Change the x and y extents of this shape from the specified extents to the
     * specified target extents. What these extents actually mean depends on the
     * shape, but these are roughly the extents of the box enclosing the shape
     *
     * @param name
     * @param fromSx
     * @param fromSy
     * @param toSx
     * @param toSy
     * @param startTime
     * @param endTime
     */
    @Override
    public TweenModelBuilder<IAnimatorModel<AShape>> addScaleToChange(String name,
                                                                      float fromSx, float fromSy,
                                                                      float toSx, float toSy,
                                                                      int startTime, int endTime) {
      IdCheck(name);

      if (overlap(startTime, endTime, this.commands.get(name))) {
        throw new IllegalArgumentException("Cannot add animation, since the animation is overlap");
      }
      addEmptyCommands(name, startTime);
      AShape shape = shapes.get(name);

      ArgumentsCheck.lessThanZero((int) toSx, (int) toSy, startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinIntervalTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new ChangeDimension(shape, startTime, endTime, (int) toSx, (int) toSy);


      this.commands.get(name).add(command);
      return this;
    }

    /**
     * A method to start build the builder.
     *
     * @return a SimpleAnimatorModel - the model
     */
    public IAnimatorModel<AShape> build() {
      if (this.time == null) {
        throw new IllegalArgumentException("Need to set time");
      }
      return new SimpleAnimatorModel(this);
    }
  }
}
