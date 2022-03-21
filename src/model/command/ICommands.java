package model.command;

public interface ICommands extends ICommandsState {
  void execute(double time);

}
