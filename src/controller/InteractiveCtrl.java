package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import model.IAnimatorModel;
import view.IAnimatorView;
import model.shape.AShape;

public class InteractiveCtrl implements IAnimatorController, ActionListener {
  private IAnimatorModel model;
  private IAnimatorView view;
  private final JOptionPane popUp;
  private double tempo;
  private boolean isLoop;
  private Timer timer;
  private double lastCmdTime;

  public InteractiveCtrl(IAnimatorModel model, IAnimatorView view, double tempo, String... args) {
    this.model = model;
    this.view = view;
    this.popUp = new JOptionPane();
    this.tempo = tempo;
    this.isLoop = false;
    this.timer = null;
    this.lastCmdTime = 0;
    //Not sure about the scanner

  }

  @Override
  public void start() {

    this.view.setListener(this);

  }

  @Override
  public Timer getTimer() {
    return null;
  }

  @Override
  public double getTempo() {
    return 0;
  }

  public void actionPerformed(ActionEvent ae) {
    switch (ae.getActionCommand()) {
      case "Start Button":
        //
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
    }
  }

}
