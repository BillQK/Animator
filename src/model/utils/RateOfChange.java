package model.utils;

/**
 * Represents the calculation of one method to find the rate of change.
 */
public final class RateOfChange {

  /**
   * Find the rate of change for the commands called.
   * If current is less than start, return zero.
   *
   * @param current the current double
   * @param start   the start double
   * @param end     the end double
   * @return a double Rate
   */
  public static double findRate(double current, double start, double end) {
    if (current <= start) {
      return 0;
    }
    return Math.abs((current - start) / (end - start));
  }

}
