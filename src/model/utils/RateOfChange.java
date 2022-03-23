package model.utils;

/**
 * Represents the calculation of one method to find the rate of change.
 */
public class RateOfChange {
  public static double findRate(double current, double start, double end) {
    return (current - start) / (end - start);
  }

}
