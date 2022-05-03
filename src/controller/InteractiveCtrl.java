package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import model.IAnimatorModel;
import model.utils.AnimationStart;
import model.utils.Tempo;
import view.IAnimatorView;
import model.shape.AShape;

/**
 * An Interactive Visual View Controller class. This controller offers a visual view class
 * with button click which user can press the button to manipulate the view to start, pause,
 * resume, restart, looping, increase and decrease the speed.
 */
public class InteractiveCtrl implements IAnimatorController, ActionListener, KeyListener {
  private final IAnimatorModel model;
  private final IAnimatorView view;
  private List<AShape> ms;

  private double speed;
  private Timer timer;
  private Tempo t;

  private boolean isLoop;
  private boolean isOutline;
  private boolean isDiscreteT;
  private boolean isSlowMo;
  private final double lastCmdTime;

  /**
   * Constructor for InteractiveCtrl with the given model, view and speed.
   *
   * @param model IAnimatorModel - the given model
   * @param view  IAnimatorView - the given view
   * @param speed double - the view given speed
   */
  public InteractiveCtrl(IAnimatorModel model, IAnimatorView view, double speed) {
    this.model = model;
    this.view = view;

    this.speed = speed;
    this.timer = null;
    this.t = null;

    this.isLoop = false;
    this.isOutline = false;
    this.isDiscreteT = false;
    this.isSlowMo = false;
    this.lastCmdTime = model.getLastTimeCommands();
  }

  /**
   * Method to start the controller and send the information to view to know what to show.
   */
  @Override
  public void start() {

    this.view.addListener(this);
    this.view.addKeyListener(this);

    view.makeVisible();

    this.t = new Tempo(0);

    this.createNewModel();

    this.timer = new Timer(1000 / (int) this.speed, ac);
  }

  /**
   * A method to create a deep copy of the list of all the Shape from the model.
   */
  private void createNewModel() {
    ms = new ArrayList<>();
    ms.addAll(model.getShapes());
  }

  ActionListener ac = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

      int tick = model.getSlowMoTempoAt((int) t.getTempo());
      if (tick != -1 && !isSlowMo) {
        isSlowMo = true;
        timer.stop();
        timer = new Timer(1000 / tick, ac);
        timer.start();
      } else if (isSlowMo) {
        isSlowMo = false;
        timer.stop();
        timer = new Timer(1000 / (int) speed, ac);
        timer.start();
      }
      System.out.println("Tempo: " + tick);

      if (isLoop && (lastCmdTime - t.getTempo() < 0.000001)) {
        timer.restart();
        createNewModel();
        t = new Tempo(0);
      } else if (lastCmdTime - t.getTempo() < 0.000001) {
        timer.stop();
      }

      AnimationStart.execute(ms, model, t, view, isDiscreteT);
    }
  };

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

  /**
   * Invoked when a button has been performed.
   *
   * @param ae ActionEvent - the event to be processed
   */
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
        this.speed += 10;
        this.timer.setDelay(1000 / (int) this.speed);
        break;
      case "SpeedDown Button":
        if (this.speed <= 0) {
          this.timer.setDelay(this.timer.getDelay());
        } else {
          if (this.speed - 10 <= 0) {
            this.timer.setDelay(this.timer.getDelay());
          } else {
            this.speed -= 10;
            this.timer.setDelay(1000 / (int) this.speed);
          }
        }
        break;
      case "Loop Button":
        this.isLoop = !isLoop;
        view.setIsLoop(isLoop);
        break;
      case "Outline Check":
        this.isOutline = !isOutline;
        view.setIsOutline(isOutline);
        break;
      case "Discrete Time Check":
        this.isDiscreteT = !isDiscreteT;
        view.setIsDiscreteT(isDiscreteT);
        break;
      default:
        throw new IllegalArgumentException("Button cannot applied");
    }
  }

  /**
   * Invoked when a key has been typed.
   *
   * @param e the event to be processed
   */
  @Override
  public void keyTyped(KeyEvent e) {
    throw new UnsupportedOperationException("1");
  }

  /**
   * Invoked when a key has been pressed.
   *
   * @param e the event to be processed
   */
  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_R:
        this.timer.restart();
        this.createNewModel();
        this.t = new Tempo(0);
        break;
      case KeyEvent.VK_S:
        this.timer.start();
        break;
      case KeyEvent.VK_P:
        this.timer.stop();
        break;
      case KeyEvent.VK_U:
        this.speed += 10;
        this.timer.setDelay(1000 / (int) this.speed);
        break;
      case KeyEvent.VK_D:
        if (this.speed <= 0) {
          this.timer.setDelay(this.timer.getDelay());
        } else {
          if (this.speed - 10 <= 0) {
            this.timer.setDelay(this.timer.getDelay());
          } else {
            this.speed -= 10;
            this.timer.setDelay(1000 / (int) this.speed);
          }
        }
        break;
      case KeyEvent.VK_L:
        this.isLoop = !isLoop;
        view.setIsLoop(isLoop);
        break;
      case KeyEvent.VK_O:
        this.isOutline = !isOutline;
        view.setIsOutline(isOutline);
        break;
      case KeyEvent.VK_T:
        this.isDiscreteT = !isDiscreteT;
        view.setIsDiscreteT(isDiscreteT);
        break;
      default:
        throw new IllegalArgumentException("Key cannot applied");
    }
  }

  /**
   * Invoked when a key has been released.
   *
   * @param e the event to be processed
   */
  @Override
  public void keyReleased(KeyEvent e) {
    throw new UnsupportedOperationException("2");
  }
}
