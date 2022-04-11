import org.junit.Test;

import java.awt.*;

import model.IAnimatorModel;
import model.SimpleAnimatorModel;
import model.command.ChangeColor;
import model.command.ChangeDimension;
import model.command.ICommands;
import model.command.Move;
import model.shape.AShape;
import model.shape.Rectangle;
import model.shape.Shape;
import model.utils.Posn;
import model.utils.Time;

import static org.junit.Assert.assertEquals;

/**
 * Test class for ICommands class.
 */
public class ICommandsTest {

  IAnimatorModel s = new SimpleAnimatorModel.TweenBuilder()
          .addRectangle("1", 10,10,30,30, 250,250,250,
                                0, 30)
            .addMove("1", 2,2, 30,30, 0,10)
            .addColorChange("1", 2,2,2, 100,100,100,5, 20)
            .addScaleToChange("1", 4,4, 20,20,0,10)
            .addMove("1", 2,2,13,13, 15, 30)
            .build();

  AShape s1 = new Rectangle("2", Shape.RECTANGLE, new Color(0,0,0), 20,
          20, 30,40, new Time(0,100));

  ICommands c1 = new ChangeColor(s1, 0, 50, new Color(0,0,0),
          new Color(50,50,50));
  ICommands c2 = new Move(s1, 20, 30, new Posn(20,20),
          new Posn(10,10));
  ICommands c3 = new ChangeDimension(s1, 30,50, 30,40,
          10,60);

  int delta = Integer.MIN_VALUE;

  //This test is when we will implerment the controller later
  @Test
  public void execute() {
    c1.execute(30);
    assertEquals(s1.getColor(), new Color(30,30,30));
  }

  @Test
  public void execute1() {
    c2.execute(25);
    assertEquals(s1.getPosition().getX(), 15, delta);
  }

  @Test
  public void execute2() {
    c3.execute(40);
    assertEquals(s1.getWidth(), 20, delta);
  }


}