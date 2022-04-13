package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import model.IAnimatorModel;
import model.command.ICommands;
import model.io.AnimationFileReader;
import model.utils.Tempo;
import view.IAnimatorView;
import model.shape.AShape;

public class InteractiveCtrl implements IAnimatorController, ActionListener {
  private IAnimatorModel model;
  private IAnimatorView view;

  private final JOptionPane popUp;

  private double tempo;
  private Timer timer;
  private Tempo t;

  private boolean isLoop;
  private double lastCmdTime;

  public InteractiveCtrl(IAnimatorModel model, IAnimatorView view, double tempo) {
    this.model = model;
    this.view = view;
    this.popUp = new JOptionPane();

    this.tempo = tempo;
    this.timer = null;
    this.t = null;

    this.isLoop = false;
    this.lastCmdTime = model.getLastTimeCommands();
  }

  @Override
  public void start() {

    this.view.setListener(this);

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

  public void actionPerformed(ActionEvent ae) {
    switch (ae.getActionCommand()) {
      case "Start Button":
        this.timer.start();
        break;
      case "Pause Button":
        this.timer.stop();
        break;
      case "Restart Button":
        //model reset
        this.timer.restart();
        break;
      case "SpeedUp Button":
        this.tempo += 10;
        break;
      case "SpeedDown Button":
        if (this.tempo <= 0) {
          this.tempo = 1;
        } else {
          this.tempo -= 10;
        }
        break;
      case "Loop Button":
        //model finished -> model reset
        break;
      default:
        throw new IllegalArgumentException("Button cannot applied");
    }
  }

}
