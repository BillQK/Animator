package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import controller.IAnimatorController;
import model.shape.AShape;

public class AnimatorVisualView extends JFrame implements IAnimatorView {
  private APanel panel;
  private List<AShape> shapes;

  public AnimatorVisualView(double speed, List<AShape> shapes) {
    super();

    this.shapes = shapes;
    this.setTitle("Animation");
    this.setSize(700,700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.panel = new APanel();
    this.panel.setPreferredSize(new Dimension(700,700));

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
}
