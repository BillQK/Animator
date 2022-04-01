package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.shape.AShape;

public class APanel extends JPanel {
  private List<AShape> shapes;

  public APanel() {
    super();
    shapes = new ArrayList<>();
    this.setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D)  g;

   }
}
