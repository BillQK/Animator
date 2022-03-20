package view;

import model.IAnimatorModelState;

public class AnimatorTextView implements IAnimatorView {
  private final IAnimatorModelState<?> model;
  private Appendable out;

  public AnimatorTextView(IAnimatorModelState<?> model, Appendable out) {
    this.model = model;
    this.out = out;
  }

  @Override
  public String toString() {
    return "";
  }
}
