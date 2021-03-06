package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

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
import model.shape.Plus;
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
public class SimpleAnimatorModel implements IAnimatorModel {
  private final LinkedHashMap<String, AShape> shapes;
  private final LinkedHashMap<String, List<ICommands>> commands;
  private TreeSet<Integer> discreteTime;
  private TreeMap<Integer, Integer> timeintervals;
  private List<Integer> slowmoTempo;
  private final int width;
  private final int height;

  /**
   * A constructor for SimpleAnimationModel class with the SimpleAnimator ModelBuilder builder.
   *
   * @param tweenBuilder the builder pattern builder
   */
  public SimpleAnimatorModel(TweenBuilder tweenBuilder) {
    this.shapes = tweenBuilder.shapes;
    this.commands = tweenBuilder.commands;
    this.discreteTime = tweenBuilder.discreteTime;
    this.timeintervals = tweenBuilder.timeintervals;
    this.slowmoTempo = tweenBuilder.slowmoTempo;
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

    TweenBuilder.addCommandsHelper(c, commands);
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
   * @param orderOfCommands the order of the commands need to be deleted in the list of command of
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
    List<String> myList = new ArrayList<>(shapes.keySet());
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
   * A Method to get the List of ICommands of the specific shape's id.
   *
   * @param id the given string to get the list of command for the specific shape.
   * @return List of ICommand - the list of commands
   */
  public List<ICommands> getExecutableCommand(String id) {
    return this.commands.get(id);
  }

  /**
   * Return a new state of the given shape at a specific tick of the specific shape.
   *
   * @param time a double - represent the time
   * @param s    a String - represent the id of the shape
   * @return an updated state copy of the Shape
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

  /**
   * A method to get the height of the canvas.
   *
   * @return int - the height of the canvas
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * A method to get the width of the canvas.
   *
   * @return int - the width of the canvas
   */
  @Override
  public int getWidth() {
    return width;
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
    if (time.size() == 0) {
      return 0;
    }
    return Collections.max(time);
  }

  /**
   * A method to get the last end time of the whole animations.
   *
   * @return double - the last end time of the animation
   */
  @Override
  public double getLastTimeCommands() {
    List<Double> time = new ArrayList<>();
    for (String s : shapes.keySet()) {
      time.add(highestEndTime(commands.get(s)));
    }
    return Collections.max(time);
  }

  @Override
  public TreeSet<Integer> getDiscreteTimeInteger() {
    return this.discreteTime;
  }

  /**
   * a method that get slowmo tempo.
   * @param tick the given tick
   * @return an int
   */
  public int getSlowMoTempoAt(int tick) {
    Integer floorStart = timeintervals.floorKey(tick);
    if (floorStart != null) {
      if (tick < timeintervals.get(floorStart)) {
        List<Integer> lostart = new ArrayList<>();
        lostart.addAll(timeintervals.keySet());
        int index = lostart.indexOf(floorStart);
        return slowmoTempo.get(index);
      }
    }
    return -1;
  }

  //  public TreeMap<Integer, Integer> getTimeInterval() {
  //    return this.timeintervals;
  //  }

  /**
   * Represents a simple animation builder that will add shapes and animations to the model.
   */
  public static class TweenBuilder implements TweenModelBuilder<IAnimatorModel> {

    private final LinkedHashMap<String, AShape> shapes;
    private final LinkedHashMap<String, List<ICommands>> commands;
    private TreeSet<Integer> discreteTime;
    private TreeMap<Integer, Integer> timeintervals;
    private List<Integer> slowmoTempo;
    private int width;
    private int height;

    /**
     * A constructor for tween builder.
     */
    public TweenBuilder() {
      this.shapes = new LinkedHashMap<>();
      this.commands = new LinkedHashMap<>();
      this.discreteTime = new TreeSet<>();
      this.timeintervals = new TreeMap<>();
      this.slowmoTempo = new ArrayList<>();
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

    @Override
    public TweenModelBuilder<IAnimatorModel> addTimeIntervals(int start, int end, int tempo) {
      ArgumentsCheck.lessThanZero(start, end, tempo);
      if (end < start) {
        throw new IllegalArgumentException("Invalid time interval");
      }
      if (tempo == 0) {
        throw new IllegalArgumentException("Tempo for Slow Motion cannot be zero");
      }

      Integer ceilingStart = timeintervals.ceilingKey(start);
      Integer floorStart = timeintervals.floorKey(start);
      int index = -1;

      if (floorStart != null) {
        if (start < timeintervals.get(floorStart)) {
          throw new IllegalArgumentException("Overlapping time");
        }
      }

      if (ceilingStart != null) {
        if (end > timeintervals.get(ceilingStart)) {
          throw new IllegalArgumentException("Overlapping time");
        } else {
          List<Integer> lostart = new ArrayList<>();
          lostart.addAll(timeintervals.keySet());
          index = lostart.indexOf(ceilingStart);
        }
      }

      timeintervals.put(start, end);

      if (index != -1) {
        slowmoTempo.add(index, tempo);
      } else {
        slowmoTempo.add(tempo);
      }
      return this;
    }

    /**
     * Set the bounds of the canvas for the animation.
     *
     * @param width  the width in pixels of the canvas
     * @param height the height in pixels of the canvas
     */
    @Override
    public TweenModelBuilder<IAnimatorModel> setBounds(int width, int height) {
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
    public TweenModelBuilder<IAnimatorModel> addOval(String name, float cx, float cy,
                                                     float xRadius, float yRadius,
                                                     float red, float green, float blue,
                                                     int startOfLife, int endOfLife) {
      if (startOfLife > endOfLife) {
        throw new IllegalArgumentException("The start time cannot be bigger than end time");
      }
      if (ArgumentsCheck.lessThanOrEqualsToOne(red, green, blue)) {
        red = red * 255;
        green = green * 255;
        blue = blue * 255;
      }
      ArgumentsCheck.colorRange((int) red, (int) green, (int) blue);
      ArgumentsCheck.lessThanZero(xRadius, yRadius, red, green, blue,
              startOfLife, endOfLife);

      // Assign Variable
      Color c = new Color((int) red, (int) green, (int) blue);
      AShape shape = new Ellipse(name, Shape.ELLIPSE, c, cx, cy, xRadius, yRadius,
              new Time(startOfLife, endOfLife));

      // Add Variable into data structure
      List<ICommands> mtRL = new ArrayList<>();
      this.shapes.put(name, shape);
      this.commands.put(name, mtRL);
      this.discreteTime.add(startOfLife);
      this.discreteTime.add(endOfLife);
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
    public TweenModelBuilder<IAnimatorModel> addRectangle(String name, float lx, float ly,
                                                          float width, float height,
                                                          float red, float green,
                                                          float blue,
                                                          int startOfLife, int endOfLife) {
      if (startOfLife > endOfLife) {
        throw new IllegalArgumentException("The start time cannot be bigger than end time");
      }
      if (ArgumentsCheck.lessThanOrEqualsToOne(red, green, blue)) {
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
      this.discreteTime.add(startOfLife);
      this.discreteTime.add(endOfLife);
      return this;
    }

    /**
     * Add a new plus to the model with the given specifications.
     *
     * @param name        the unique name given to this shape
     * @param lx          the minimum x-coordinate of a corner of the
     *                    plus
     * @param ly          the minimum y-coordinate of a corner of the
     *                    plus
     * @param width       the width of the plus
     * @param height      the height of the plus
     * @param red         the red component of the color of the plus
     * @param green       the green component of the color of the plus
     * @param blue        the blue component of the color of the plus
     * @param startOfLife the time tick at which this plus appears
     * @param endOfLife   the time tick at which this plus disappears
     * @return the builder object
     */
    @Override
    public TweenModelBuilder<IAnimatorModel> addPlus(String name, float lx, float ly,
                                                     float width, float height,
                                                     float red, float green, float blue,
                                                     int startOfLife, int endOfLife) {
      if (startOfLife > endOfLife) {
        throw new IllegalArgumentException("The start time cannot be bigger than end time");
      }
      if (ArgumentsCheck.lessThanOrEqualsToOne(red, green, blue)) {
        red = red * 255;
        green = green * 255;
        blue = blue * 255;
      }
      ArgumentsCheck.colorRange((int) red, (int) green, (int) blue);
      ArgumentsCheck.lessThanZero(width, height, red, green, blue);

      // Assign Variable
      Color c = new Color((int) red, (int) green, (int) blue);
      AShape shape = new Plus(name, Shape.PLUS, c, lx, ly, width, height,
              new Time(startOfLife, endOfLife));

      // Add Variable into data structure
      List<ICommands> mtRL = new ArrayList<>();
      this.shapes.put(name, shape);
      this.commands.put(name, mtRL);
      this.discreteTime.add(startOfLife);
      this.discreteTime.add(endOfLife);
      return this;
    }


    private void addCommands(ICommands c) {
      addCommandsHelper(c, commands);
    }

    private static void addCommandsHelper(ICommands c, LinkedHashMap<String,
            List<ICommands>> commands) {
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

      List<ICommands> commandsList = commands.get(addShape.getName());
      for (int i = 0; i < size; i++) {
        ICommands current = commandsList.get(i);
        double currentStart = current.getStart();

        if (start < currentStart) {
          commandsList.add(i, c);
        }
      }
      if (size == commandsList.size()) {
        commandsList.add(c);
      }
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
    public TweenModelBuilder<IAnimatorModel> addMove(String name,
                                                     float moveFromX, float moveFromY,
                                                     float moveToX, float moveToY,
                                                     int startTime, int endTime) {
      idCheck(name);
      addEmptyCommands(name, startTime);

      ArgumentsCheck.lessThanZero(startTime, endTime);
      if (endTime < startTime) {
        throw new IllegalArgumentException("The time of the command is invalid");
      }

      Posn origin = new Posn(moveFromX, moveFromY);
      Posn destination = new Posn(moveToX, moveToY);
      AShape s;

      s = this.shapes.get(name);

      ICommands com = new Move(s, startTime, endTime, origin, destination);
      this.addCommands(com);
      this.discreteTime.add(startTime);
      this.discreteTime.add(endTime);

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
    public TweenModelBuilder<IAnimatorModel> addColorChange(String name,
                                                            float oldR, float oldG,
                                                            float oldB, float newR,
                                                            float newG, float newB,
                                                            int startTime, int endTime) {
      idCheck(name);
      addEmptyCommands(name, startTime);

      ArgumentsCheck.lessThanZero(startTime, endTime);
      ArgumentsCheck.colorRange((int) newR, (int) newG, (int) newB);
      ArgumentsCheck.colorRange((int) oldR, (int) oldG, (int) oldB);
      if (endTime < startTime) {
        throw new IllegalArgumentException("The time of the command is invalid");
      }

      if (ArgumentsCheck.lessThanOrEqualsToOne(newR, newG, newB)) {
        newR = newR * 255;
        newG = newG * 255;
        newB = newB * 255;
      }

      if (ArgumentsCheck.lessThanOrEqualsToOne(oldR, oldG, oldB)) {
        oldR = oldR * 255;
        oldG = oldG * 255;
        oldB = oldB * 255;
      }

      AShape s;
      Color newC = new Color((int) newR, (int) newG, (int) newB);
      Color oldC = new Color((int) oldR, (int) oldG, (int) oldB);

      s = this.shapes.get(name);

      ICommands com = new ChangeColor(s, startTime, endTime, oldC, newC);
      this.addCommands(com);
      this.discreteTime.add(startTime);
      this.discreteTime.add(endTime);
      return this;
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
    public TweenModelBuilder<IAnimatorModel> addScaleToChange(String name,
                                                              float fromSx, float fromSy,
                                                              float toSx, float toSy,
                                                              int startTime, int endTime) {
      idCheck(name);
      addEmptyCommands(name, startTime);

      ArgumentsCheck.lessThanZero(startTime, endTime, (int) toSx, (int) toSy,
              (int) fromSx, (int) fromSy);
      if (endTime < startTime) {
        throw new IllegalArgumentException("The time of the command is invalid");
      }

      AShape s;

      s = this.shapes.get(name);


      ICommands com = new ChangeDimension(s, startTime, endTime, fromSx, fromSy,
              toSx, toSy);
      this.addCommands(com);
      this.discreteTime.add(startTime);
      this.discreteTime.add(endTime);

      return this;
    }

    /**
     * A method to start build the builder.
     *
     * @return a SimpleAnimatorModel - the model
     */
    public IAnimatorModel build() {
      return new SimpleAnimatorModel(this);
    }
  }
}
