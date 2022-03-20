package model;

import model.animation.IAnimations;
import model.shape.AShape;

public interface IAnimatorModel<K> extends IAnimatorModelState<K> {
  void addShape(String id, AShape s);

  void addAnimations(String id, IAnimations a);
}
