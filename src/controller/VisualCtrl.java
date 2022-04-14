package controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import model.IAnimatorModel;
import model.command.ICommands;
import model.shape.AShape;
import model.utils.Tempo;
import view.IAnimatorView;

/**
 * A Visual View Controller class. This controller offers a visual view class which
 * depends on the input from the user which will run the animation in the
 * visual with no control or manipulate on the view.
 */
public class VisualCtrl implements IAnimatorController {
  private IAnimatorModel model;
  private IAnimatorView view;
  private double speed;
  private Tempo t;
  private Timer timer;
  private List<AShape> ms;

  /**
   * Constructor for VisualCtrl with the given model, view and speed.
   *
   * @param model IAnimatorModel - the given model
   * @param view IAnimatorView - the given view
   * @param speed double - the view given speed
   */
  public VisualCtrl(IAnimatorModel model, IAnimatorView view, double speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;
    this.t = null;
    this.timer = null;
  }

  /**
   * Method to start the controller and send the information to view to know what to show.
   */
  @Override
  public void start() {
    this.t = new Tempo(this.speed);


    ms = new ArrayList<>();
    for (AShape sh : model.getShapes()) {
      ms.add(sh);
    }

    ActionListener timeListner = ae -> {
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
    };

    this.timer = new Timer(1000 / (int) this.speed, timeListner);
    timer.start();
  }

  /**
   * Method to get the build-in timer for the controller/view.
   *
   * @return Timer - the system time
   */
  @Override
  public Timer getTimer() {
    return this.timer;
  }

  /**
   * Method to get the speed that the user put in.
   *
   * @return double - the tempo speed
   */
  @Override
  public double getSpeed() {
    return this.speed;
  }

}
