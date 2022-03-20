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
    ArgumentsCheck.lessThanZero(x, y);
    this.x = x;
    this.y = y;
  }

  /**
   * An empty argument constructor for Posn.
   */
  public Posn() {
    this.x = 0;
    this.y = 0;
  }

  /**
   * A copy constructor.
   *
   * @param posn a Posn
   */
  public Posn(Posn posn) {
    if (posn == null) {
      throw new IllegalArgumentException("Posn cannot be null");
    }
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
    ArgumentsCheck.lessThanZero(dx, dy);
    return new Posn(dx, dy);
  }

  /**
   * A method to set the current position to the new given position. MUTATING THE POSN!!.
   *
   * @param x the given new x coordinate
   * @param y the given new y coordinate
   */
  public void setPosn(int x, int y) {
    ArgumentsCheck.lessThanZero(x, y);
    this.x = x;
    this.y = y;
  }

  /**
   * A method that takes in a Posn and subtract that position. MUTATING THE POSN!!.
   *
   * @param other a Posn
   */
  public void minus(Posn other) {
    if (other == null) {
      throw new IllegalArgumentException("The given Posn cannot be null");
    }
     this.x -= other.getX();
    this.y -= other.getY();
  }

  /**
   * A method that takes in a Posn and plus that position. MUTATING THE POSN!!.
   *
   * @param other a Posn
   */
  public void plus(Posn other) {
    if (other == null) {
      throw new IllegalArgumentException("The given Posn cannot be null");
    }
    this.x += other.getX();
    this.y += other.getY();
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
