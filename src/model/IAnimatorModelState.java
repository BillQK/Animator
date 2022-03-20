package model;


import java.util.List;

public interface IAnimatorModelState<K> {
  String getState();

  List<IShape> getShapes();

  List<IAnimations> getAnimations();

  Time getTime();

}
