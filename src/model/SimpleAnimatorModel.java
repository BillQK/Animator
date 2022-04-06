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
 * This class will implement all method in IAnimationModelState<AShape> </AShape> which
 * help the model to get all the model's state and all the method on
 * IAnimationModel<AShape> </AShape> to add and delete the specific shape and animation.
 */
public class SimpleAnimatorModel implements IAnimatorModel<AShape> {
  private final LinkedHashMap<String, AShape> shapes;
  private final LinkedHashMap<String, List<ICommands>> commands;
  private final int width;
  private final int height;

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
//
//    if (this.commands.get(c.getTheShape().getName()).size() != 0) {
//      double value = biggestEndTime(this.commands.get(c.getTheShape().getName()));
//      if (c.getStart() != value) {
//        throw new IllegalArgumentException("Gap Error");
//      }
//    }
//    this.commands.put(c.getTheShape().getName(), new ArrayList<>());
//    this.commands.get(c.getTheShape().getName()).add(c);

    CommandType commandType = c.getType();
    AShape addShape = c.getTheShape();
    int size = commands.get(addShape.getName()).size();
    double start = c.getStart();
    double end = c.getEnd();

    for (ICommands s : commands.get(addShape.getName())) {
      CommandType currentType = s.getType();
      AShape shape = s.getTheShape();
      double cStart = s.getStart();
      double cEnd = s.getEnd();

      if (commandType == currentType && addShape.getName().equals(shape.getName())) {
        ArgumentsCheck.overlappingTime(start, end, cStart, cEnd);
      }
    }
    List<ICommands> commandsList = this.commands.get(addShape.getName());
    for (int i = 0; i < size; i++) {
      ICommands current = commandsList.get(i);
      double Start = current.getStart();

      if (start < Start) {
        commandsList.add(i, c);
      }
    }
    if (size == commandsList.size()) {
      commandsList.add(c);
    }
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
   * A method to delete a specific command of a shape.
   *
   * @param id              a String
   * @param orderOfCommands the order of the commands need to be delete in the list of command of
   *                        the shape (it is not an index, it is the order in the list starting
   *                        from 1)
   * @throws IllegalArgumentException if the id and the orderOfCommands is not valid
   */
  @Override
  public void deleteCommands(String id, int orderOfCommands) {
    if (this.commands.get(id) == null) {
      throw new IllegalArgumentException("Invalid id");
    }
    if (orderOfCommands > this.commands.get(id).size()) {
      throw new IllegalArgumentException("Invalid orderOfCommands");
    }
    List<ICommands> s = this.commands.get(id);
    AShape shape = this.shapes.get(id);
    ICommands c = s.get(orderOfCommands - 1);
    s.set(orderOfCommands - 1, new EmptyCommand(shape, CommandType.EMPTY,
            c.getStart(), c.getEnd()));
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
   * A method to get the list of commands of a specific shape.
   *
   * @param id - String id of the shape
   * @return a List - a list of command
   */
  @Override
  public List<ICommandsState> getCommands(String id) {
    if (this.commands.get(id) == null) {
      throw new IllegalArgumentException("Illegal Shape");
    }
    return new ArrayList<>(this.commands.get(id));
  }

  /**
   * Return a new state of the given shape at a specific tick of the specific shape.
   *
   * @param time a double - represent the time
   * @param s    a String - represent the id of the shape
   * @return a updated state copy of the Shape
   */
  @Override
  public AShape getShapeAtTick(double time, String s) {
    List<ICommands> commandsList = this.commands.get(s);
    AShape shape = this.shapes.get(s).getTheShape();
    for (ICommands iCommands : commandsList) {
      if (time >= iCommands.getStart() && time <= iCommands.getEnd()) {
        shape = iCommands.getShapeAtTick(time, shape);
        return shape;
      } else {
        shape = shape.updateShape(iCommands);
      }
    }
    return shape;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  /**
   * Represents a simple animation builder that will add shapes and animations to the model.
   */
  public static class TweenBuilder implements TweenModelBuilder<IAnimatorModel<AShape>> {

    private final LinkedHashMap<String, AShape> shapes;
    private final LinkedHashMap<String, List<ICommands>> commands;
    private int width;
    private int height;

    public TweenBuilder() {
      this.shapes = new LinkedHashMap<>();
      this.commands = new LinkedHashMap<>();
    }

    /**
     * A method to add an empty command which does nothing.
     *
     * @param idShape   String - id of the shape to add the empty commands
     * @param startTime the start time to know where to add the command
     */
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

    /**
     * A method to check if there exist a shape based on the given key - id of the shape.
     *
     * @param idShape String - id of the shape
     */
    private void idCheck(String idShape) {
      if (!shapes.containsKey(idShape)) {
        throw new IllegalArgumentException("Invalid Shape");
      }
    }

    /**
     * A method to find the highest end time from the given list of commands.
     *
     * @param commandsList the given list of commands
     * @return a double - the highest end time of a command
     */
    private double highestEndTime(List<ICommands> commandsList) {
      List<Double> time = new ArrayList<>();
      for (ICommands c : commandsList) {
        time.add(c.getEnd());
      }
      return Collections.max(time);
    }

    /**
     * A method to check that the given end and start time of the new command doesn't overlap with
     * any commands in the list of cammands of a specific shape.
     *
     * @param startTime the start time of the new command
     * @param endTime   the end time of the new command
     * @param iCommands the list of commands of the shape
     * @return true if there is an overlap, false otherwise
     */
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
      ArgumentsCheck.lessThanZero(width, height);
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
      if (red < 1 || green < 1 || blue < 1) {
        red = red * 255;
        green = green * 255;
        blue = blue * 255;
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
                                                                  float red, float green,
                                                                  float blue,
                                                                  int startOfLife, int endOfLife) {
      if (startOfLife > endOfLife) {
        throw new IllegalArgumentException("The start time cannot be bigger than end time");
      }
      if (red < 1 || green < 1 || blue < 1) {
        red = red * 255;
        green = green * 255;
        blue = blue * 255;
      }
      ArgumentsCheck.colorRange((int) red, (int) green, (int) blue);
      ArgumentsCheck.lessThanZero(width, height, red, green, blue);

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
      idCheck(name);


      if (!this.commands.get(name).isEmpty()) {
        ICommands com = this.commands.get(name).get(this.commands.get(name).size() - 1);
        if (com.getType() != CommandType.MOVE
                && (startTime == com.getStart() && endTime == com.getEnd())) {
          addMoveMethod(name,moveFromX, moveFromY, startTime, endTime,  moveToX, moveToY);
        } else {
          if (overlap(startTime, endTime, this.commands.get(name))) {
            throw new IllegalArgumentException("Cannot add animation, " +
                    "the animation time is overlap");
          }
          addMoveMethod(name,moveFromX, moveFromY, startTime, endTime, moveToX, moveToY);
        }
      } else {
        if (overlap(startTime, endTime, this.commands.get(name))) {
          throw new IllegalArgumentException("Cannot add animation, " +
                  "the animation time is overlap");
        }
        addMoveMethod(name, moveFromX, moveFromY, startTime, endTime, moveToX, moveToY);
      }
      return this;
    }

    /**
     * A method to add the command on to the list of command of the given specific shape.
     *
     * @param name      the string id of the shape
     * @param startTime the start time of the animation
     * @param endTime   the end time of the animation
     * @param moveToX   the ending X position that the shape should be
     * @param moveToY   the ending Y position that the shape should be
     */
    private void addMoveMethod(String name, float moveFromX, float moveFromY,  int startTime, int endTime,
                               float moveToX, float moveToY) {
      AShape shape = shapes.get(name);

      addEmptyCommands(name, startTime);

      ArgumentsCheck.lessThanZero(startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinIntervalTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new Move(shape, startTime, endTime, new Posn(moveFromX, moveFromY), new Posn(moveToX, moveToY));

      this.commands.get(name).add(command);
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
                                                                    float oldR, float oldG,
                                                                    float oldB, float newR,
                                                                    float newG, float newB,
                                                                    int startTime, int endTime) {
      idCheck(name);

      if (!this.commands.get(name).isEmpty()) {
        ICommands com = this.commands.get(name).get(this.commands.get(name).size() - 1);
        if (com.getType() != CommandType.CHANGE_COLOR
                && (startTime == com.getStart() && endTime == com.getEnd())) {
          addChangeColorMethod(name, oldR, oldG, oldB, newR, newG, newB, startTime, endTime);
        } else {
          if (overlap(startTime, endTime, this.commands.get(name))) {
            throw new IllegalArgumentException("Cannot add animation, "
                    + "the animation time is overlap");
          }
          addChangeColorMethod(name, oldR, oldG, oldB, newR, newG, newB, startTime, endTime);
        }
      } else {
        if (overlap(startTime, endTime, this.commands.get(name))) {
          throw new IllegalArgumentException("Cannot add animation, "
                  + "the animation time is overlap");
        }
        addChangeColorMethod(name, oldR, oldG, oldB, newR, newG, newB, startTime, endTime);
      }
      return this;
    }

    /**
     * A method to add the command on to the list of command of the given specific shape.
     *
     * @param name      the string id of the shape
     * @param newR      the new red color that the shape should be
     * @param newG      the new green color that the shape should be
     * @param newB      the new blue color that the shape should be
     * @param startTime the start time of the animation
     * @param endTime   the end time of the animation
     */
    private void addChangeColorMethod(String name, float oldR, float oldG, float oldB, float newR, float newG, float newB,
                                      int startTime, int endTime) {
      AShape shape = shapes.get(name);
      addEmptyCommands(name, startTime);

      Color startColor = new Color((int) oldR, (int) oldG, (int) oldB);

      if (newR < 1 || newG < 1 || newB < 1) {
        newR = newR * 255;
        newG = newG * 255;
        newB = newB * 255;
      }
      ArgumentsCheck.colorRange((int) newR, (int) newG, (int) newB);
      ArgumentsCheck.lessThanZero((int) newR, (int) newG, (int) newB,
              startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinIntervalTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new ChangeColor(shape, startTime, endTime,
              startColor, new Color((int) newR, (int) newG, (int) newB));
      //One method for sorting the list based on the times of animations.

      this.commands.get(name).add(command);
    }

    /**
     * Change the x and y extents of this shape from the specified extents to the
     * specified target extents. What these extents actually mean depends on the
     * shape, but these are roughly the extents of the box enclosing the shape
     *
     * @param name      the unique name of the shape whose color is to be changed
     * @param fromSx    the old x-position
     * @param fromSy    the old y-position
     * @param toSx      the new x-position
     * @param toSy      the new y-position
     * @param startTime the time tick at which this width and height change should start
     * @param endTime   the time tick at which this width and height change should end
     */
    @Override
    public TweenModelBuilder<IAnimatorModel<AShape>> addScaleToChange(String name,
                                                                      float fromSx, float fromSy,
                                                                      float toSx, float toSy,
                                                                      int startTime, int endTime) {
      idCheck(name);

      if (!this.commands.get(name).isEmpty()) {
        int highestIndex = this.commands.get(name).size() - 1;
        ICommands com = this.commands.get(name).get(highestIndex);
        if (com.getType() != CommandType.CHANGE_DIMENSION
                && (startTime == com.getStart() && endTime == com.getEnd())) {
          addScaleToChangeMethod(name, fromSx,  fromSy , toSx, toSy, startTime, endTime);
        } else {
          if (overlap(startTime, endTime, this.commands.get(name))) {
            throw new IllegalArgumentException("Cannot add animation, "
                    + "since the animation is overlap");
          }
          addScaleToChangeMethod(name, fromSx,  fromSy , toSx, toSy, startTime, endTime);
        }
      } else {
        if (overlap(startTime, endTime, this.commands.get(name))) {
          throw new IllegalArgumentException("Cannot add animation, "
                  + "since the animation is overlap");
        }
        addScaleToChangeMethod(name, fromSx,  fromSy , toSx, toSy, startTime, endTime);
      }
      return this;
    }

    /**
     * A method to add the command on to the list of command of the given specific shape.
     *
     * @param name      the string id of the shape
     * @param toSx      the ending width that the shape should be
     * @param toSy      the ending height that the shape should be
     * @param startTime the start time of the animation
     * @param endTime   the end time of the animation
     */
    private void addScaleToChangeMethod(String name, float fromSx, float fromSy, float toSx, float toSy,
                                        int startTime, int endTime) {
      addEmptyCommands(name, startTime);
      AShape shape = shapes.get(name);

      ArgumentsCheck.lessThanZero((int) toSx, (int) toSy, startTime, endTime);
      double shapeStart = shape.getTime().getStartTime();
      double shapeEnd = shape.getTime().getEndTime();
      ArgumentsCheck.withinIntervalTime(shapeStart, shapeEnd, startTime, endTime);

      ICommands command = new ChangeDimension(shape, startTime, endTime, fromSx, fromSy, (int) toSx, (int) toSy);

      this.commands.get(name).add(command);
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
