package controller;

import javax.swing.*;

import model.IAnimatorModel;
import view.IAnimatorView;

public class TextCtrl implements IAnimatorController {
  private IAnimatorView view;
  private final String filename;

  public TextCtrl(IAnimatorView view, String filename) {
    this.view = view;
    this.filename = filename;
  }

  @Override
  public void start() {
    if (filename == null) {
      System.out.println(this.view.getDetails());
    }
    this.view.writeFile(filename);
  }

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }

  @Override
  public double getTempo() {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }
}
