import org.junit.Test;

import java.awt.*;

import model.IAnimatorModel;
import model.SimpleAnimatorModel;
import model.command.ChangeColor;
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
  IAnimatorModel<AShape> s;
  double delta = Integer.MIN_VALUE;

  @Test
  public void testBuilderAddRectangleBeforeSetTime() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .build();
    assertEquals(s.getShapes().get(0).getName(), "1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddRectangleWithInvalidName() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle(null, 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .build();
    assertEquals(s.getShapes().get(0).getPosition().getY(), 15, delta);
  }

  @Test
  public void testBuilderAddRectangleWithvalidXPos() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", -10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .build();
    assertEquals(s.getShapes().get(0).getPosition().getY(), 15, delta);
  }

  @Test
  public void testBuilderAddRectangleWithvalidYPos() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, -15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .build();
    assertEquals(s.getShapes().get(0).getPosition().getX(), 10, delta);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddRectangleWithInvalidWidth() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, -100, 200,
                    10, 10, 10,
                    0, 10)
            .build();
    assertEquals(s.getShapes().get(0).getPosition().getX(), 10, delta);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddRectangleWithInvalidHeight() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, -200,
                    10, 10, 10,
                    0, 10)
            .build();
    assertEquals(s.getShapes().get(0).getPosition().getX(), 10, delta);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddRectangleWithInvalidRed() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    -10, 10, 10,
                    0, 10)
            .build();
    assertEquals(s.getShapes().get(0).getPosition().getX(), 10, delta);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddRectangleWithInvalidGreen() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, -10, 10,
                    0, 10)
            .build();
    assertEquals(s.getShapes().get(0).getPosition().getX(), 10, delta);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddRectangleWithInvalidBlue() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, -10,
                    0, 10)
            .build();
    assertEquals(s.getShapes().get(0).getPosition().getX(), 10, delta);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddRectangleWithInvalidStartTime() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, -10, 10,
                    -10, 10)
            .build();
    assertEquals(s.getShapes().get(0).getPosition().getX(), 10, delta);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddRectangleWithInvalidEndTime() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, -10, 10,
                    0, -10)
            .build();
    assertEquals(s.getShapes().get(0).getPosition().getX(), 10, delta);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBuilderAddRectangleWithWrongTimeRange() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, -10, 10,
                    10, 0)
            .build();
    assertEquals(s.getShapes().get(0).getPosition().getX(), 10, delta);
  }


  @Test
  public void testBuilderAddRectangle() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .build();

    Color r = new Color(10, 10, 10);
    assertEquals(s.getShapes().get(0),
            new Rectangle("1",
                    Shape.RECTANGLE, r, 10, 15, 100, 200,
                    new Time(0, 10)));
  }


  @Test
  public void testBuilderAddMultipleRectangle() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addRectangle("2", 10, 15, 100, 200,
                    10, 10, 10,
                    20, 30)
            .addRectangle("3", 10, 15, 100, 200,
                    10, 10, 10,
                    30, 40)
            .addRectangle("4", 10, 15, 100, 200,
                    10, 10, 10,
                    40, 50)
            .addRectangle("5", 10, 15, 100, 200,
                    10, 10, 10,
                    50, 60)
            .addRectangle("6", 10, 15, 100, 200,
                    10, 10, 10,
                    70, 80)
            .addRectangle("7", 10, 15, 100, 200,
                    10, 10, 10,
                    90, 100)
            .build();

    assertEquals(s.getShapes().size(), 7);
  }

  @Test
  public void testBuilderAddEllipse() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addOval("1", 10, 10, 100, 200,
                    10, 10, 10,
                    0, 10)
            .build();
    Color e = new Color(10, 10, 10);
    assertEquals(s.getShapes().get(0),
            new Ellipse("1",
                    Shape.ELLIPSE, e, 10, 10, 100, 200, new Time(0, 10)));
  }


  @Test
  public void testBuilderAddMultipleEllipse() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addOval("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addOval("2", 10, 15, 100, 200,
                    10, 10, 10,
                    20, 30)
            .addOval("3", 10, 15, 100, 200,
                    10, 10, 10,
                    30, 40)
            .addOval("4", 10, 15, 100, 200,
                    10, 10, 10,
                    40, 50)
            .addOval("5", 10, 15, 100, 200,
                    10, 10, 10,
                    50, 60)
            .addOval("6", 10, 15, 100, 200,
                    10, 10, 10,
                    70, 80)
            .addOval("7", 10, 15, 100, 200,
                    10, 10, 10,
                    90, 100)
            .build();
    assertEquals(s.getShapes().size(), 7);
  }

  @Test
  public void testBuilderAddCommandSortInOrder() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addColorChange("1", 10, 10, 10, 20, 20, 20,
                    1, 3)
            .addScaleToChange("1", 100, 200, 3, 6,
                    3, 7)
            .addMove("1", 10, 15, 20, 20,
                    7, 10)
            .build();
    assertEquals(s.getCommands("1").get(0).getType(), CommandType.CHANGE_COLOR);
    assertEquals(s.getCommands("1").get(1).getType(), CommandType.CHANGE_DIMENSION);
    assertEquals(s.getCommands("1").get(2).getType(), CommandType.MOVE);
  }

  //the from x and from y can be any variable???
  @Test
  public void testBuilderAddCommandSortInOrder1() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addColorChange("1", 10, 10, 10, 20, 20, 20,
                    1, 3)
            .addScaleToChange("1", 100, 200, 3, 6,
                    3, 7)
            .addMove("1", 10, 15, 20, 20,
                    7, 10)
            .build();
    assertEquals(s.getCommands("1").get(0).getType(), CommandType.CHANGE_COLOR);
    assertEquals(s.getCommands("1").get(1).getType(), CommandType.CHANGE_DIMENSION);
    assertEquals(s.getCommands("1").get(2).getType(), CommandType.MOVE);
  }


  @Test
  public void testBuilderAddCommand() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addColorChange("1", 10, 10, 10, 20, 20, 20,
                    3, 5)
            .addMove("1", 11, 30, 5, 6,
                    5, 6)
            .addScaleToChange("1", 10, 10, 150, 250,
                    6, 9)
            .build();
    assertEquals(s.getCommands("1").get(0).getStart(), 3, 0.01);
    assertEquals(s.getCommands("1").get(0).getEnd(), 5, 0.01);
    assertEquals(s.getCommands("1").get(1).getStart(), 5, 0.01);
    assertEquals(s.getCommands("1").get(1).getEnd(), 6, 0.01);
    assertEquals(s.getCommands("1").get(2).getStart(), 6, 0.01);
    assertEquals(s.getCommands("1").get(2).getEnd(), 9, 0.01);
    assertEquals(s.getCommands("1").get(1).getType(), CommandType.MOVE);
  }

  @Test
  public void testBuilderGetCommand() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addColorChange("1", 10, 10, 10, 20, 20, 20,
                    3, 5)
            .addMove("1", 10, 10, 11, 30,
                    5, 6)
            .addScaleToChange("1", 100, 100, 150, 250,
                    6, 9)
            .build();
    assertEquals(s.getCommands("1").get(0).getType(), CommandType.CHANGE_COLOR);
    assertEquals(s.getCommands("1").get(1).getType(), CommandType.MOVE);
    assertEquals(s.getCommands("1").get(2).getType(), CommandType.CHANGE_DIMENSION);
  }

  @Test
  public void testBuilderGetCommandInOrderTime() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10, 0, 40)
            .addColorChange("1", 10, 10, 10,
                    20, 20, 20, 0, 10)
            .addMove("1", 10, 10, 11, 30,
                    10, 15)
            .addScaleToChange("1", 100, 100, 150, 250,
                    15, 20)
            .addMove("1", 10, 10, 11, 40,
                    20, 30)
            .addScaleToChange("1", 100, 100, 200, 200,
                    30, 40)
            .build();
    assertEquals(s.getCommands("1").get(0).getType(), CommandType.CHANGE_COLOR);
    assertEquals(s.getCommands("1").get(1).getType(), CommandType.MOVE);
    assertEquals(s.getCommands("1").get(2).getType(), CommandType.CHANGE_DIMENSION);
    assertEquals(s.getCommands("1").get(3).getType(), CommandType.MOVE);
    assertEquals(s.getCommands("1").get(4).getType(), CommandType.CHANGE_DIMENSION);
  }

  @Test
  public void testAddRectangle() {
    s = new SimpleAnimatorModel.TweenBuilder().build();
    AShape rec1 = new Rectangle("C",
            Shape.RECTANGLE, new Color(30, 30, 30), -10, -20, 500,
            400, new Time(3, 99));


    s.addShape(rec1);
    assertEquals(s.getShapes().size(), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeFailedId() {
    s = new SimpleAnimatorModel.TweenBuilder().build();
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


  @Test
  public void testAddCommands() {
    s = new SimpleAnimatorModel.TweenBuilder().build();

    AShape rec1 = new Rectangle("C",
            Shape.RECTANGLE, new Color(30, 30, 30), -10, -20, 500,
            400, new Time(3, 99));

    ICommands c = new Move(rec1, 4, 30, new Posn(-10, -20),
            new Posn(1, 4));
    ICommands c2 = new Move(rec1, 31, 45, new Posn(-11, 4),
            new Posn(5, 8));
    ICommands c3 = new ChangeColor(rec1, 30, 46, new Color(30, 30, 30),
            new Color(100, 100, 100));

    s.addShape(rec1);
    s.addCommands(c);
    s.addCommands(c2);
    s.addCommands(c3);

    assertEquals(s.getCommands("C").size(), 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddCommandsToShapeThatIsNotInModel() {
    s = new SimpleAnimatorModel.TweenBuilder().build();

    AShape rec1 = new Rectangle("C",
            Shape.RECTANGLE, new Color(30, 30, 30), -10, -20, 500,
            400, new Time(3, 99));

    ICommands c = new Move(rec1, 4, 30, new Posn(-10, -20), new Posn(1, 4));

    // Throw IllegalException
    s.addCommands(c);
  }

  @Test
  public void testDeleteShape() {
    s = new SimpleAnimatorModel.TweenBuilder().build();

    AShape rec1 = new Rectangle("C",
            Shape.RECTANGLE, new Color(30, 30, 30), -10, -20, 500,
            400, new Time(3, 99));

    ICommands c = new Move(rec1, 4, 30, new Posn(-10, -20), new Posn(1, 4));

    s.addShape(rec1);
    s.addCommands(c);
    s.deleteShape("C");

    try {
      s.getCommands("C");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Illegal Shape");
    }
    assertEquals(s.getShapes().size(), 0);
  }

  @Test
  public void testGetShapes() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addScaleToChange("1", 10, 10, 10, 10,
                    1, 5)
            .addRectangle("2", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addRectangle("3", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .build();

    assertEquals(s.getShapes().size(), 3);
  }


  @Test
  public void testGetCommands() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addScaleToChange("1", 10, 10, 10, 10,
                    0, 5)
            .addMove("1", 0, 0, 1, 10,
                    5, 10)
            .addRectangle("2", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addMove("2", 2, 2, 20, 20,
                    0, 5)
            .addColorChange("2", 10, 10, 10,
                    100, 100, 100, 5, 10)
            .build();

    assertEquals(s.getCommands("1").size(), 2);
  }

  @Test
  public void testGetCommandsOfTheInsideList() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addScaleToChange("1", 10, 10, 10, 10,
                    1, 5)
            .addRectangle("2", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addMove("2", 10, 10, 20, 20,
                    1, 5)
            .addColorChange("2", 10, 10, 10,
                    100, 100, 100, 5, 10)
            .build();

    assertEquals(s.getCommands("1").size(), 1);

  }

  @Test
  public void testGetCommandsOfTheInsideList1() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addScaleToChange("1", 5, 5, 10, 10,
                    1, 5)
            .addRectangle("2", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addMove("2", 10, 10, 20, 20,
                    1, 5)
            .addColorChange("2", 10, 10, 10,
                    100, 100, 100, 5, 10)
            .build();

    assertEquals(s.getCommands("2").size(), 2);
  }

  @Test
  public void testUsingBuilderAndInterface() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addScaleToChange("1", 10, 10, 10, 10,
                    1, 5)
            .addRectangle("2", 10, 15, 100, 200,
                    10, 10, 10,
                    0, 10)
            .addMove("2", 10, 10, 20, 20,
                    1, 5)
            .addColorChange("2", 10, 10, 10,
                    100, 100, 100, 5, 10)
            .build();
    AShape ellip = new Ellipse("E", Shape.ELLIPSE, new Color(250, 30, 10),
            3, 4, 30, 30, new Time(0, 30));

    ICommands c = new Move(ellip, 0, 15, new Posn(3,4), new Posn(30, 20));

    s.addShape(ellip);
    s.addCommands(c);

    assertEquals(s.getShapes().size(), 3);
    assertEquals(s.getCommands("1").size(), 1);
  }

  @Test
  public void testOverlappingTime() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10, 0, 10)
            .addScaleToChange("1", 5, 5, 10, 10,
                    1, 5)
            .addRectangle("2", 10, 15, 100, 200,
                    10, 10, 10, 0, 10)
            .addMove("2", 10, 10, 20, 20,
                    1, 5)
            // overlapping
            .addColorChange("2", 10, 10, 10,
                    100, 100, 100, 2, 8)
            .build();

    assertEquals(s.getCommands("2").size(), 2);
  }

  @Test
  public void testOverlappingTimeAnimationInSide() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10, 0, 10)
            .addScaleToChange("1", 5, 5, 10, 10,
                    1, 5)
            .addRectangle("2", 10, 15, 100, 200,
                    10, 10, 10, 0, 10)
            .addMove("2", 10, 10, 20, 20,
                    1, 5)
            // overlapping in side the 1 to 5
            .addColorChange("2", 10, 10, 10,
                    100, 100, 100, 2, 3)
            .build();
  }


  @Test
  public void testGapInAnimation() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10, 0, 10)
            .addScaleToChange("1", 5, 5, 10, 10,
                    1, 5)
            .addRectangle("2", 10, 15, 100, 200,
                    10, 10, 10, 0, 10)
            .addMove("2", 10, 10, 20, 20,
                    1, 5)
            // 1 sec gap
            .addColorChange("2", 10, 10, 10,
                    100, 100, 100, 7, 10)
            .build();

    assertEquals(s.getCommands("2").get(1).getType(), CommandType.EMPTY);
  }

  @Test
  public void testDeleteCommands() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 15, 100, 200,
                    10, 10, 10, 0, 10)
            .addScaleToChange("1", 5, 5, 10, 10,
                    1, 5)
            .addRectangle("2", 10, 15, 100, 200,
                    10, 10, 10, 0, 10)
            .addMove("2", 10, 10, 20, 20,
                    1, 5)
            // 1 sec gap
            .addColorChange("2", 10, 10, 10,
                    100, 100, 100, 7, 10)
            .build();

    s.deleteCommands("2", 1);
    assertEquals(s.getCommands("2").get(0).getType(), CommandType.EMPTY);
  }

  @Test
  public void testExtraForBuilder() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10,10,30,30, 250,250,250,
                    0, 30)
            .addMove("1", 2,2, 30,30, 0,10)
            .addColorChange("1", 2,2,2, 100,100,100,5, 20)
            .addScaleToChange("1", 4,4, 20,20,0,10)
            .addMove("1", 2,2,13,13, 15, 30)
            .build();

    assertEquals(s.getCommands("1").get(0).getType(), CommandType.MOVE);
    assertEquals(s.getCommands("1").get(1).getType(), CommandType.CHANGE_DIMENSION);
    assertEquals(s.getCommands("1").get(2).getType(), CommandType.CHANGE_COLOR);
    assertEquals(s.getCommands("1").get(3).getType(), CommandType.MOVE);
  }

  @Test
  public void testExecute() {
    s = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10,10,30,30, 250,250,250,
                    0, 30)
            .addMove("1", 10,10, 30,30, 0,10)
            .addMove("1", 30,30,45,45, 10, 15)
            .addMove("1", 45,45,60,60, 15, 30)
            .build();


    for (ICommands c : s.getExecutableCommand("1")) {
      c.execute(7);

    }

    System.out.println(s.getShapes().get(0).getPosition().getX());
  }

}
