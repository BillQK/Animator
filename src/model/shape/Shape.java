package model.shape;

/**
 * This enumerated type represents the two different shapes in the model: Rectangle and Ellipse.
 */
public enum Shape {
  RECTANGLE("Rectangle"), ELLIPSE("Ellipse");

  private final String type;

  /**
   * The constructor for the enum class.
   * @param type the given type of the shape
   */
  Shape(String type) {
    if (type == null || type.equals("")) {
      throw new IllegalArgumentException("The type of the shape cannot be null or empty");
    }
    this.type = type;
  }

  /**
   * Get the string of the given shape type.
   * @return the String of the given shape type
   */
  public String getShapeType() {
    return type;
  }

}

