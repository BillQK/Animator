package model.utils;

import java.util.ArrayList;
import java.util.List;

import model.IAnimatorModel;
import model.command.ICommands;
import model.shape.AShape;
import view.IAnimatorView;

public final class AnimationStart {

  public static void execute(List<AShape> ms, IAnimatorModel model, Tempo t, IAnimatorView view) {
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

    AnimationStart.run(ms, model, t, view);
  }

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
}
