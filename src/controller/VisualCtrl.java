package controller;

import javax.swing.*;

import model.IAnimatorModel;
import view.IAnimatorView;

public class VisualCtrl implements IAnimatorController {
  private IAnimatorModel model;
  private IAnimatorView view;
  private double tempo;

  VisualCtrl(IAnimatorModel model, IAnimatorView view, double tempo) {
    this.model = model;
    this.view = view;
    this.tempo = tempo;
  }

  @Override
  public void start() {
    long starttime = System.currentTimeMillis();
  }

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }

  @Override
  public double getTempo() {
    return this.tempo;
  }
}
