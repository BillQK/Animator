package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import controller.IAnimatorController;
import model.IAnimatorModelState;
import model.shape.AShape;

public class AnimatorVisualView extends JFrame implements IAnimatorView {
  private final APanel panel;
  private final List<AShape> shapes;
  private final IAnimatorModelState<AShape> model;

  public AnimatorVisualView(double speed, List<AShape> shapes, IAnimatorModelState<AShape> model) {
    super();

    this.shapes = shapes;
    this.model = model;
    this.setTitle("Animation");
    this.setSize(700, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.panel = new APanel(model);
    this.panel.setPreferredSize(new Dimension(700, 700));
    this.add(panel);

    this.panel.setShapes(shapes);

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



}
