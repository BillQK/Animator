import org.junit.Test;

import model.utils.RateOfChange;

import static org.junit.Assert.assertEquals;

/**
 * A test class for rate of change.
 */
public class RateOfChangeTest {

  //Test to find the rate when (current - start) / (end - start)
  @Test
  public void testFindRate1() {
    assertEquals(RateOfChange.findRate(40, 10, 50),
            0.75, Integer.MIN_VALUE);
  }

  //Test to find the rate when (current - start) / (end - start)
  @Test
  public void testFindRate2() {
    assertEquals(RateOfChange.findRate(49, 10, 50),
            0.975, Integer.MIN_VALUE);
  }

  //Test to find the rate when (current - start) / (end - start)
  @Test
  public void testFindRate3() {
    assertEquals(RateOfChange.findRate(10, 10, 50),
            0, Integer.MIN_VALUE);
  }

  //Test to find the rate when (current - start) / (end - start)
  @Test
  public void testFindRate4() {
    assertEquals(RateOfChange.findRate(0, 10, 50),
            0, Integer.MIN_VALUE);
  }

  //Test to find the rate when (current - start) / (end - start)
  @Test
  public void testFindRate5() {
    assertEquals(RateOfChange.findRate(40, 10, 50),
            0.75, Integer.MIN_VALUE);
  }

  //Test to find the rate when (current - start) / (end - start)
  @Test
  public void testFindRate6() {
    assertEquals(RateOfChange.findRate(-1, 10, 30),
            0, Integer.MIN_VALUE);
  }

  //Test to find the rate when (current - start) / (end - start)
  @Test
  public void testFindRate7() {
    assertEquals(RateOfChange.findRate(37, 19, 31),
            1.5, Integer.MIN_VALUE);
  }

  //Test to find the rate when (current - start) / (end - start)
  @Test
  public void testFindRate8() {
    assertEquals(RateOfChange.findRate(22, 5, 13),
            2.125, Integer.MIN_VALUE);
  }

  //Test to find the rate when (current - start) / (end - start)
  //and when start is bigger than current
  @Test
  public void testFindRate9() {
    assertEquals(RateOfChange.findRate(22, 80, 13),
            0, Integer.MIN_VALUE);
  }
}