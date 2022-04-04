import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.*;

import model.IAnimatorModel;
import model.IAnimatorModelState;
import model.SimpleAnimatorModel;
import model.io.AnimationFileReader;
import model.shape.AShape;
import view.AnimatorSVGView;
import view.AnimatorTextView;
import view.AnimatorViewCreator;
import view.AnimatorVisualView;
import view.IAnimatorView;

public class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    IAnimatorView view = new AnimatorVisualView();
    IAnimatorModel<AShape> model = null;
    int tick = 1;

    while (scan.hasNext()) {
      String in = scan.next();
      try {
        switch (in) {
          case "-in":
            AnimationFileReader fr = new AnimationFileReader();
            String fileName = scan.next();
            File file = new File(fileName);
            try {
              model = fr.readFile(fileName, new SimpleAnimatorModel.TweenBuilder());
            } catch (FileNotFoundException e) {
              view.showErrorMessage("File does not exists");
            }
          case "-out":
            if (model == null) {
              view.showErrorMessage("Model cannot be null");
            }
            String outputFile = scan.next();
            view.writeFile(outputFile);
          case "-view":
            if (model == null) {
              view.showErrorMessage("Model cannot be null");
            }
            String type = scan.next();
            view = AnimatorViewCreator.create(type, model);
          case "-speed":
            tick = scan.nextInt();
          default:
            view.showErrorMessage("Not Correct Command");
        }
      } catch (NoSuchElementException e) {
        // return something
      }
    }

    if (model != null && view != null) {
      Tempo t = new Tempo();

      IAnimatorModel<AShape> finalModel = model;
      IAnimatorView finalView = view;
      ActionListener timeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
          List<AShape> losTempo = new ArrayList<>();
          AShape shape;
          for (AShape s : finalModel.getShapes()) {
            shape = finalModel.getShapeAtTick(t.getTempo(), s.getName());
            losTempo.add(shape);
          }

          System.out.println(t.getTempo() + "hi");
          finalView.setShapes(losTempo);
          finalView.refresh();
          t.addTempo();
        }
      };

      Timer timer = new Timer(tick, timeListener);
      timer.start();

    }

    //IAnimatorModelState<AShape> model = new SimpleAnimatorModel.TweenBuilder()
    //            .addRectangle("1", 10,10,100,100,
    //                    106,110,120,0,40)
    //            .addColorChange("1", 10,10,10,
    //                    15,15,15,0,5)
    //            .addMove("1", 10,10,
    //                    15,40, 6, 7)
    //            .addMove("1", 15,40,
    //                    10,10, 8, 15)
    //            .addScaleToChange("1", 100, 100,
    //                    200, 200,15, 30)
    //            .addOval("2", 10,10,10,50,
    //                    200, 200,200, 0,50)
    //            .addMove("2", 10,10,
    //                    50,50,0,10)
    //            .build();
    //    AnimatorVisualView view = new AnimatorVisualView();
    //
    //    view.makeVisible();
    //
    //    Tempo t = new Tempo();
    //
    //    ActionListener timeListener = new ActionListener() {
    //      @Override
    //      public void actionPerformed(ActionEvent ae) {
    //        List<AShape> losTempo = new ArrayList<>();
    //        AShape shape;
    //        for (AShape s : model.getShapes()) {
    //          shape = model.getShapeAtTick(t.getTempo(), s.getName());
    //          losTempo.add(shape);
    //        }
    //
    //        System.out.println(t.getTempo() + "hi");
    //        view.setShapes(losTempo);
    //        view.refresh();
    //        t.addTempo();
    //      };
    //
    //    };
    //
    //  Timer timer = new Timer(1000, timeListener);
    //  timer.start();
    //
    //    AnimationFileReader fr = new AnimationFileReader();
    //    try {
    //      IAnimatorModel<AShape> model1 = fr.readFile("resource/toh-3.txt", new SimpleAnimatorModel.TweenBuilder());
    //      IAnimatorView textview = new AnimatorTextView(model1);
    //      textview.writeFile("text-transcript");
    //
    //    } catch (IOException e) {
    //      e.printStackTrace();
    //    }
    //
    //    AnimationFileReader fr2 = new AnimationFileReader();
    //    try {
    //      IAnimatorModelState<AShape> model2 = fr2.readFile("resource/toh-8.txt", new SimpleAnimatorModel.TweenBuilder());
    //      IAnimatorView textview = new AnimatorSVGView(model2, 500);
    //      textview.writeFile("toh-at-20");
    //
    //    } catch (IOException e) {
    //      e.printStackTrace();
    //    }




  }
  public static class Tempo {
    double tempo = 0;


    public double getTempo() {
      return this.tempo;
    }

    public void addTempo() {
      tempo++;
    }
  }
}
