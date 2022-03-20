package model;

public class Time {
  int start;
  int end;

  public Time(int start, int end) {
    if (start < 0 || end < 0 || end <= start) {
      throw new IllegalArgumentException("Start and End time cannot be negative," +
              "and End time cannot be less than or equal to start");
    }
    this.start = start;
    this.end = end;
  }
}