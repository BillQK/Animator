package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;


import model.IAnimatorModelState;
import model.shape.AShape;

/**
 * Represents an interactive view class which extends JFrame and implements IAnimatorView,
 * which represent us a visual interactive running animation which allow us to start, pause,
 * resume, restart, looping, increase and decrease the speed of running the animation.
 */
public class AnimatorInteractiveView extends JFrame implements IAnimatorView {
  private final APanel panel;
  private final JButton start;
  private final JButton pause;
  private final JButton restart;
  private final JButton speedup;
  private final JButton speeddown;
  private final JToggleButton loop;
  private final JCheckBox outline;
  private final JCheckBox discreteT;
  private boolean isLoop;
  private boolean isOutline;
  private boolean isDiscreteT;

  /**
   * Constructor of the AnimatorInteractiveView. This is where we set up our JFrame and adding our
   * panel to paint the view.
   *
   * @param model the given model
   */
  public AnimatorInteractiveView(IAnimatorModelState model) {
    super();
    this.setTitle("Interactive");
    this.setSize(model.getWidth(), model.getHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.isLoop = false;
    this.isOutline = false;
    this.isDiscreteT = true;

    this.panel = new APanel();
    this.panel.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
    this.add(panel);

    JScrollPane scroll = new JScrollPane(panel);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroll.setBounds(50, 30, 300, 50);
    this.add(scroll, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    panel.add(buttonPanel, BorderLayout.SOUTH);

    start = new JButton("Start");
    start.setActionCommand("Start Button");
    buttonPanel.add(start);

    pause = new JButton("Pause");
    pause.setActionCommand("Pause Button");
    buttonPanel.add(pause);

    restart = new JButton("Restart");
    restart.setActionCommand("Restart Button");
    buttonPanel.add(restart);

    speedup = new JButton("Speed Up");
    speedup.setActionCommand("SpeedUp Button");
    buttonPanel.add(speedup);

    speeddown = new JButton("Speed Down");
    speeddown.setActionCommand("SpeedDown Button");
    buttonPanel.add(speeddown);

    loop = new JToggleButton("Loop");
    loop.setActionCommand("Loop Button");
    buttonPanel.add(loop);

    outline = new JCheckBox("Outline");
    outline.setActionCommand("Outline Check");
    buttonPanel.add(outline);

    discreteT = new JCheckBox("Discrete Time");
    discreteT.setActionCommand("Discrete Time Check");
    buttonPanel.add(discreteT);

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
    start.addActionListener(listener);
    pause.addActionListener(listener);
    restart.addActionListener(listener);
    speedup.addActionListener(listener);
    speeddown.addActionListener(listener);
    loop.addActionListener(listener);
    outline.addActionListener(listener);
    discreteT.addActionListener(listener);
  }

  /**
   * Set up the key listener to handle the key pressed in this view.
   *
   * @param klistener the action listener
   */
  public void addKeyListener(KeyListener klistener) {
    start.addKeyListener(klistener);
    pause.addKeyListener(klistener);
    restart.addKeyListener(klistener);
    speedup.addKeyListener(klistener);
    speeddown.addKeyListener(klistener);
    loop.addKeyListener(klistener);
    outline.addKeyListener(klistener);
    discreteT.addKeyListener(klistener);
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
   * Method to set the boolean IsLoop into the given boolean.
   *
   * @param loop boolean - the given boolean to set the IsLoop as
   */
  @Override
  public void setIsLoop(boolean loop) {
    this.isLoop = loop;
  }

  /**
   * Method to get the IsLoop current boolean.
   *
   * @return boolean - the current IsLoop boolean
   */
  @Override
  public boolean getIsLoop() {
    return this.isLoop;
  }

  /**
   * Method to set the boolean IsOutline into the given boolean.
   *
   * @param outline boolean - the given boolean to set the IsOutline as
   */
  public void setIsOutline(boolean outline) {
    this.isOutline = outline;
    this.panel.setIsOutline(outline);
  }

  /**
   * Method to get the IsOutline current boolean.
   *
   * @return boolean - the current IsOutline boolean
   */
  public boolean getIsOutline() {
    return this.isOutline;
  }

  /**
   * Method to set the boolean IsDiscreteT into the given boolean.
   *
   * @param discreteT boolean - the given boolean to set the IsDiscreteT as
   */
  public void setIsDiscreteT(boolean discreteT) {
    this.isDiscreteT = discreteT;
    this.panel.setIsDiscreteT(discreteT);
  }

  /**
   * Method to get the IsDiscreteT current boolean.
   *
   * @return boolean - the current IsDiscreteT boolean
   */
  public boolean getIsDiscreteT() {
    return this.isDiscreteT;
  }

}
