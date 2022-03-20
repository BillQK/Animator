package model;

public interface IAnimatorModel<K> extends IAnimatorModelState<K> {
  void addShape(String id, IShape s);

  void addAnimations(String id, IAnimations a);
}
