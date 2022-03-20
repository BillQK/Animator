import org.junit.Test;

import model.SimpleAnimatorModel;
import model.Time;

public class AnimatorModelTest {

  @Test
  public void testBuilder() {
    SimpleAnimatorModel s = new SimpleAnimatorModel.AMBuilder().
            addRectangle("1,", 1, 3, 4, 4, 5, 5, 5, new Time(1, 2)).build();

  }

}
