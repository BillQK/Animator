package controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import model.IAnimatorModel;
import model.shape.AShape;
import model.utils.AnimationStart;
import model.utils.Tempo;
import view.IAnimatorView;

/**
 * A Visual View Controller class. This controller offers a visual view class which
 * depends on the input from the user which will run the animation in the
 * visual with no control or manipulate on the view.
 */
public class VisualCtrl implements IAnimatorController {
  private final IAnimatorModel model;
  private final IAnimatorView view;
  private final double speed;
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
    ms.addAll(model.getShapes());

    ActionListener timeListner = ae -> {
      AnimationStart.execute(ms, model, t, view);
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
