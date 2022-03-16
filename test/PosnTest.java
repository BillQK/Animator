import org.junit.Test;

import model.Posn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PosnTest {
  Posn posn;

  @Test
  public void getX() {
    posn = new Posn(1, 2);
    assertEquals(posn.getX(), 1);
  }

  @Test
  public void getY() {
    posn = new Posn(1, 2);
    assertEquals(posn.getY(), 2);
  }

  @Test
  public void moved() {
    posn = new Posn(1, 2);
    assertEquals(posn.moved(3, 2).getX(), 3);
    assertEquals(posn.moved(3, 2).getY(), 2);
  }

  @Test
  public void minus() {
    posn = new Posn(1, 2);
    posn.minus(new Posn(1, 2));
    assertEquals(posn.getY(), 0);
    assertEquals(posn.getX(), 0);

  }

  @Test
  public void plus() {
    posn = new Posn(1, 2);
    posn.plus(new Posn(2, 2));
    assertEquals(posn.getX(), 3);
    assertEquals(posn.getY(), 4);
  }

  @Test
  public void testEquals() {
    posn = new Posn(1, 2);
    assertTrue(posn.equals(new Posn(1, 2)));
  }

}