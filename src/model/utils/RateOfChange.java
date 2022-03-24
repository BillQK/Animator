package model.utils;

/**
 * Represents the calculation of one method to find the rate of change.
 */
public final class RateOfChange {
  public static double findRate(double current, double start, double end) {
    if (current < start) {
      return 0;
    }
    return Math.abs((current - start) / (end - start));
  }

}
