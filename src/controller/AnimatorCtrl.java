package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import model.IAnimatorModel;
import view.IAnimatorView;
import model.shape.AShape;

public class AnimatorCtrl implements IAnimatorController {
  private IAnimatorModel model;
  private IAnimatorView view;
  private double tempo;
  private boolean isLoop;
  private Timer timer;
  private List<AShape> loShapes;

  public void actionPerformed(ActionEvent ae) {
    switch (ae.getActionCommand()) {
      case "Start Button":
<<<<<<< HEAD
        this.timer.start();
=======

>>>>>>> 0bcb8650f667cea987d563784484ad5f78081219
        break;
      case "Pause Button":
        this.timer.stop();
        break;
      case "Restart Button":
        this.timer.restart();
        break;
      case "SpeedUp Button":
        //
        break;
      case "SpeedDown Button":
        //
        break;
      case "Loop Button":
        //
        break;
    }
  }

  @Override
  public void start() {

  }
}
