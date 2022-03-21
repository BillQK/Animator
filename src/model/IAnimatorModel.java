package model;

import model.command.ICommands;
import model.shape.AShape;

public interface IAnimatorModel<K> extends IAnimatorModelState<K> {
  void addShape(String id, AShape s);

  void addAnimations(String id, ICommands a);
}
