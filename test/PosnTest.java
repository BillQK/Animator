import org.junit.Test;

import model.utils.Posn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the test class for Posn class.
 */
public class PosnTest {
  Posn posn;
  Posn mtposn;

  //Test get the X position.
  @Test
  public void getX() {
    posn = new Posn(1, 2);
    assertEquals(posn.getX(), 1);
  }

  //Test get the Y position.
  @Test
  public void getY() {
    posn = new Posn(1, 2);
    assertEquals(posn.getY(), 2);
  }

  //Test create a new Posn with the given X and Y.
  @Test
  public void moved() {
    posn = new Posn(1, 2);
    assertEquals(posn.moved(3,2).getX(), 3);
    assertEquals(posn.moved(3,2).getY(), 2);
  }

  //Test minus the given position to get a new position.
  @Test
  public void minus() {
    posn = new Posn(1, 2);
    posn.minus(new Posn(1, 2));
    assertEquals(posn.getY(), 0);
    assertEquals(posn.getX(), 0);

  }

  //Test plus the given position to get a new position.
  @Test
  public void plus() {
    posn = new Posn(1, 2);
    posn.plus(new Posn(2, 2));
    assertEquals(posn.getX(), 3);
    assertEquals(posn.getY(), 4);
  }

  //Test check if two position is equals.
  @Test
  public void testEquals() {
    posn = new Posn(1, 2);
    mtposn = new Posn();
    assertTrue(posn.equals(new Posn(1, 2)));
  }

  //Test check if two position is equals with the default constructor.
  @Test
  public void testEquals1() {
    mtposn = new Posn();
    assertTrue(mtposn.equals(new Posn(0, 0)));
  }

}