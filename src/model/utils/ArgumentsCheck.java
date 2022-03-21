package model.utils;

public class ArgumentsCheck {
  public static void lessThanZero(double... args) {
    for (double a : args) {
      if (a < 0) {
        throw new IllegalArgumentException("Cannot be less than Zero");
      }
    }
  }

  public static void emptyString(String... args) {
    for (String s : args) {
      if (s.equals("")) {
        throw new IllegalArgumentException("Cannot be empty");
      }
    }
  }

  public static void withinShapeTime(double shapeStart, double shapeEnd,
                                     double comStart, double comEnd) {
    if (comStart < shapeStart || comEnd > shapeEnd) {
      throw new IllegalArgumentException("Time does not range within the bigger time frame.");
    }
  }

  // 30 - 40 (0 - 50)
  // a - b

  //3 - 20
  //1 - 17



  public static void overlappingTime(double shapeStart, double shapeEnd,
                                     double comStart, double comEnd) {
//    if ((comEnd > shapeStart && comStart <= comEnd) || (comStart < shapeEnd && comStart <= comEnd)) {
//      throw new IllegalArgumentException("Time is overlapping with another animation.");
//    }

//    if (!(shapeStart >= comStart && shapeEnd <= comEnd)) {
//      throw new IllegalArgumentException("Time is overlapping with another animation.");
//    }

    if ()
  }
}
