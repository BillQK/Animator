package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.IAnimatorModelState;
import model.shape.AShape;
import model.shape.Shape;

public class APanel extends JPanel {
  private List<AShape> shapes;
//  Timer time;
//  private final IAnimatorModelState<AShape> model;
//  IAnimatorModelState<AShape> model


  public APanel() {
    super();
    shapes = new ArrayList<>();
    this.setBackground(Color.WHITE);
//    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    AffineTransform transform = ((Graphics2D) g).getTransform();

    for (AShape s : shapes) {
      int x = (int) s.getPosition().getX();
      int y = (int) s.getPosition().getY();
      int w = (int) s.getWidth();
      int h = (int) s.getHeight();
      Color c = s.getColor();

      if (s.getType() == Shape.RECTANGLE) {
        g2.setColor(c);
        g2.fillRect(x,y,w,h);
        g2.drawRect(x,y,w,h);
      } else if (s.getType() == Shape.ELLIPSE) {
        g2.setColor(c);
        g2.fillOval(x,y,w,h);
        g2.drawOval(x,y,w,h);
      }

    }
    g2.setTransform(transform);
  }

  public List<AShape> setShapes(List<AShape> s) {
    return this.shapes = s;
  }

}
