package controller;

import javax.swing.Timer;
import view.IAnimatorView;

/**
 * An SVG View Controller class. This controller offers an SVG text view class which is specified
 * in an SVG style which will show the duration, time, speed, and what field of the shape changed.
 */
public class SVGCtrl implements IAnimatorController {
  private final IAnimatorView view;
  private final String filename;

  /**
   * Constructor for SVGCtrl with the given view and filename for output.
   *
   * @param view IAnimatorView - the given view
   * @param filename String - the given filename
   */
  public SVGCtrl(IAnimatorView view, String filename) {
    this.view = view;
    this.filename = filename;
  }

  /**
   * Method to start the controller and send the information to view to know what to show.
   */
  @Override
  public void start() {
    if (filename == null) {
      System.out.println(this.view.getDetails());
    }
    this.view.writeFile(filename);
  }

  /**
   * Method to get the build-in timer for the controller/view.
   *
   * @return Timer - the system time
   */
  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }

  /**
   * Method to get the speed that the user put in.
   *
   * @return double - the tempo speed
   */
  @Override
  public double getSpeed() {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }
}
