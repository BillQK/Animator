package model;

public interface IAnimatorModel<K> extends IAnimatorModelState<K> {
  void addShape(IShape s);

  void addAnimations(IAnimations a);
}
