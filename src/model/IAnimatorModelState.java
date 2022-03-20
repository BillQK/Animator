package model;


import java.util.List;

import model.animation.IAnimations;
import model.shape.AShape;
import model.utils.Time;

public interface IAnimatorModelState<K> {
  String getState();

  List<AShape> getShapes();

  List<IAnimations> getAnimations();

  Time getTime();

}
