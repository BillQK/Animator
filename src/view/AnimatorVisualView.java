package view;


import java.awt.Dimension;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.JScrollPane;

import model.IAnimatorModelState;
import model.shape.AShape;

/**
 * Represents a visual class which extends JFrame and implements IAnimatorView, which
 * represent us a visual running animation.
 */
public class AnimatorVisualView extends JFrame implements IAnimatorView {
  private final APanel panel;


  /**
   * Constructor of the AnimatorVisualView. This is where we set up our JFrame and adding our
   * panel to paint the view.
   *
   * @param model the given model
   */
  public AnimatorVisualView(IAnimatorModelState model) {
    super();
    this.setTitle("Animation");
    this.setSize(model.getWidth(), model.getHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.panel = new APanel();
    this.panel.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
    this.add(panel);

    JScrollPane scroll = new JScrollPane(panel);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    //set the bound problems
    scroll.setBounds(100, 100, 300, 500);
    scroll.setPreferredSize(new Dimension(800, 800));

    this.add(scroll, BorderLayout.CENTER);
    this.pack();
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
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * A method to based on the given fileName to print out the according view.
   *
   * @param fileName String - a given fileName
   */
  @Override
  public void writeFile(String fileName) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Set up the button listener to handle the button click events in this view.
   *
   * @param listener the action listener
   */
  public void addListener(ActionListener listener) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Set up the key listener to handle the key pressed in this view.
   *
   * @param klistener the action listener
   */
  public void addKeyListener(KeyListener klistener) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

  /**
   * Make the view visible to start the game session.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }


  /**
   * A method to set the list of shapes field to the given list of shape arguments.
   *
   * @param s the given list of Shapes
   */
  @Override
  public void setShapes(List<AShape> s) {
    if (s == null) {
      throw new IllegalArgumentException("The list of shapes cannot be null");
    }
    List<AShape> shapes = s;
    this.panel.setShapes(shapes);
  }

  /**
   * Method to get the IsLoop current boolean.
   *
   * @return boolean - the current IsLoop boolean
   */
  @Override
  public boolean getIsLoop() {
    throw new UnsupportedOperationException("View doesn't support this method");
  }

  /**
   * Method to set the boolean IsLoop into the given boolean.
   *
   * @param loop boolean - the given boolean to set the IsLoop as
   */
  @Override
  public void setIsLoop(boolean loop) {
    throw new UnsupportedOperationException("View doesn't support this method");
  }

  /**
   * Method to get the IsOutline current boolean.
   *
   * @return boolean - the current IsOutline boolean
   */
  public boolean getIsOutline() {
    throw new UnsupportedOperationException("View doesn't support this method");
  }

  /**
   * Method to set the boolean IsOutline into the given boolean.
   *
   * @param outline boolean - the given boolean to set the IsOutline as
   */
  public void setIsOutline(boolean outline) {
    throw new UnsupportedOperationException("View doesn't support this method");
  }

  /**
<<<<<<< HEAD
   * Method to get the IsDiscreteT current boolean.
   *
   * @return boolean - the current IsDiscreteT boolean
   */
  public boolean getIsDiscreteT() {
=======
   * Method to get the IsOutline current boolean.
   *
   * @return boolean - the current IsOutline boolean
   */
  public boolean getIsOutline() {
>>>>>>> 977aa0c7e733742f5f29c4d3af2b4f8bf7fcd1f8
    throw new UnsupportedOperationException("View doesn't support this method");
  }

  /**
   * Method to set the boolean IsDiscreteT into the given boolean.
   *
   * @param discreteT boolean - the given boolean to set the IsDiscreteT as
   */
  public void setIsDiscreteT(boolean discreteT) {
    throw new UnsupportedOperationException("View doesn't support this method");
  }

<<<<<<< HEAD
=======
  /**
   * Method to get the IsDiscreteT current boolean.
   *
   * @return boolean - the current IsDiscreteT boolean
   */
  public boolean getIsDiscreteT() {
    throw new UnsupportedOperationException("View doesn't support this method");
  }

>>>>>>> 977aa0c7e733742f5f29c4d3af2b4f8bf7fcd1f8

}
