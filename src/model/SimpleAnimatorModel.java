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
 * Represents the Main model class which implemented the IAnimationModel<AShape> </AShape>.
 */
public class SimpleAnimatorModel implements IAnimatorModel<AShape> {
  private final LinkedHashMap<String, AShape> shapes;
  private final LinkedHashMap<String, List<ICommands>> commands;
  private int width;
  private int height;

  /**
   * A constructor for SimpleAnimationModel class with the SimpleAnimator ModelBuilder builder.
   *
   * @param tweenBuilder the builder pattern builder
   */
  private SimpleAnimatorModel(TweenBuilder tweenBuilder) {
    this.shapes = tweenBuilder.shapes;
    this.commands = tweenBuilder.commands;
    this.width = tweenBuilder.width;
    this.height = tweenBuilder.height;
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
    List<ICommandsState> stateList = new ArrayList<>();
    stateList.addAll(this.commands.get(id));
    return stateList;
  }

  @Override
  public AShape getShapeAtTick(double time, String s) {
    List<ICommands> commandsList = this.commands.get(s);
    AShape shape = this.shapes.get(s).getTheShape();
    for (int i = 0; i < commandsList.size(); i++) {
      if (time >= commandsList.get(i).getStart() && time <= commandsList.get(i).getEnd()) {
//        if (commandsList.get(i).getType() == CommandType.MOVE) {
//
//        }
//        RateOfChange.findRate(time, commandsList.get(i).getStart(), commandsList.get(i).getEnd());
        shape = commandsList.get(i).getShapeAtTick(time, shape);
        return shape;
      } else {
        shape = shape.updateShape(commandsList.get(i));
      }
    }
    return shape;
  }


  public static class TweenBuilder implements TweenModelBuilder<IAnimatorModel<AShape>> {

    private final LinkedHashMap<String, AShape> shapes;
    private final LinkedHashMap<String, List<ICommands>> commands;
    private int width;
    private int height;

    public TweenBuilder() {
      this.shapes = new LinkedHashMap<>();
      this.commands = new LinkedHashMap<>();
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
      this.height = height;
      this.width = width;
      return this;
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
      if (startOfLife > endOfLife) {
        throw new IllegalArgumentException("The start time cannot be bigger than end time");
      }
      ArgumentsCheck.colorRange((int) red, (int) green, (int) blue);
      ArgumentsCheck.lessThanZero(cx, cy, xRadius, yRadius, red, green, blue,
              startOfLife, endOfLife);

      // Assign Variable
      Color c = new Color((int) red, (int) green, (int) blue);
      AShape shape = new Ellipse(name, Shape.ELLIPSE, c, cx, cy, xRadius, yRadius,
              new Time(startOfLife, endOfLife));

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
      if (startOfLife > endOfLife) {
        throw new IllegalArgumentException("The start time cannot be bigger than end time");
      }
      ArgumentsCheck.colorRange((int) red, (int) green, (int) blue);
      ArgumentsCheck.lessThanZero(lx, ly, width, height, red, green, blue);

      // Assign Variable
      Color c = new Color((int) red, (int) green, (int) blue);
      AShape shape = new Rectangle(name, Shape.RECTANGLE, c, lx, ly, width, height,
              new Time(startOfLife, endOfLife));

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

      ArgumentsCheck.lessThanZero(startTime, endTime);
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
      return new SimpleAnimatorModel(this);
    }
  }
}
