import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.IAnimatorModelState;
import model.SimpleAnimatorModel;
import model.shape.AShape;
import view.AnimatorVisualView;

public class main {
  public static void main(String[] args) {
    IAnimatorModelState<AShape> model;
    model = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10,10,100,100,10,10,10,0,100)
            .addColorChange("1", 10,10,10,15,15,15,0,5)
            .addMove("1", 10,10,15,40, 6, 7)
            .addMove("1", 15,40,10,10, 8, 15)
            .addScaleToChange("1", 100, 100, 200, 200,15, 30)
            .addOval("2", 10,10,10,50, 10, 10,10, 0,50)
            .addMove("2", 10,10,50,50,0,10)
            .build();
    AnimatorVisualView view = new AnimatorVisualView(10, model.getShapes());
    view.makeVisible();
  }
}
