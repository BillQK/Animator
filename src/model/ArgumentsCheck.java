package model;

public class ArgumentsCheck {
  public static void lessThanZero(int... args) {
    for (int a : args) {
      if (a < 0) {
        throw new IllegalArgumentException("Cannot be less than Zero");
      }
    }
  }
}
