package model;

public enum Shape {
  RECTANGLE("Rectangle"), ELLIPSE("Ellipse");

  private final String type;

  Shape(String type) {
    this.type = type;
  }
}

