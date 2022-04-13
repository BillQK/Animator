package controller;

import java.awt.event.ActionListener;

import javax.swing.*;

import model.IAnimatorModel;
import model.command.ACommand;
import model.utils.Tempo;
import view.IAnimatorView;

public class VisualCtrl implements IAnimatorController {
  private IAnimatorModel model;
  private IAnimatorView view;
  private double tempo;
  private Tempo t;
  private Timer timer;

  VisualCtrl(IAnimatorModel model, IAnimatorView view, double tempo) {
    this.model = model;
    this.view = view;
    this.tempo = tempo;
    this.t = null;
    this.timer = null;
  }

  @Override
  public void start() {
    this.t = new Tempo(this.tempo);

    IAnimatorModel finalmodel = model;
    IAnimatorView finalview = view;

    view.makeVisible();

    ActionListener timeListner = ae -> {

    }

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
