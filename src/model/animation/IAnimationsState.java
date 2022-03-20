package model.animation;

import model.shape.AShape;
import model.utils.Time;

public interface IAnimationsState {
  String getBeginsState();

  String getEndsState();

  String getState();

  AShape getShape();

  Time getTime();

  AnimationType getType();

  int getStart();

  int getEnd();

}
