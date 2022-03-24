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

public class AnimatorTextViewTest {
  IAnimatorModel<AShape> model;
  IAnimatorView state;

  @Before
  public void setUp() {
    model = new SimpleAnimatorModel.AMBuilder()
            .setTime(100)
            .addRectangle("1", 10, 15, 100, 200, 10, 10, 10,
                    new Time(0, 15))
            .addChangeColor("1", new Color(20, 20, 20), 9, 10)
            .addMove("1", 11, 40, 4, 9)
            .addMove("1", 11, 30, 1, 4)
            .addChangeDimension("1", 150, 250, 9, 10)
            .addChangeDimension("1", 200, 200, 10, 15)
            .build();
    state = new AnimatorTextView(model);
  }

  @Test
  public void testToStringTest() {
    System.out.println(state.toString());
    assertEquals(state.toString(),
            " ");
  }

}