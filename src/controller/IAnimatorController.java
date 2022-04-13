package controller;

import javax.swing.Timer;

/**
 * Interface for the Animator controller. An implementation will work with the IAnimatorModel
 * interface to provide a video of animation.
 */
public interface IAnimatorController {

  void start();

  Timer getTimer();

  double getTempo();

}
