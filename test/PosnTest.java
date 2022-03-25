import org.junit.Test;

import model.utils.Posn;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for Posn class.
 */
public class PosnTest {
  Posn posn;
  Posn mtposn;
  double delta = Integer.MIN_VALUE;

  //Test get the X position.
  @Test
  public void getX() {
    posn = new Posn(1, 2);
    assertEquals(posn.getX(), 1, delta);
  }

  //Test get the Y position.
  @Test
  public void getY() {
    posn = new Posn(1, 2);
    assertEquals(posn.getY(), 2, delta);
  }

  //Test create a new Posn with the given negative X and Y.
  @Test
  public void movedNegX() {
    posn = new Posn(1, 2);
    assertEquals(posn.moved(-3, 2).getY(), 2, delta);
  }

  //Test create a new Posn with the given X and negative Y.
  @Test
  public void movedNegY() {
    posn = new Posn(1, 2);
    assertEquals(posn.moved(3, -2).getX(), 3, delta);
  }

  //Test create a new Posn with the given X and Y.
  @Test
  public void moved() {
    posn = new Posn(1, 2);
    assertEquals(posn.moved(3, 2).getX(), 3, delta);
    assertEquals(posn.moved(3, 2).getY(), 2, delta);
  }

  //Test set the Posn to a new Posn with the given negative X and Y.
  @Test
  public void setPosnNegX() {
    posn = new Posn(1, 2);
    posn.setPosn(-3, 2);
    assertEquals(posn.getY(), 2, delta);
  }

  //Test set the Posn to a new Posn with the given X and negative Y.
  @Test
  public void setPosnNegY() {
    posn = new Posn(1, 2);
    posn.setPosn(3, -2);
    assertEquals(posn.getX(), 3, delta);
  }

  //Test set the Posn to a new Posn with the given X and Y.
  @Test
  public void setPosn() {
    posn = new Posn(1, 2);
    posn.setPosn(3, 2);
    assertEquals(posn.getX(), 3, delta);
    assertEquals(posn.getY(), 2, delta);
  }

  //Test minus the given position to get a new position.
  @Test
  public void minus() {
    posn = new Posn(1, 2);
    posn.minus(new Posn(1, 2));
    assertEquals(posn.getY(), 0, delta);
    assertEquals(posn.getX(), 0, delta);
  }

  //Test minus the given Null position to get a new position.
  @Test(expected = IllegalArgumentException.class)
  public void minusNull() {
    posn = new Posn(1, 2);
    posn.minus(null);
    assertEquals(posn.getY(), 1, delta);
    assertEquals(posn.getX(), 2, delta);
  }

  //Test plus the given position to get a new position.
  @Test
  public void plus() {
    posn = new Posn(1, 2);
    posn.plus(new Posn(2, 2));
    assertEquals(posn.getX(), 3, delta);
    assertEquals(posn.getY(), 4, delta);
  }

  //Test minus the given Null position to get a new position.
  @Test(expected = IllegalArgumentException.class)
  public void plusNull() {
    posn = new Posn(1, 2);
    posn.plus(null);
    assertEquals(posn.getY(), 1, delta);
    assertEquals(posn.getX(), 2, delta);
  }

  //Test check if two position is equals.
  @Test
  public void testEquals() {
    posn = new Posn(1, 2);
    mtposn = new Posn();
    assertEquals(posn, new Posn(1, 2));
  }

  //Test check if two position is equals with the default constructor.
  @Test
  public void testEquals1() {
    mtposn = new Posn();
    assertEquals(mtposn, new Posn(0, 0));
  }

  //Test given negative x position
  @Test
  public void testNegXPosn() {
    posn = new Posn(-10, 5);
    assertEquals(posn.getY(), 5, delta);
  }

  //Test given negative y position
  @Test
  public void testNegYPosn() {
    posn = new Posn(10, -5);
    assertEquals(posn.getX(), 10, delta);
  }

  //Test given negative x and y position
  @Test
  public void testNegXYPosn() {
    posn = new Posn(-10, -5);
    assertEquals(posn.getX(), -10, delta);
  }

  //Test given null position constructor
  @Test(expected = IllegalArgumentException.class)
  public void testNullPosnConstructor() {
    posn = new Posn(null);
    assertEquals(posn.getX(), 10, delta);
  }

  //Test given negative position X constructor
  @Test
  public void testNullPosnXConstructor() {
    mtposn = new Posn(-10, 0);
    posn = new Posn(mtposn);
    assertEquals(posn.getX(), -10, delta);
  }

  //Test given negative position Y constructor
  @Test
  public void testNullPosnYConstructor() {
    mtposn = new Posn(10, -10);
    posn = new Posn(mtposn);
    assertEquals(posn.getX(), 10, delta);
  }

}