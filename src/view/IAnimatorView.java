package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

import model.shape.AShape;

/**
 * Represents a way of presenting the animation in a form useful to end users, i.e.,
 * a human-readable form.
 */
public interface IAnimatorView {

  /**
   * Return the present state of the game as a string. The string is formatted
   * as follows:
   * <pre>
   * Shape:[b]Shape's name[b]Shape's Type (Rectangle or Ellipse)[n]
   * [b][b]...[b][b]START[b][b].......[b][b]END[b][n]
   * motion[b]Shape's name[b]Time[b]X[b]Y[b]Width[b]Height[b]Red[b]Green[b]Blue[b][b]
   * [b]Time[b]X[b]Y[b]Width[b]Height[b]Red[b]Green[b]Blue[n]
   *
   * motion[b]Shape's name[b]Shape's Time[b]Shape's X[b]Shape's Y[b]Shape's Width[b]Shape's Height
   * [b]Shape's Red[b]Shape's Green[b]Shape's Blue[b][b][b]Shape's Time[b]Shape's X[b]Shape's Y
   * [b]Shape's Width[b]Shape's Height[b]Shape's Red[b]Shape's Green[b]Shape's Blue[b][n]
   * (commands on the shape in order)
   * ...
   * [n]
   *
   * Shape:[b]Shape's name[b]Shape's Type (Rectangle or Ellipse)[n] (the shape in order)
   * [b][b]...[b][b]START[b][b].......[b][b]END[b][n]
   * motion[b]Shape's name[b]Time[b]X[b]Y[b]Width[b]Height[b]Red[b]Green[b]Blue[b][b]
   * [b]Time[b]X[b]Y[b]Width[b]Height[b]Red[b]Green[b]Blue[n]
   *
   * motion[b]Shape's name[b]Shape's Time[b]Shape's X[b]Shape's Y[b]Shape's Width[b]Shape's Height
   * [b]Shape's Red[b]Shape's Green[b]Shape's Blue[b][b][b]Shape's Time[b]Shape's X[b]Shape's Y
   * [b]Shape's Width[b]Shape's Height[b]Shape's Red[b]Shape's Green[b]Shape's Blue[b][n]
   * (commands on the shape in order)
   * ...
   * [n]
   *
   * ....
   *
   * where [b] is a single blankspace, [n] is newline.
   * </pre>
   *
   * @return the formatted string as above
   */
  String getDetails();

  /**
   * A method to based on the given fileName to print out the according view.
   *
   * @param fileName String - a given fileName
   */
  void writeFile(String fileName);

  /**
   * Set up the button listener to handle the button click events in this view.
   *
   * @param listener the action listener
   */
  void addListener(ActionListener listener);

  /**
   * Set up the key listener to handle the key pressed in this view.
   *
   * @param klistener the action listener
   */
  void addKeyListener(KeyListener klistener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * A method to set the list of shapes field to the given list of shape arguments.
   *
   * @param losTempo the given list of Shapes
   */
  void setShapes(List<AShape> losTempo);

  /**
   * Method to set the boolean IsLoop into the given boolean.
   *
   * @param loop boolean - the given boolean to set the IsLoop as
   */
  void setIsLoop(boolean loop);

  /**
   * Method to get the IsLoop current boolean.
   *
   * @return boolean - the current IsLoop boolean
   */
  boolean getIsLoop();

  /**
   * Method to set the boolean IsOutline into the given boolean.
   *
   * @param outline boolean - the given boolean to set the IsOutline as
   */
  void setIsOutline(boolean outline);

  /**
   * Method to get the IsOutline current boolean.
   *
   * @return boolean - the current IsOutline boolean
   */
  boolean getIsOutline();

  /**
   * Method to set the boolean IsOutline into the given boolean.
   *
   * @param discreteT boolean - the given boolean to set the IsOutline as
   */
  void setIsDiscreteT(boolean discreteT);

  /**
   * Method to get the IsOutline current boolean.
   *
   * @return boolean - the current IsOutline boolean
   */
  boolean getIsDiscreteT();


}
