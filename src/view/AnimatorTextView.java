package view;

import model.IAnimatorModelState;

/**
 * Represents the main view class which implemented the IAnimatorView.
 */
public class AnimatorTextView implements IAnimatorView {
  private final IAnimatorModelState<?> model;

  /**
   * A constructor for AnimatorTextView class.
   * @param model the given model state to operate the operations on
   */
  public AnimatorTextView(IAnimatorModelState<?> model) {
    this.model = model;
  }

  /**
   * A method that print out the state of the model.
   * @return a String
   */
  @Override
  public String toString() {
    return model.getState();
  }
}
