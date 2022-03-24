import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import model.IAnimatorModelState;
import model.SimpleAnimatorModel;
import model.shape.AShape;
import model.utils.Time;

import static org.junit.Assert.assertEquals;

public class AnimatorTextViewTest {
  IAnimatorModelState<AShape> state;

  @Before
  public void setUp() {
    state = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 15))
            .addChangeColor("1", new Color(20, 20, 20), 4, 10)
            .addMove("1", 11, 30, 1, 4)
            .addChangeDimension("1", 150, 250, 5, 9)
            .addMove("1", 11, 40, 4, 9)
            .addChangeDimension("1", 200, 200, 10, 15)
            .build();
  }

  @Test
  public void testToStringShapeTest() {
    assertEquals(state.getState(),
            "Shape: 1 Rectangle\n" +
                    "         START                                  END \n" +
                    "motion 1 Time X Y Width Height Red Green Blue " +
                    "  Time X Y Width Height Red Green Blue\n" +
                    "motion 1 1.0 10.0 15.0 100.0 200.0 10 10 10 " +
                    "    4.0 11.0 30.0 100.0 200.0 10 10 10 \n" +
                    "motion 1 4.0 11.0 30.0 100.0 200.0 10 10 10   " +
                    "  9.0 11.0 40.0 100.0 200.0 10 10 10 \n" +
                    "motion 1 4.0 11.0 30.0 100.0 200.0 10 10 10   " +
                    "  10.0 11.0 30.0 100.0 200.0 20 20 20 \n" +
                    "motion 1 5.0 11.0 30.0 100.0 200.0 11 11 11  " +
                    "   9.0 11.0 30.0 150.0 250.0 11 11 11 \n" +
                    "motion 1 10.0 11.0 30.0 162.5 262.5 11 11 11   " +
                    "  15.0 11.0 30.0 200.0 200.0 11 11 11 \n" +
                    "\n");
  }

}