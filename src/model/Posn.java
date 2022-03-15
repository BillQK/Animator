package model;

import java.util.Objects;

/**
 * A Posn class.
 */
public class Posn {
  int x;
  int y;

  /**
   * A constructor for Posn.
   *
   * @param x int x
   * @param y int y
   */
  public Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * A copy constructor.
   *
   * @param posn a Posn
   */
  public Posn(Posn posn) {
    this.x = posn.x;
    this.y = posn.y;
  }

  /**
   * A method to get the X coordinate.
   *
   * @return an int
   */
  public int getX() {
    return x;
  }

  /**
   * A method to get the X coordinate.
   *
   * @return an int
   */
  public int getY() {
    return y;
  }

  /**
   * A method that moves the current to a new x and y.
   *
   * @param dx A new x coordinate
   * @param dy A new y coordinate
   * @return a Posn
   */
  public Posn moved(int dx, int dy) {
    return new Posn(dx, dy);
  }

  /**
   * A method that takes in a Posn and subtract that position.
   *
   * @param other a Posn
   * @return a new Posn
   */
  public Posn minus(Posn other) {
    return new Posn(this.x - other.getX(), this.y - other.getY());
  }

  /**
   * A method that takes in a Posn and plus that position.
   *
   * @param other a Posn
   * @return a new Posn
   */
  public Posn plus(Posn other) {
    return new Posn(this.x + other.getX(), this.y - other.getY());
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null) {
      return false;
    } else {
      Posn posn = (Posn) other;
      return Objects.equals(this.x, posn.x) && Objects.equals(this.y, posn.y);
    }
  }

  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }


}
