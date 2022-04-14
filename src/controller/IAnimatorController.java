package controller;

import javax.swing.Timer;

/**
 * Interface for the Animator controller. An implementation will work with the IAnimatorModel
 * interface to provide a video of animation or any view depend on what the user want.
 */
public interface IAnimatorController {

  /**
   * Method to start the controller and send the information to view to know what to show.
   */
  void start();

  /**
   * Method to get the build-in timer for the controller/view.
   *
   * @return Timer - the system time
   */
  Timer getTimer();

  /**
   * Method to get the speed that the user put in.
   *
   * @return double - the tempo speed
   */
  double getSpeed();

}
