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

  @Test
  public void testBuilderSetTime() {
    SimpleAnimatorModel s = new SimpleAnimatorModel.AMBuilder().setTime(1000)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10, new Time(0, 10))
            .addEllipse("2", 15, 15, 100, 200, 10, 40, 30, new Time(0, 10))
            .addMove("1", 30, 30, 1, 5)
            .addChangeColor("1", new Color(23,123,23), 5, 10)
            .addChangeDimension("1", 50, 50, 1, 5)
            .build();
    System.out.println(s.getState());

    Color r = new Color(10, 10, 10);
    Color e = new Color(10, 40, 30);
    assertEquals(s.getShapes().size(), 2);
    assertEquals(s.getShapes().get(0),
            new Rectangle("1",
                    Shape.RECTANGLE, r, 10, 15, 100, 200, new Time(0, 10)));
    assertEquals(s.getShapes().get(1),
            new Ellipse("2",
                    Shape.ELLIPSE, e, 15, 15, 100, 200, new Time(0, 10)));
    assertEquals(s.getCommands().get(0).get(0).getType(), CommandType.MOVE);
    assertEquals(s.getTime(), new Time(0, 1000));
  }

}
