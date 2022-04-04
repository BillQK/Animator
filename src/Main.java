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
