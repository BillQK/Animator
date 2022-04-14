package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.IAnimatorModel;
import model.command.ICommands;
import model.utils.Tempo;
import view.IAnimatorView;
import model.shape.AShape;

/**
 * An Interactive Visual View Controller class. This controller offers a visual view class
 * with button click which user can press the button to manipulate the view to start, pause,
 * resume, restart, looping, increase and decrease the speed.
 */
public class InteractiveCtrl implements IAnimatorController, ActionListener {
  private IAnimatorModel model;
  private IAnimatorView view;
  private final JOptionPane popUp;
  private List<AShape> ms;

  private double speed;
  private Timer timer;
  private Tempo t;

  private boolean isLoop;
  private double lastCmdTime;

  /**
   * Constructor for InteractiveCtrl with the given model, view and speed.
   *
   * @param model IAnimatorModel - the given model
   * @param view IAnimatorView - the given view
   * @param speed double - the view given speed
   */
  public InteractiveCtrl(IAnimatorModel model, IAnimatorView view, double speed) {
    this.model = model;
    this.view = view;
    this.popUp = new JOptionPane();

    this.speed = speed;
    this.timer = null;
    this.t = null;

    this.isLoop = false;
    this.lastCmdTime = model.getLastTimeCommands();
  }

  /**
   * Method to start the controller and send the information to view to know what to show.
   */
  @Override
  public void start() {

    this.view.setListener(this);

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
      default:
        throw new IllegalArgumentException("Button cannot applied");
    }
  }

}
