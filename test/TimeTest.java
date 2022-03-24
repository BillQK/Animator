import org.junit.Test;

import model.utils.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Time class.
 */
public class TimeTest {

  double startTime = 10;
  double endTime = 40;
  double DELTA = Integer.MIN_VALUE;

  @Test
  public void testGetStartTime() {
    Time t = new Time(startTime, endTime);
    assertEquals(t.getStartTime(), startTime, DELTA);
  }

  @Test
  public void testGetStartTime1() {
    Time t = new Time(endTime);
    assertEquals(t.getStartTime(), 0, DELTA);
  }

  @Test
  public void testGetEndTime() {
    Time t = new Time(startTime, endTime);
    assertEquals(t.getEndTime(), endTime, DELTA);
  }

  @Test
  public void testGetEndTime1() {
    Time t = new Time(endTime);
    assertEquals(t.getEndTime(), endTime, DELTA);
  }

  @Test
  public void testEqual() {
    Time t = new Time(startTime, endTime);
    Time t1 = new Time(10, 40);
    assertTrue(t.equals(t1));
  }

  @Test
  public void testEqual1() {
    Time t = new Time(0, endTime);
    Time t1 = new Time(40);
    assertTrue(t.equals(t1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGivenStartBiggerThanEndTime() {
    Time t = new Time(40, 30);
    assertEquals(t.getEndTime(), 30, DELTA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGivenNegStartTime() {
    Time t = new Time(-10, endTime);
    assertEquals(t.getEndTime(), endTime, DELTA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGivenNegEndTime() {
    Time t = new Time(startTime, -10);
    assertEquals(t.getStartTime(), startTime, DELTA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGivenNegEndAndStartTime() {
    Time t = new Time(-10, -10);
    assertEquals(t.getStartTime(), startTime, DELTA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGivenNegEndTime1Argument() {
    Time t = new Time( -10);
    assertEquals(t.getStartTime(), startTime, DELTA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGivenNullTime() {
    Time t = new Time(null);
    assertEquals(t.getStartTime(), startTime, DELTA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGivenNegTime1() {
    Time t1 = new Time(-10, endTime);
    Time t = new Time(t1);
    assertEquals(t.getStartTime(), startTime, DELTA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGivenNegTime2() {
    Time t1 = new Time(startTime, -10);
    Time t = new Time(t1);
    assertEquals(t.getStartTime(), startTime, DELTA);
  }
}
