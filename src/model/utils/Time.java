package model.utils;

import java.util.Objects;

/**
 * A Time class.
 */
public class Time {
  double start;
  double end;

  /**
   * A constructor for Time.
   *
   * @param start the given start time
   * @param end the given end time
   */
  public Time(double start, double end) {
    if (end <= start) {
      throw new IllegalArgumentException("Start and End time cannot be negative," +
              "and End time cannot be less than or equal to start");
    }
    ArgumentsCheck.lessThanZero(start, end);
    this.start = start;
    this.end = end;
  }

  /**
   * Another constructor for Time where we hard code the start Time
   * to be zero.
   *
   * @param end the given end time
   */
  public Time(double end) {
    ArgumentsCheck.lessThanZero(end);
    this.start = 0;
    this.end = end;
  }

  /**
   * Another constructor for Time.
   *
   * @param time the given time
   */
  public Time(Time time) {
    if (time == null) {
      throw new IllegalArgumentException("Time cannot be null");
    }
    this.start = time.start;
    this.end = time.end;
  }

  /**
   * A method to get the start time.
   *
   * @return a double - the start time.
   */
  public double getStartTime() {
    return start;
  }

  /**
   * A method to get the end time.
   *
   * @return a double - the end time.
   */
  public double getEndTime() {
    return end;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null) {
      return false;
    } else {
      Time time = (Time) other;
      return Objects.equals(this.start, time.start) && Objects.equals(this.end, time.end);
    }
  }

  public int hashCode() {
    return Objects.hash(this.start, this.end);
  }

}