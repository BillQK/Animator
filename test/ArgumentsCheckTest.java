import org.junit.Test;

import model.utils.ArgumentsCheck;

import static org.junit.Assert.assertEquals;

public class ArgumentsCheckTest {

  @Test(expected = IllegalArgumentException.class)
  public void testLessThanZero() {
    ArgumentsCheck.lessThanZero(0.0, -1.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyString() {
    ArgumentsCheck.emptyString("");
  }

  @Test
  public void testWithinShapeTime() {
    ArgumentsCheck.withinShapeTime(10.0, 15.0, 11.0, 13);
    try {
      ArgumentsCheck.withinShapeTime(10.0, 15.0, 9.0, 14.0);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time does not range within the bigger time frame.");
    }
  }

  @Test
  public void testOverlappingTime() {
    ArgumentsCheck.overlappingTime(30, 40, 10, 20);
    ArgumentsCheck.overlappingTime(50, 60, 10, 40);
    ArgumentsCheck.overlappingTime(15, 40, 0, 14);
    ArgumentsCheck.overlappingTime(15, 20, 25, 40);
    try {
      ArgumentsCheck.overlappingTime(0, 20, 1, 19);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time is overlapping with another animation.");
    }

    try {
      ArgumentsCheck.overlappingTime(30, 40, 10, 38);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time is overlapping with another animation.");
    }
    try {
      ArgumentsCheck.overlappingTime(15, 45, 0, 65);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time is overlapping with another animation.");
    }
    try {
      ArgumentsCheck.overlappingTime(15, 45, 65, 47);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Invalid time range");
    }
    try {
      ArgumentsCheck.overlappingTime(15, 20, 18, 40);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time is overlapping with another animation.");
    }
    try {
      ArgumentsCheck.overlappingTime(15,20, 15, 20);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Time is overlapping with another animation.");
    }
  }


}
