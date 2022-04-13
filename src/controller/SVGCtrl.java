package controller;

import javax.swing.Timer;
import view.IAnimatorView;

public class SVGCtrl implements IAnimatorController {
  private IAnimatorView view;
  private final String filename;

  public SVGCtrl(IAnimatorView view, String filename) {
    this.view = view;
    this.filename = filename;
  }

  @Override
  public void start() {
    if (filename == null) {
      System.out.println(this.view.getDetails());
    }
    this.view.writeFile(filename);
  }

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }

  @Override
  public double getTempo() {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }
}
