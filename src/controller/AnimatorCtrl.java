package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.Timer;

import model.IAnimatorModelState;
import view.IAnimatorView;
import model.shape.AShape;

public class AnimatorCtrl implements IAnimatorController {
  private IAnimatorModelState model;
  private IAnimatorView view;
  private double tempo;
  private boolean isLoop;
  private Timer timer;
  private List<AShape> newListShapes;

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
}
