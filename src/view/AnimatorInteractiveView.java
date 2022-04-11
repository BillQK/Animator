package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;


import controller.IAnimatorController;
import model.IAnimatorModelState;
import model.shape.AShape;

public class AnimatorInteractiveView extends JFrame implements IAnimatorView {
  private final APanel panel;
  private JToggleButton start;
  private JToggleButton pause;
  private JButton restart;
  private JButton speedup;
  private JButton speeddown;
  private JButton loop;

  public AnimatorInteractiveView(IAnimatorModelState model) {
    super();
    this.setTitle("Interactive");
    this.setSize(model.getWidth(), model.getHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.panel = new APanel();
    this.panel.setPreferredSize(new Dimension(700, 700));
    this.add(panel);

    JScrollPane scroll = new JScrollPane(panel);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroll.setBounds(50, 30, 300, 50);
    this.add(scroll, BorderLayout.CENTER);

    javax.swing.JPanel buttonPanel = new javax.swing.JPanel();
    buttonPanel.setLayout(new FlowLayout());
    panel.add(buttonPanel, BorderLayout.SOUTH);

    start = new JToggleButton("Start");
    start.setActionCommand("Start Button");
    buttonPanel.add(start);

    pause = new JToggleButton("Pause");
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

    loop = new JButton("Loop");
    loop.setActionCommand("Loop Button");
    buttonPanel.add(loop);

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
    return null;
  }

  /**
   * A method to based on the given fileName to print out the according view.
   *
   * @param fileName String - a given fileName
   */
  @Override
  public void writeFile(String fileName) {

  }

  /**
   * Set up the controller to handle click events in this view.
   *
   * @param listener the controller
   */
  @Override
  public void addListener(IAnimatorController listener) {

  }

  /**
   * Set up the button listener to handle the button click events in this view.
   *
   * @param listener the action listener
   */
  public void setListener(ActionListener listener) {
    start.addActionListener(listener);
    pause.addActionListener(listener);
    restart.addActionListener(listener);
    speedup.addActionListener(listener);
    speeddown.addActionListener(listener);
    loop.addActionListener(listener);
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
   * A method to show an error message.
   *
   * @param error String - the error message
   */
  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error,
            "Error", JOptionPane.ERROR_MESSAGE);
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
}
