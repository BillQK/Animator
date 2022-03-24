import org.junit.Assert;
import org.junit.Test;

import model.utils.RateOfChange;

import static org.junit.Assert.assertEquals;

/**
 * A test class for rate of change.
 */
public class RateOfChangeTest {

  @Test
  public void testFindRate() {
    assertEquals(RateOfChange.findRate(40, 10, 50), 0.75, Integer.MIN_VALUE);
    assertEquals(RateOfChange.findRate(49, 10, 50), 0.975, Integer.MIN_VALUE);
    assertEquals(RateOfChange.findRate(10, 10, 50), 0, Integer.MIN_VALUE);
    assertEquals(RateOfChange.findRate(0, 10, 50), 0, Integer.MIN_VALUE);
    assertEquals(RateOfChange.findRate(40, 10, 50), 0.75, Integer.MIN_VALUE);
    assertEquals(RateOfChange.findRate(-1, 10, 30), 0, Integer.MIN_VALUE);
  }
}