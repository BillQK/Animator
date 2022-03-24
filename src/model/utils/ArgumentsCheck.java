package model.utils;

public class ArgumentsCheck {

  /**
   * Check if the inputs is less than zero.
   *
   * @param args the multiple input (double) put in to check
   */
  public static void lessThanZero(double... args) {
    for (double a : args) {
      if (a < 0) {
        throw new IllegalArgumentException("Cannot be less than Zero");
      }
    }
  }

  /**
   * Check if the inputs is an empty String
   *
   * @param args the multiple input (String) put in to check
   */
  public static void emptyString(String... args) {
    for (String s : args) {
      if (s.equals("")) {
        throw new IllegalArgumentException("Cannot be empty");
      }
    }
  }

  /**
   * Check if the inputs is an invalid color parameter
   *
   * @param args the multiple input (int) put in to check
   */
  public static void colorRange(int... args) {
    for (int i : args) {
      if (i < 0 || i > 255) {
        throw new IllegalArgumentException("Invalid color parameter");
      }
    }
  }

  /**
   * Check if one given start and end time is within
   * the range of another given start and end time.
   *
   * @param shapeStart the range start time
   * @param shapeEnd   the range end time
   * @param comStart   the second start time to see if it is in the range time
   * @param comEnd     the second end time to see if it is in the range time
   */
  public static void withinIntervalTime(double shapeStart, double shapeEnd, double comStart, double comEnd) {
    if (comStart < shapeStart || comEnd > shapeEnd || comStart > comEnd || shapeStart > shapeEnd) {
      throw new IllegalArgumentException("Time does not range within the bigger time frame.");
    }
  }

  /**
   * Check if one given start and end time is not overlapping with
   * another start and end time.
   *
   * @param shapeStart the first start time
   * @param shapeEnd   the first end time
   * @param comStart   the second start time to see
   *                   if it's overlap or in the range of the first time
   * @param comEnd     the second end time to see
   *                   if it's overlap or in the range of the first time
   */
  public static void overlappingTime(double shapeStart, double shapeEnd,
                                     double comStart, double comEnd) {
//    if ((shapeStart < comEnd && shapeEnd > comEnd)
//            || (shapeStart < comStart && shapeEnd > comStart)
//            || (comStart > comEnd) || (shapeStart > shapeEnd)
//            || (comStart < shapeStart && comEnd > shapeEnd)
//            || (comStart == shapeStart && comEnd == shapeEnd))
    if (shapeEnd <= shapeStart || comEnd <= comStart) {
      throw new IllegalArgumentException("Invalid time range");
    }
    //    if (shapeStart <= comEnd && comStart <= shapeEnd) {
    if (shapeStart < comEnd && comStart < shapeEnd) {
      throw new IllegalArgumentException("Time is overlapping with another animation.");
    }
  }
}
