package controller;

import javax.swing.Timer;

import view.IAnimatorView;

/**
 * A Text View Controller class. This controller offers a text view class which present in
 * text style which will show the state of the shape after each motion.
 */
public class TextCtrl implements IAnimatorController {
  private final IAnimatorView view;
  private final String filename;

  /**
   * Constructor for TextCtrl with the given view and filename for output.
   *
   * @param view IAnimatorView - the given view
   * @param filename String - the given filename
   */
  public TextCtrl(IAnimatorView view, String filename) {
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
