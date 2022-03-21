import org.junit.Test;

import java.awt.*;

import model.SimpleAnimatorModel;
import model.command.ChangeDimension;
import model.shape.AShape;
import model.shape.Ellipse;
import model.shape.Shape;
import model.utils.Time;

import static org.junit.Assert.assertEquals;

public class AnimatorModelTest {

  @Test
  public void testBuilder() {
    String ellipN = "E";
    Color ellipCol = new Color(225, 0, 0);
    double ellipPosX = 20;
    double ellipPosY = 10;
    double ellipW = 30;
    double ellipH = 20;
    Time ellipT = new Time(20, 30);
    AShape ellipShape = new Ellipse(ellipN, Shape.ELLIPSE, ellipCol, ellipPosX, ellipPosY,
            ellipW, ellipH, ellipT);

    SimpleAnimatorModel s = new SimpleAnimatorModel.AMBuilder()
            .addRectangle("1,", 1, 3, 4, 4,
                    5, 5, 5, new Time(1, 2))
            .addEllipse("2", 1, 3, 3, 4,
                    5, 5, 6, new Time(2, 3))
            .addRectangle("1", 1, 4, 5, 6,
                    6, 7, 5, new Time(19, 40))
            .addMove("1", 2, 3, 0, 1)
            .build();
    s.getShapes();
    s.getShapes().get(0).setHeight(3);
    String command = new ChangeDimension(ellipShape, 1, 2,
            10, 10).getEndsState();
    System.out.println(command);
    assertEquals(s.getShapes().get(0).getType(), Shape.RECTANGLE);


  }

}
