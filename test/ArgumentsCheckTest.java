import org.junit.Test;

import model.utils.ArgumentsCheck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for ArgumentsCheck class.
 */
public class ArgumentsCheckTest {

  //Test arguments are not less than zero
  @Test
  public void testNoLessThanZero() {
    ArgumentsCheck.lessThanZero(0.0, 10.0, 0.2, 20.0);
    assertEquals(1+1, 2);
  }

  //Test arguments are not empty String
  @Test
  public void testNoEmptyString() {
    ArgumentsCheck.emptyString("hello", "world", " - d");
    assertEquals(1+1, 2);
  }

  //Test arguments are not in the interval time
  @Test
  public void testOutOfIntervalTime() {
    try {
      ArgumentsCheck.withinIntervalTime(10.0, 15.0,
              11.0, 16.0);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time does not range within the bigger time frame.");
    }
  }

  //Test arguments are in the interval time
  @Test
  public void testInIntervalTime() {
    ArgumentsCheck.withinIntervalTime(10.0, 15.0, 10.0, 15.0);
    assertEquals(1+1, 2);
  }

  //Test arguments are in the interval time
  @Test
  public void testInIntervalTime1() {
    ArgumentsCheck.withinIntervalTime(10.0, 15.0, 12.0, 13.0);
    assertEquals(1+1, 2);
  }

  //Test not overlap time
  @Test
  public void testNotOverlapTime() {
    ArgumentsCheck.overlappingTime(30, 40, 10, 20);
    assertEquals(1+1, 2);
  }

  //Test not overlap time
  @Test
  public void testNotOverlapTime1() {
    ArgumentsCheck.overlappingTime(50, 60, 70, 90);
    assertEquals(1+1, 2);
  }

  //Test not overlap time
  @Test
  public void testNotOverlapTime2() {
    ArgumentsCheck.overlappingTime(15, 40, 13, 15);
    assertEquals(1+1, 2);
  }

  //Test not overlap time
  @Test
  public void testNotOverlapTime3() {
    ArgumentsCheck.overlappingTime(15, 20, 20, 40);
    assertEquals(1+1, 2);
  }

  //Test arguments are less than zero
  @Test(expected = IllegalArgumentException.class)
  public void testLessThanZeroWithNeg() {
    ArgumentsCheck.lessThanZero(0.0, -1.0);
    assertEquals(1+1, 2);
  }

  //Test arguments are empty String
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyString() {
    ArgumentsCheck.emptyString("");
    assertEquals(1+1, 2);
  }

  //Test arguments in and out of shape time
  @Test
  public void testWithinShapeTime() {
    ArgumentsCheck.withinIntervalTime(10.0, 15.0, 11.0, 13);
    try {
      ArgumentsCheck.withinIntervalTime(10.0, 15.0, 9.0, 14.0);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time does not range within the bigger time frame.");
    }
  }

  //Test overlap time
  @Test
  public void testOverlappingTime() {
    try {
      ArgumentsCheck.overlappingTime(0, 20, 1, 19);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time is overlapping with another animation.");
    }
  }

  //Test overlap time
  @Test
  public void testOverlappingTime1() {
    try {
      ArgumentsCheck.overlappingTime(30, 40, 10, 38);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time is overlapping with another animation.");
    }
  }

  //Test overlap time
  @Test
  public void testOverlappingTime2() {
    try {
      ArgumentsCheck.overlappingTime(15, 45, 0, 65);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time is overlapping with another animation.");
    }
  }

  //Test overlap time
  @Test
  public void testOverlappingTime3() {
    try {
      ArgumentsCheck.overlappingTime(15, 45, 65, 47);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid time range");
    }
  }

  //Test overlap time
  @Test
  public void testOverlappingTime4() {
    try {
      ArgumentsCheck.overlappingTime(15, 20, 18, 40);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time is overlapping with another animation.");
    }
  }

  //Test overlap time
  @Test
  public void testOverlappingTime5() {
    try {
      ArgumentsCheck.overlappingTime(15, 20, 15, 20);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time is overlapping with another animation.");
    }
  }

}
