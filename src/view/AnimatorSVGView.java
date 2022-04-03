package view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.IAnimatorModelState;
import model.command.ICommandsState;
import model.shape.AShape;

public class AnimatorSVGView extends AnimatorTextView {
  private final double tempo;

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
      List<ICommandsState> loc = model.getCommands(s.getName());
      for (ICommandsState c : loc) {
        details += c.getSVG(tempo);
      }
      details += s.getSVGEndShape() + "\n";
    }
    details += "</svg>";

    return details;
  }

  @Override
  public void writeFile(String fileName) {
    try {
      FileWriter output = new FileWriter(fileName + ".svg");
      output.write(this.getDetails());
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



}
