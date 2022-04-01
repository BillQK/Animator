package view;

import java.util.List;

import model.IAnimatorModelState;
import model.command.ICommands;
import model.shape.AShape;

public class AnimatorSVGView extends AnimatorTextView {
  private double tempo;
  /**
   * A constructor for AnimatorTextView class.
   *
   * @param model the given model state to operate the operations on
   */
  public AnimatorSVGView(IAnimatorModelState<?> model, double tempo) {
    super(model);
    this.tempo = tempo;
  }
  @Override
  public String getDetails() {

    String details = "<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n";

    List<AShape> los = model.getShapes();

    for (AShape s : los) {
      details += s.getSVG();
      List<ICommands> loc = model.getCommands(s.getName());
      for (ICommands c : loc) {
        details += c.getSVG(tempo);
      }
    }
  }


}
