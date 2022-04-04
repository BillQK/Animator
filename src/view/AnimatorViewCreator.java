package view;

import java.util.Objects;

import model.IAnimatorModelState;
import model.shape.AShape;

/**
 * A factory representation of a AnimotorViewCreator,
 * Its usage is to return a instance of Animator View object.
 */
public class AnimatorViewCreator {
  /**
   * A method that return an instance of the given View.
   * @param view a String
   * @param model a IAnimatorModelState model
   * @return IAnimatorView
   */
  public static IAnimatorView create(String view, IAnimatorModelState<AShape> model,
                                     double tempo) {
    if (Objects.equals(view, "")) {
      throw new IllegalArgumentException("Type of view cannot be empty");
    } else if (Objects.equals(view, "svg")) {
      return new AnimatorSVGView(model, tempo);
    } else if (Objects.equals(view, "text")) {
      return new AnimatorTextView(model);
    } else if (Objects.equals(view, "visual")) {
      return new AnimatorVisualView();
    } else {
      throw new IllegalArgumentException("Invalid view type");
    }
  }
}
