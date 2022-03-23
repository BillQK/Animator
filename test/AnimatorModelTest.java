import org.junit.Test;

import java.awt.*;

import model.SimpleAnimatorModel;
import model.command.CommandType;
import model.shape.Ellipse;
import model.shape.Rectangle;
import model.shape.Shape;
import model.utils.Time;

import static org.junit.Assert.assertEquals;

public class AnimatorModelTest {
  SimpleAnimatorModel s;

  @Test
  public void testBuilderSetTime() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(1000)
            .build();
    assertEquals(s.getTime(), new Time(0, 1000));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddRectangleBeforeSetTime() {
    s = new SimpleAnimatorModel.AMBuilder()
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10, new Time(0, 10))
            .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderSetTimeZero() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(0)
            .build();

  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderSetTimeLessThanZero() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(-10)
            .build();
  }

  @Test
  public void testBuilderAddRectangle() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10, new Time(0, 10))
            .build();

    Color r = new Color(10, 10, 10);
    assertEquals(s.getShapes().get(0),
            new Rectangle("1",
                    Shape.RECTANGLE, r, 10, 15, 100, 200, new Time(0, 10)));


  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddRectangleOutSideOfModelTime() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10, new Time(90, 110))
            .build();
  }

  @Test
  public void testBuilderAddMultipleRectangle() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10, new Time(0, 10))
            .addRectangle("2", 10, 15, 100, 200, 10, 10, 10, new Time(20, 30))
            .addRectangle("3", 10, 15, 100, 200, 10, 10, 10, new Time(30, 40))
            .addRectangle("4", 10, 15, 100, 200, 10, 10, 10, new Time(40, 50))
            .addRectangle("5", 10, 15, 100, 200, 10, 10, 10, new Time(50, 60))
            .addRectangle("6", 10, 15, 100, 200, 10, 10, 10, new Time(70, 80))
            .addRectangle("7", 10, 15, 100, 200, 10, 10, 10, new Time(90, 100))
            .build();

    assertEquals(s.getShapes().size(), 7);
  }

  @Test
  public void testBuilderAddEllipse() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addEllipse("1", 10, 10, 100, 200, 10, 10, 10, new Time(0, 10))
            .build();
    Color e = new Color(10, 10, 10);
    assertEquals(s.getShapes().get(0),
            new Ellipse("1",
                    Shape.ELLIPSE, e, 10, 10, 100, 200, new Time(0, 10)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddEllipseOutSideOfModelTime() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addEllipse("1", 10, 15, 100, 200, 10, 10, 10, new Time(90, 110))
            .build();
    Color e = new Color(10, 10, 10);
    assertEquals(s.getShapes().get(1),
            new Ellipse("2",
                    Shape.ELLIPSE, e, 15, 15, 100, 200, new Time(0, 10)));
    assertEquals(s.getCommands().get(0).get(0).getType(), CommandType.MOVE);
    assertEquals(s.getTime(), new Time(0, 1000));
  }


  @Test
  public void testBuilderAddMultipleEllipse() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addEllipse("1", 10, 15, 100, 200, 10, 10, 10, new Time(0, 10))
            .addEllipse("2", 10, 15, 100, 200, 10, 10, 10, new Time(20, 30))
            .addEllipse("3", 10, 15, 100, 200, 10, 10, 10, new Time(30, 40))
            .addEllipse("4", 10, 15, 100, 200, 10, 10, 10, new Time(40, 50))
            .addEllipse("5", 10, 15, 100, 200, 10, 10, 10, new Time(50, 60))
            .addEllipse("6", 10, 15, 100, 200, 10, 10, 10, new Time(70, 80))
            .addEllipse("7", 10, 15, 100, 200, 10, 10, 10, new Time(90, 100))
            .build();

    assertEquals(s.getShapes().size(), 7);
  }

  @Test
  public void testBuilderAddCommand() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10, new Time(0, 10))
            .addChangeColor("1", new Color(20, 20, 20), 3, 5)
            .addMove("1", 11, 30, 1, 3)
            .addChangeDimension("1", 150, 250, 6, 9)
            .build();

    assertEquals(s.getCommands().get(0).get(0).getStart(), 1, 0.01);
    assertEquals(s.getCommands().get(0).get(0).getEnd(), 3, 0.01);
    assertEquals(s.getCommands().get(0).get(1).getStart(), 3, 0.01);
    assertEquals(s.getCommands().get(0).get(1).getEnd(), 5, 0.01);
    assertEquals(s.getCommands().get(0).get(2).getStart(), 6, 0.01);
    assertEquals(s.getCommands().get(0).get(2).getEnd(), 9, 0.01);
  }

  @Test
  public void testBuilderGetCommand() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10, new Time(0, 10))
            .addChangeColor("1", new Color(20, 20, 20), 3, 5)
            .addMove("1", 11, 30, 1, 3)
            .addChangeDimension("1", 150, 250, 6, 9)
            .build();


    assertEquals(s.getCommands().get(0).get(0).getType(), CommandType.MOVE);
    assertEquals(s.getCommands().get(0).get(1).getType(), CommandType.CHANGE_COLOR);
    assertEquals(s.getCommands().get(0).get(2).getType(), CommandType.CHANGE_DIMENSION);

  }


}
