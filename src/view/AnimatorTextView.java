package view;

import model.IAnimatorModelState;

public class AnimatorTextView implements IAnimatorView {
  private final IAnimatorModelState<?> model;

  public AnimatorTextView(IAnimatorModelState<?> model) {
    this.model = model;
  }

  @Override
  public String toString() {
    return model.getState();
  }
}
