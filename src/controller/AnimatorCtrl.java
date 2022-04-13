package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.*;

import model.IAnimatorModel;
import view.IAnimatorView;
import model.shape.AShape;

public class AnimatorCtrl implements IAnimatorController, ActionListener {
  private IAnimatorModel model;
  private IAnimatorView view;
  private final JOptionPane popUp;
  private double tempo;
  private boolean isLoop;
  private Timer timer;
  private List<AShape> loShapes;
  private int sec;
  private Scanner s;

  public AnimatorCtrl(IAnimatorModel model, IAnimatorView view, double tempo, String... args) {
    this.model = model;
    this.view = view;
    this.popUp = new JOptionPane();
    this.tempo = tempo;
    this.isLoop = false;
    this.timer = null;
    this.loShapes = null;
    //Also the second and tempo, are they the same field or not?
    this.sec = 1;
    //Not sure about the scanner
    this.s = new Scanner(System.in);
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
        //
        break;
    }
  }

  @Override
  public void start() {

  }
}
