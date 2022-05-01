package model.utils;

import java.util.ArrayList;
import java.util.List;

import model.IAnimatorModel;
import model.command.ICommands;
import model.shape.AShape;
import view.IAnimatorView;

/**
 * Initialize class for animation. This class is for to make a deep copy of the shape, before
 * running the animation and seting the list of commands of the shape to the new deep copy and
 * run the animation in the controller.
 */
public final class AnimationStart {

  /**
   * Method to make a deep copy and set the list of command of the shape to this new
   * deep copy shape.
   *
   * @param ms    the new deep copy list of shapes
   * @param model the model to get the shape
   * @param t     the speed
   * @param view  the view to show it
   */
  public static void execute(List<AShape> ms, IAnimatorModel model, Tempo t, IAnimatorView view,
                             boolean isDiscreteT) {
    List<ICommands> com;
    for (AShape modelS : ms) {
      com = model.getExecutableCommand(modelS.getName());
      for (ICommands c : com) {
        AShape modelShape = c.getShape();
        for (AShape m : ms) {
          if (modelShape.getName().equals(m.getName())) {
            c.setShape(m);
          }
        }
      }
    }

    if (!isDiscreteT) {
      run(ms, model, t, view);
    }
    if (
            isDiscreteT) {
      runDiscrete(ms, model, t, view);
    }
  }

  /**
   * Method to run the animation in the controller.
   *
   * @param ms    the deep copy list of AShape to run the animation
   * @param model the model to get the shape
   * @param t     the speed
   * @param view  the view to show it
   */
  private static void run(List<AShape> ms, IAnimatorModel model, Tempo t, IAnimatorView view) {
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

  private static void runDiscrete(List<AShape> ms, IAnimatorModel model, Tempo t,
                                  IAnimatorView view) {
    List<AShape> losTempo = new ArrayList<>();
    for (Integer in : model.getDiscreteTimeInteger()) {
      for (AShape mShape : ms) {
        for (ICommands c : model.getExecutableCommand(mShape.getName())) {
          if (t.getTempo() == in) {
            c.execute(in);
          }
        }
        losTempo.add(mShape);
      }
    }

    view.setShapes(losTempo);
    view.refresh();
    t.addTempo();
    System.out.println(t.getTempo());

    view.makeVisible();

  }
}
