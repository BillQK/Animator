import org.junit.Test;

import java.awt.*;

import model.AShape;
import model.Rectangle;
import model.Shape;
import model.SimpleAnimatorModel;
import model.Time;

import static org.junit.Assert.assertEquals;

public class AnimatorModelTest {

  @Test
  public void testBuilder() {
    SimpleAnimatorModel s = new SimpleAnimatorModel.AMBuilder()
            .addRectangle("1,", 1, 3, 4, 4,
                    5, 5, 5, new Time(1, 2))
            .addEllipse("2", 1, 3, 3, 4,
                    5, 5, 6, new Time(2, 3))
            .addRectangle("1", 1, 4, 5, 6,
                    6, 7, 5, new Time(19, 40))
            .build();
    s.getShapes();
    s.getShapes().get(0).setHeight(3);

    assertEquals(s.getShapes().get(0).getType(), Shape.RECTANGLE);
  }

}
