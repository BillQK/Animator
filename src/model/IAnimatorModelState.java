package model;


import java.util.List;

import model.command.ICommands;
import model.shape.AShape;
import model.utils.Time;

public interface IAnimatorModelState<K> {
  String getState();

  List<AShape> getShapes();

  List<List<ICommands>> getCommands();

  Time getTime();
}
