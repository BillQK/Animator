import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import model.IAnimatorModel;
import model.IAnimatorModelState;
import model.SimpleAnimatorModel;
import model.io.AnimationFileReader;
import model.shape.AShape;
import view.AnimatorSVGView;
import view.AnimatorTextView;
import view.AnimatorVisualView;
import view.IAnimatorView;

public class Main {
  public static void main(String[] args) {
    IAnimatorModelState<AShape> model = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10,10,100,100,106,110,120,0,100)
            .addColorChange("1", 10,10,10,15,15,15,0,5)
            .addMove("1", 10,10,15,40, 6, 7)
            .addMove("1", 15,40,10,10, 8, 15)
            .addScaleToChange("1", 100, 100, 200, 200,15, 30)
            .addOval("2", 10,10,10,50, 200, 200,200, 0,50)
            .addMove("2", 10,10,50,50,0,10)
            .build();
    AnimatorVisualView view = new AnimatorVisualView(0, model.getShapes(), model);

    view.makeVisible();

    Timer t = new Timer(30, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {

        int tick = 30;
        view.refresh();
        view.makeVisible();
        for (int i = 0; i < tick; i++) {
          System.out.println(model.getShapeAtTick(i, "1").getPosition().getX());
        }
        System.out.println("hi");
        tick++;
      };

    });
    t.start();


    view.makeVisible();

    AnimationFileReader fr = new AnimationFileReader();
    try {
      IAnimatorModel<AShape> model1 = fr.readFile("resource/toh-3.txt", new SimpleAnimatorModel.TweenBuilder());
      IAnimatorView textview = new AnimatorTextView(model1);
      textview.writeFile("text-transcript");

    } catch (IOException e) {
      e.printStackTrace();
    }

    AnimationFileReader fr2 = new AnimationFileReader();
    try {
      IAnimatorModelState<AShape> model2 = fr2.readFile("resource/toh-8.txt", new SimpleAnimatorModel.TweenBuilder());
      IAnimatorView textview = new AnimatorSVGView(model2, 500);
      textview.writeFile("toh-at-20");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
