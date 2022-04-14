package controller;

import javax.swing.Timer;

public class MockController implements IAnimatorController {
  final StringBuilder log;

  public MockController(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void start() {
    log.append("Controller start");
  }

  @Override
  public Timer getTimer() {
    log.append("Get the build-in timer");
    return null;
  }

  @Override
  public double getSpeed() {
    log.append("Get the Speed of the Model");
    return 0;
  }
}
