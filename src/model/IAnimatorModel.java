package model;

import java.util.List;

import model.command.ICommands;
import model.shape.AShape;

public interface IAnimatorModel<K> extends IAnimatorModelState<K> {
  void addShape(String id, AShape s);

  void addCommands(String id, List<ICommands> a);
}
