import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import model.IAnimatorModel;
import model.IAnimatorModelState;
import model.SimpleAnimatorModel;
import model.shape.AShape;
import model.utils.Time;
import view.AnimatorSVGView;
import view.AnimatorTextView;
import view.IAnimatorView;

import static org.junit.Assert.assertEquals;

/**
 * This class test for IAnimatorView class.
 */
public class AnimatorTextViewTest {
  IAnimatorModelState<AShape> model;
  IAnimatorView state;
  IAnimatorView state2;


  @Before
  public void setUp() {
    model = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10,10,100,100,10,10,10,0,100)
            .addColorChange("1", 10,10,10,15,15,15,0,5)
            .addMove("1", 10,10,15,40, 6, 7)
            .addMove("1", 15,40,10,10, 8, 15)
            .addScaleToChange("1", 100, 100, 200, 200,15, 30)
            .addOval("2", 10,10,10,50, 10, 10,10, 0,50)
            .addMove("2", 10,10,50,50,0,10)
            .build();

    state = new AnimatorTextView(model);
  }

  @Test
  public void testToStringTest() {
    assertEquals(state.getDetails(),
            "Shape: 1 Rectangle\n" +
                    "         START                                  END \n" +
                    "motion 1 Time X Y Width Height Red Green Blue   Time X Y Width Height Red Green Blue\n" +
                    "motion 1 0.0 10.0 10.0 100.0 100.0 10 10 10       5.0 10.0 10.0  100.0 100.0 15 15 15\n" +
                    "motion 1 5.0 10.0 10.0 100.0 100.0 15 15 15       6.0 10.0 10.0  100.0 100.0 15 15 15\n" +
                    "motion 1 6.0 10.0 10.0 100.0 100.0 15 15 15       7.0 15.0 40.0  100.0 100.0 15 15 15\n" +
                    "motion 1 7.0 15.0 40.0 100.0 100.0 15 15 15       8.0 15.0 40.0  100.0 100.0 15 15 15\n" +
                    "motion 1 8.0 15.0 40.0 100.0 100.0 15 15 15       15.0 10.0 10.0  100.0 100.0 15 15 15\n" +
                    "motion 1 15.0 10.0 10.0 100.0 100.0 15 15 15       30.0 10.0 10.0  200.0 200.0 15 15 15\n" +
                    "\n" +
                    "Shape: 2 Ellipse\n" +
                    "         START                                  END \n" +
                    "motion 2 Time X Y Width Height Red Green Blue   Time X Y Width Height Red Green Blue\n" +
                    "motion 2 0.0 10.0 10.0 10.0 50.0 10 10 10       10.0 50.0 50.0  10.0 50.0 10 10 10\n" +
                    "\n");
  }

}