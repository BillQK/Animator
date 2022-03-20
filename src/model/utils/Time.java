package model.utils;

import java.util.Objects;

import model.utils.ArgumentsCheck;

public class Time {
  int start;
  int end;

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