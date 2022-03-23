package model.command;

public interface ICommands extends ICommandsState, Comparable<ICommands> {
  void execute(double time);

}
