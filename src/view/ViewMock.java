package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

import model.shape.AShape;

/**
 * This class represent a fake copy of the view to test
 * if whether the input send from the controller was successfully test and pass.
 */
public class ViewMock implements IAnimatorView {
  final StringBuilder log;

  /**
   * Constructor for ViewMock class.
   *
   * @param log StringBuilder - the log
   */
  public ViewMock(StringBuilder log) {
    this.log = log;
  }


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
  @Override
  public String getDetails() {
    log.append("getDetailsMethod ");
    return " ";
  }

  /**
   * A method to based on the given fileName to print out the according view.
   *
   * @param fileName String - a given fileName
   */
  @Override
  public void writeFile(String fileName) {
    log.append("writeFileMethod ");
  }

  /**
   * Set up the button listener to handle the button click events in this view.
   *
   * @param listener the action listener
   */
  @Override
  public void addListener(ActionListener listener) {
    log.append("ButtonaddListenerMethod ");
  }

  /**
   * Set up the key listener to handle the key pressed in this view.
   *
   * @param klistener the action listener
   */
  @Override
  public void addKeyListener(KeyListener klistener) {
    log.append("AddKeyListenerMethod ");
  }

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  @Override
  public void refresh() {
    log.append("RefreshMethod ");
  }

  /**
   * Make the view visible to start the game session.
   */
  @Override
  public void makeVisible() {
    log.append("MakeVisibleMethod ");
  }

  /**
   * A method to set the list of shapes field to the given list of shape arguments.
   *
   * @param losTempo the given list of Shapes
   */
  @Override
  public void setShapes(List<AShape> losTempo) {
    log.append("setShapesMethod ");
  }

  /**
   * Method to set the boolean IsLoop into the given boolean.
   *
   * @param loop boolean - the given boolean to set the IsLoop as
   */
  @Override
  public void setIsLoop(boolean loop) {
    log.append("setIsLoopMethod ");
  }

  /**
   * Method to get the IsLoop current boolean.
   *
   * @return boolean - the current IsLoop boolean
   */
  @Override
  public boolean getIsLoop() {
    log.append("getIsLoopMethod ");
    return false;
  }
}
