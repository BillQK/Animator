package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.Timer;

import model.IAnimatorModel;
import view.IAnimatorView;
import model.shape.AShape;

public class AnimatorCtrl implements IAnimatorController, ActionListener {
  private IAnimatorModel model;
  private IAnimatorView view;
  private double tempo;
  private boolean isLoop;
  private Timer timer;
  private List<AShape> loShapes;

  public void actionPerformed(ActionEvent ae) {
    switch (ae.getActionCommand()) {
      case "Start Button":
        //
        break;
      case "Pause Button":
        //
        break;
      case "Restart Button":
        //
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
