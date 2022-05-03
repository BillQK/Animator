import org.junit.Before;
import org.junit.Test;

import model.IAnimatorModelState;
import model.SimpleAnimatorModel;
import view.AnimatorInteractiveView;
import view.IAnimatorView;

import static org.junit.Assert.assertEquals;

/**
 * A test class for animator interactive view test.
 */
public class AnimatorInteractiveViewTest {
  IAnimatorModelState model;
  IAnimatorView view;

  @Before
  public void setUp() {
    model = new SimpleAnimatorModel.TweenBuilder()
            .setBounds(100, 100)
            .addRectangle("1", 10, 10, 100, 100, 10, 10, 10, 0, 100)
            .addColorChange("1", 10, 10, 10, 15, 15, 15, 0, 5)
            .addMove("1", 10, 10, 15, 40, 6, 7)
            .addColorChange("1", 5, 5, 5, 200, 200, 200, 6, 7)
            .addMove("1", 15, 40, 10, 10, 8, 15)
            .addScaleToChange("1", 100, 100, 200, 200, 15, 30)
            .addOval("2", 10, 10, 10, 50, 10, 10, 10, 0, 50)
            .addMove("2", 10, 10, 50, 50, 0, 10)
            .build();

    view = new AnimatorInteractiveView(model);
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

  @Test
  public void setIsLoop() {
    assertEquals(false, view.getIsLoop());
    view.setIsLoop(true);
    assertEquals(true, view.getIsLoop());
  }

  @Test
  public void getIsLoop() {
    assertEquals(false, view.getIsLoop());
  }

  @Test
  public void setIsOutline() {
    assertEquals(false, view.getIsOutline());
    view.setIsOutline(true);
    assertEquals(true, view.getIsOutline());
  }

  @Test
  public void getIsOutline() {
    assertEquals(false, view.getIsOutline());
  }

  @Test
  public void setIsDiscreteT() {
    assertEquals(false, view.getIsDiscreteT());
    view.setIsDiscreteT(true);
    assertEquals(true, view.getIsDiscreteT());
  }

  @Test
  public void getIsDiscreteT() {
    assertEquals(false, view.getIsDiscreteT());
  }
}
