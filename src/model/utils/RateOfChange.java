package model.utils;

public class RateOfChange {
  public static double findRate(double current, double start, double end) {
    return (current - start) / (end - start);
  }

}
