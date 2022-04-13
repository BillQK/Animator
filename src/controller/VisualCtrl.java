package controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.IAnimatorModel;
import model.command.ICommands;
import model.shape.AShape;
import model.utils.Tempo;
import view.IAnimatorView;

public class VisualCtrl implements IAnimatorController {
  private IAnimatorModel model;
  private IAnimatorView view;
  private double tempo;
  private Tempo t;
  private Timer timer;

  public VisualCtrl(IAnimatorModel model, IAnimatorView view, double tempo) {
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
      List<AShape> losTempo = new ArrayList<>();
      for (AShape s : finalmodel.getShapes()) {
        for (ICommands c : finalmodel.getExecutableCommand(s.getName())) {
          if (t.getTempo() >= c.getStart() && t.getTempo() <= c.getEnd()) {
            c.execute(t.getTempo());
          }
        }
        losTempo.add(s);
      }

      finalview.setShapes(losTempo);
      System.out.println(t.getTempo());
      finalview.refresh();
      t.addTempo();
    };


    this.timer = new Timer(1000 / (int) this.tempo, timeListner);
    timer.start();
  }

  @Override
  public Timer getTimer() {
    return this.timer;
  }

  @Override
  public double getTempo() {
    return this.tempo;
  }

}
