package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import model.IAnimatorModel;
import model.SimpleAnimatorModel;
import model.command.ICommands;
import model.io.AnimationFileReader;
import model.utils.Tempo;
import view.AnimatorViewCreator;
import view.IAnimatorView;
import model.shape.AShape;

public class InteractiveCtrl implements IAnimatorController, ActionListener {
  private IAnimatorModel model;
  private IAnimatorView view;
  private final JOptionPane popUp;
  List<AShape> ms;

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

    this.createNewModel();

    this.timer = new Timer(1000 / (int) this.tempo, ac);
    timer.start();
  }

  private void createNewModel() {

    ms = new ArrayList<>();
    for (AShape sh : model.getShapes()) {
      ms.add(sh);
    }
  }

  ActionListener ac = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

      if (isLoop && (lastCmdTime - t.getTempo() < 0.000001)) {
        timer.restart();
        createNewModel();
        t = new Tempo(0);
      } else if (lastCmdTime - t.getTempo() < 0.000001) {
        timer.stop();
      }

      List<ICommands> com = null;
      for (AShape modelS : ms) {
        com = model.getExecutableCommand(modelS.getName());
        for (int j = 0; j < com.size(); j++) {
          ICommands c = com.get(j);
          AShape modelShape = c.getShape();
          for (int i = 0; i < ms.size(); i++) {
            if (modelShape.getName().equals(ms.get(i).getName())) {
              c.setShape(ms.get(i));
            }
          }
        }
      }

      List<AShape> losTempo = new ArrayList<>();
      for (AShape mShape : ms) {
        for (ICommands c : model.getExecutableCommand(mShape.getName())) {
          if (t.getTempo() >= c.getStart() && t.getTempo() <= c.getEnd()) {
            c.execute(t.getTempo());
          }
        }
        losTempo.add(mShape);
      }

      view.setShapes(losTempo);
      view.refresh();
      t.addTempo();
      System.out.println(t.getTempo());

      view.makeVisible();
    }
  };


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
        this.timer.restart();
        this.createNewModel();
        this.t = new Tempo(0);
        break;
      case "SpeedUp Button":
        this.tempo += 10;
        this.timer.setDelay(1000 / (int) this.tempo);
        break;
      case "SpeedDown Button":
        if (this.tempo <= 0) {
          this.timer.setDelay(this.timer.getDelay());
        } else {
          if (this.tempo - 10 <= 0) {
            this.timer.setDelay(this.timer.getDelay());
          } else {
            this.tempo -= 10;
            this.timer.setDelay(1000 / (int) this.tempo);
          }
        }
        break;
      case "Loop Button":
        this.isLoop = !isLoop;
        view.setIsLoop(isLoop);
        break;
      default:
        throw new IllegalArgumentException("Button cannot applied");
    }
  }

}
