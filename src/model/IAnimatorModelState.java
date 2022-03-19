package model;


import java.util.List;

public interface IAnimatorModelState<K> {
  String getDescription();

  List<IShape> getShapes();

  List<IAnimations> getAnimations();

  Time getTime();

}
