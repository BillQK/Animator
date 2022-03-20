package model.utils;

import model.utils.ArgumentsCheck;

import java.util.Objects;

public class Time {
  private final int start;
  private final int end;

  public Time(int start, int end) {
    if (end <= start) {
      throw new IllegalArgumentException("Start and End time cannot be negative," +
              "and End time cannot be less than or equal to start");
    }
    ArgumentsCheck.lessThanZero(start, end);
    this.start = start;
    this.end = end;
  }

  public Time(int end) {
    ArgumentsCheck.lessThanZero(end);
    this.start = 0;
    this.end = end;
  }

  public int getStartTime() {
    return start;
  }

  public int getEndTime() {
    return end;
  }

  public String getStartToString() {
    return String.valueOf(this.start);
  }

  public String getEndToString() {
    return String.valueOf(this.end);
  }

  public boolean equals(Object that) {
    if (this == that ) {
      return true;
    } else if (that == null) {
      return false;
    } else {
      Time time = (Time) that;
      return Objects.equals(this.start, time.start) && Objects.equals(this.end, time.end);
    }
  }

  public int hashCode() {
    return Objects.hash(this.start, this.end);
  }
}