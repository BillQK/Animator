import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import model.IAnimatorModel;
import model.SimpleAnimatorModel;
import model.shape.AShape;
import model.utils.Time;
import view.AnimatorTextView;
import view.IAnimatorView;

import static org.junit.Assert.assertEquals;

/**
 * This class test for IAnimatorView class.
 */
public class AnimatorTextViewTest {
  IAnimatorModel<AShape> model;
  IAnimatorView state;

  @Before
  public void setUp() {
    model = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 15))
            .addChangeColor("1", new Color(20, 20, 20), 0, 3)
            .addMove("1", 11, 40, 3, 5)
            .addMove("1", 11, 30, 5, 6)
            .addChangeDimension("1", 150, 250, 6, 10)
            .addChangeDimension("1", 200, 200, 10, 15)
            .build();
    state = new AnimatorTextView(model);
  }

  @Test
  public void testToStringTest() {
    System.out.println(state.toString());
    assertEquals(state.toString(),
            "Shape: 1 Rectangle\n" +
                    "         START                                  END \n" +
                    "motion 1 Time X Y Width Height Red Green Blue " +
                    "  Time X Y Width Height Red Green Blue\n" +
                    "motion 1 0.0 11.0 30.0 150.0 250.0 20 20 20 " +
                    "    3.0 11.0 30.0 150.0 250.0 20 20 20 \n" +
                    "motion 1 3.0 11.0 30.0 150.0 250.0 20 20 20   " +
                    "  5.0 11.0 40.0 150.0 250.0 20 20 20 \n" +
                    "motion 1 5.0 11.0 40.0 150.0 250.0 20 20 20   " +
                    "  6.0 11.0 30.0 150.0 250.0 20 20 20 \n" +
                    "motion 1 6.0 11.0 30.0 150.0 250.0 20 20 20    " +
                    " 10.0 11.0 30.0 150.0 250.0 20 20 20 \n" +
                    "motion 1 10.0 11.0 30.0 150.0 250.0 20 20 20   " +
                    "  15.0 11.0 30.0 200.0 200.0 20 20 20 \n" +
                    "\n");
  }

}