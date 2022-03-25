import org.junit.Test;

import java.awt.Color;

import model.SimpleAnimatorModel;
import model.command.CommandType;
import model.command.ICommands;
import model.command.Move;
import model.shape.AShape;
import model.shape.Ellipse;
import model.shape.Rectangle;
import model.shape.Shape;
import model.utils.Posn;
import model.utils.Time;

import static org.junit.Assert.assertEquals;

/**
 * Test class for SimpleAnimatorModel class.
 */
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
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
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
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .build();

    Color r = new Color(10, 10, 10);
    assertEquals(s.getShapes().get(0),
            new Rectangle("1",
                    Shape.RECTANGLE, r, 10, 15, 100, 200,
                    new Time(0, 10)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddRectangleOutSideOfModelTime() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(90, 110))
            .build();
  }

  @Test
  public void testBuilderAddMultipleRectangle() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addRectangle("2", 10, 15, 100, 200, 10, 10, 10,
                    new Time(20, 30))
            .addRectangle("3", 10, 15, 100, 200, 10, 10, 10,
                    new Time(30, 40))
            .addRectangle("4", 10, 15, 100, 200, 10, 10, 10,
                    new Time(40, 50))
            .addRectangle("5", 10, 15, 100, 200, 10, 10, 10,
                    new Time(50, 60))
            .addRectangle("6", 10, 15, 100, 200, 10, 10, 10,
                    new Time(70, 80))
            .addRectangle("7", 10, 15, 100, 200, 10, 10, 10,
                    new Time(90, 100))
            .build();

    assertEquals(s.getShapes().size(), 7);
  }

  @Test
  public void testBuilderAddEllipse() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addEllipse("1", 10, 10, 100, 200, 10, 10, 10,
                    new Time(0, 10))
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
            .addEllipse("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(90, 110))
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
            .addEllipse("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addEllipse("2", 10, 15, 100, 200, 10, 10, 10,
                    new Time(20, 30))
            .addEllipse("3", 10, 15, 100, 200, 10, 10, 10,
                    new Time(30, 40))
            .addEllipse("4", 10, 15, 100, 200, 10, 10, 10,
                    new Time(40, 50))
            .addEllipse("5", 10, 15, 100, 200, 10, 10, 10,
                    new Time(50, 60))
            .addEllipse("6", 10, 15, 100, 200, 10, 10, 10,
                    new Time(70, 80))
            .addEllipse("7", 10, 15, 100, 200, 10, 10, 10,
                    new Time(90, 100))
            .build();
    assertEquals(s.getShapes().size(), 7);
  }

  @Test
  public void testBuilderAddCommandSortInOrder() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addChangeColor("1", new Color(20, 20, 20), 1, 3)
            .addChangeDimension("1", 150, 250, 3, 6)
            .addMove("1", 11, 30, 6, 9)
            .build();
    assertEquals(s.getCommands().get(0).get(0).getType(), CommandType.CHANGE_COLOR);
    assertEquals(s.getCommands().get(0).get(1).getType(), CommandType.CHANGE_DIMENSION);
    assertEquals(s.getCommands().get(0).get(2).getType(), CommandType.MOVE);
  }

  @Test
  public void testBuilderAddCommand() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addChangeColor("1", new Color(20, 20, 20), 3, 5)
            .addMove("1", 11, 30, 5, 6)
            .addChangeDimension("1", 150, 250, 6, 9)
            .build();
    assertEquals(s.getCommands().get(0).get(0).getStart(), 3, 0.01);
    assertEquals(s.getCommands().get(0).get(0).getEnd(), 5, 0.01);
    assertEquals(s.getCommands().get(0).get(1).getStart(), 5, 0.01);
    assertEquals(s.getCommands().get(0).get(1).getEnd(), 6, 0.01);
    assertEquals(s.getCommands().get(0).get(2).getStart(), 6, 0.01);
    assertEquals(s.getCommands().get(0).get(2).getEnd(), 9, 0.01);
  }

  @Test
  public void testBuilderGetCommand() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addChangeColor("1", new Color(20, 20, 20), 3, 5)
            .addMove("1", 11, 30, 5, 6)
            .addChangeDimension("1", 150, 250, 6, 9)
            .build();
    assertEquals(s.getCommands().get(0).get(0).getType(), CommandType.CHANGE_COLOR);
    assertEquals(s.getCommands().get(0).get(1).getType(), CommandType.MOVE);
    assertEquals(s.getCommands().get(0).get(2).getType(), CommandType.CHANGE_DIMENSION);
  }

  @Test
  public void testBuilderGetCommandInOrderTime() {
    s = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 40))
            .addChangeColor("1", new Color(20, 20, 20), 0, 10)
            .addMove("1", 11, 30, 10, 15)
            .addChangeDimension("1", 150, 250, 15, 20)
            .addMove("1", 11, 40, 20, 30)
            .addChangeDimension("1", 200, 200, 30, 40)
            .build();
    assertEquals(s.getCommands().get(0).get(0).getType(), CommandType.CHANGE_COLOR);
    assertEquals(s.getCommands().get(0).get(1).getType(), CommandType.MOVE);
    assertEquals(s.getCommands().get(0).get(2).getType(), CommandType.CHANGE_DIMENSION);
    assertEquals(s.getCommands().get(0).get(3).getType(), CommandType.MOVE);
    assertEquals(s.getCommands().get(0).get(4).getType(), CommandType.CHANGE_DIMENSION);
  }

  @Test
  public void testAddShape() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100).build();
    AShape rec1 = new Rectangle("C",
            Shape.RECTANGLE, new Color(30, 30, 30), -10, -20, 500,
            400, new Time(3, 99));


    s.addShape(rec1);
    assertEquals(s.getShapes().size(), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeFailedId() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100).build();
    AShape rec1 = new Rectangle("C",
            Shape.RECTANGLE, new Color(30, 30, 30), -10, -20, 500,
            400, new Time(3, 99));

    AShape rec2 = new Rectangle("C",
            Shape.RECTANGLE, new Color(30, 30, 30), -10, -20, 500,
            400, new Time(3, 99));

    s.addShape(rec1);
    // throw Illegal Exception due to same id
    s.addShape(rec2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeOutSideOfModelInterval() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100).build();
    AShape rec1 = new Rectangle("C",
            Shape.RECTANGLE, new Color(30, 30, 30), -10, -20, 500,
            400, new Time(3, 101));

    s.addShape(rec1);
  }

  @Test
  public void testAddCommands() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100).build();

    AShape rec1 = new Rectangle("C",
            Shape.RECTANGLE, new Color(30, 30, 30), -10, -20, 500,
            400, new Time(3, 99));

    ICommands c = new Move(rec1, 4, 30, new Posn(1, 4));

    s.addShape(rec1);
    s.addCommands(c);

    assertEquals(s.getCommands().size(), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddCommandsToShapeThatIsNotInModel() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100).build();

    AShape rec1 = new Rectangle("C",
            Shape.RECTANGLE, new Color(30, 30, 30), -10, -20, 500,
            400, new Time(3, 99));

    ICommands c = new Move(rec1, 4, 30, new Posn(1, 4));

    // Throw IllegalException
    s.addCommands(c);
  }

  @Test
  public void testDeleteShape() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100).build();

    AShape rec1 = new Rectangle("C",
            Shape.RECTANGLE, new Color(30, 30, 30), -10, -20, 500,
            400, new Time(3, 99));

    ICommands c = new Move(rec1, 4, 30, new Posn(1, 4));

    s.addShape(rec1);
    s.addCommands(c);
    s.deleteShape("C");

    assertEquals(s.getCommands().size(), 0);
    assertEquals(s.getShapes().size(), 0);
  }

  @Test
  public void testGetShapes() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addChangeDimension("1", 10, 10, 1, 5)
            .addRectangle("2", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addRectangle("3", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .build();

    assertEquals(s.getShapes().size(), 3);
  }

  @Test
  public void testGetCommands() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addChangeDimension("1", 10, 10, 0, 5)
            .addMove("1", 1, 10, 5, 10)
            .addRectangle("2", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addMove("2", 20, 20, 0, 5)
            .addChangeColor("2", new Color(100, 100, 100), 5, 10)
            .build();

    assertEquals(s.getCommands().size(), 2);
  }

  @Test
  public void testGetCommandsOfTheInsideList() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addChangeDimension("1", 10, 10, 1, 5)
            .addRectangle("2", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addMove("2", 20, 20, 1, 5)
            .addChangeColor("2", new Color(100, 100, 100), 5, 10)
            .build();

    assertEquals(s.getCommands().get(0).size(), 1);
  }

  @Test
  public void testGetCommandsOfTheInsideList1() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addChangeDimension("1", 10, 10, 1, 5)
            .addRectangle("2", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addMove("2", 20, 20, 1, 5)
            .addChangeColor("2", new Color(100, 100, 100), 5, 10)
            .build();

    assertEquals(s.getCommands().get(1).size(), 2);
  }

  @Test
  public void testUsingBuilderAndInterface() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addChangeDimension("1", 10, 10, 1, 5)
            .addRectangle("2", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addMove("2", 20, 20, 1, 5)
            .addChangeColor("2", new Color(100, 100, 100), 5, 10)
            .build();
    AShape ellip = new Ellipse("E", Shape.ELLIPSE, new Color(250, 30, 10),
            3, 4, 30, 30, new Time(0, 30));

    ICommands c = new Move(ellip, 0, 15, new Posn(30, 20));

    s.addShape(ellip);
    s.addCommands(c);

    assertEquals(s.getShapes().size(), 3);
    assertEquals(s.getCommands().size(), 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingTime() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addChangeDimension("1", 10, 10, 1, 5)
            .addRectangle("2", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addMove("2", 20, 20, 1, 5)
            // overlapping
            .addChangeColor("2", new Color(100, 100, 100), 2, 10)
            .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingTimeAnimationInSide() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addChangeDimension("1", 10, 10, 1, 5)
            .addRectangle("2", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addMove("2", 20, 20, 1, 5)
            // overlapping in side the 1 to 5
            .addChangeColor("2", new Color(100, 100, 100), 2, 3)
            .build();
  }


  @Test(expected = IllegalArgumentException.class)
  public void testGapInAnimation() {
    s = new SimpleAnimatorModel.AMBuilder().setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addChangeDimension("1", 10, 10, 1, 5)
            .addRectangle("2", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 10))
            .addMove("2", 20, 20, 1, 5)
            // 1 sec gap
            .addChangeColor("2", new Color(100, 100, 100), 6, 10)
            .build();
  }


}
