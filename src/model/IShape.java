package model;

import java.awt.*;
import java.sql.Time;

/**
 *
 */
public interface IShape {

  Shape getType();

  Posn getPosition();

  Color getColor();
  // TODO: Don't know how to implement this
  void getTick();

  int getHeight();

  int getWidth();
}
