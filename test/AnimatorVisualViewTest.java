import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import model.IAnimatorModelState;
import model.SimpleAnimatorModel;
import model.shape.AShape;
import view.AnimatorTextView;
import view.AnimatorVisualView;
import view.IAnimatorView;

import static org.junit.Assert.*;

public class AnimatorVisualViewTest {
  IAnimatorModelState<AShape> model;
  IAnimatorView view;

  @Before
  public void setUp() {
    model = new SimpleAnimatorModel.TweenBuilder()
            .setBounds(100,100)
            .addRectangle("1", 10, 10, 100, 100, 10, 10, 10, 0, 100)
            .addColorChange("1", 10, 10, 10, 15, 15, 15, 0, 5)
            .addMove("1", 10, 10, 15, 40, 6, 7)
            .addColorChange("1", 5,5,5,200,200,200, 6,7)
            .addMove("1", 15, 40, 10, 10, 8, 15)
            .addScaleToChange("1", 100, 100, 200, 200, 15, 30)
            .addOval("2", 10, 10, 10, 50, 10, 10, 10, 0, 50)
            .addMove("2", 10, 10, 50, 50, 0, 10)
            .build();

    view = new AnimatorVisualView(model);
    view.makeVisible();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void getDetails() {
    view.getDetails();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void writeFile() {
    view.writeFile("test");
  }


}