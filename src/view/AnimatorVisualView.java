package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import controller.IAnimatorController;
import model.shape.AShape;

public class AnimatorVisualView extends JFrame implements IAnimatorView {
  private final APanel panel;
  private List<AShape> shapes;

  public AnimatorVisualView() {
    super();


    this.setTitle("Animation");
    this.setSize(700, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    this.panel = new APanel();
    this.panel.setPreferredSize(new Dimension(700, 700));
    this.add(panel);


    JScrollPane scroll = new JScrollPane(panel);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroll.setBounds(50, 30, 300, 50);


    this.add(scroll, BorderLayout.CENTER);


    this.pack();

  }

  @Override
  public String getDetails() {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public void writeFile(String fileName) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public void addListener(IAnimatorController listener) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error,
            "Error", JOptionPane.ERROR_MESSAGE);
  }

  public void setShapes(List<AShape> s) {
    if (s == null) {
      throw new IllegalArgumentException("The list of shapes cannot be null");
    }
    this.shapes = s;
    this.panel.setShapes(shapes);
  }

}
