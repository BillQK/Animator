import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
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
    Map<String, String> commandLine = new HashMap<>();



    IAnimatorView view = null;
    IAnimatorModel<AShape> model = null;
    int speed = 1;

    for (int i = 0; i < args.length-1; i++){
      String key = args[i];
      String value = args[i+1];
      commandLine.put(key,value);
    }

    if (!commandLine.containsKey("-in") && !commandLine.containsKey("-out") && !commandLine.containsKey("-view")) {
      throw new IllegalArgumentException("Incorrect command");
    }

    // case in
    AnimationFileReader fr = new AnimationFileReader();
    try {
      model = fr.readFile(commandLine.get("-in"), new SimpleAnimatorModel.TweenBuilder());

    } catch (FileNotFoundException e) {
      System.out.println("Cannot find the file");
    }

    // case -view
    view = AnimatorViewCreator.create(commandLine.get("-view"), model);
    // case speed
    if (commandLine.containsKey("-speed")){
      speed = Integer.parseInt(commandLine.get("-speed"));
    }

    // case out
    switch (commandLine.get("-view")) {
      case "text":
        System.out.println(view.getDetails());
        break;
      case "svg":
        view.writeFile(commandLine.get("-out"));
        break;
      default:
        view.makeVisible();
    }

//      try {
//        switch (commandLine.keySet()) {
//          case "-in":
//            AnimationFileReader fr = new AnimationFileReader();
//            String fileName = scan.next();
////            String fileName = s.toString();
//            try {
//              model = fr.readFile(fileName, new SimpleAnimatorModel.TweenBuilder());
//            } catch (FileNotFoundException e) {
//              System.out.println("Cannot find the file");
//            }
//          case "-out":
//            if (model == null) {
//              System.out.println("Model cannot be null");
//            }
//            if (view == null) {
//              System.out.println("View cannot be null");
//            }
//            String outputFile = scan.next();
////            String outputFile = s.toString();
//            if (Objects.equals(outputFile, "text")) {
//              view.getDetails();
//            } else if (Objects.equals(outputFile, "")) {
//              view.writeFile(outputFile);
//            } else {
//              view.makeVisible();
//            }
//          case "-view":
//            if (model == null) {
//              System.out.println("Model cannot be null");
//            }
//            String type = scan.next();
////            String type = s.toString();
//            view = AnimatorViewCreator.create(type, model);
//          case "-speed":
//            tick = scan.nextInt();
////            tick = Integer.valueOf(s.toString());
//          default:
//            System.out.println("Not a correct command");
//        }
//      } catch (NoSuchElementException e) {
//        // return something
//      }



    if (Objects.equals(commandLine.get("-view"), "visual")) {
      Tempo t = new Tempo();

      IAnimatorModel<AShape> finalModel = model;
      IAnimatorView finalView = view;

      view.makeVisible();
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

      Timer timer = new Timer(speed, timeListener);
      timer.start();

    }

//    IAnimatorModel<AShape> model1 = new SimpleAnimatorModel.TweenBuilder()
//            .addRectangle("1", 10, 10, 100, 100, 120, 110, 140, 0, 100)
//            .addColorChange("1", 120, 110, 140, 120, 110, 140, 0, 5)
//            .addMove("1", 10, 10, 15, 40, 6, 30)
//            .addColorChange("1", 5,5,5,200,200,200, 6,30)
//            .addMove("1", 15, 40, 10, 10, 30, 35)
//            .addScaleToChange("1", 100, 100, 200, 200, 35, 40)
//            .addOval("2", 10, 10, 10, 50, 10, 10, 10, 0, 50)
//            .addMove("2", 10, 10, 50, 50, 0, 10)
//            .build();
//        AnimatorVisualView view1 = new AnimatorVisualView();
//
//        view1.makeVisible();
//
//        Tempo t = new Tempo();
//
//        ActionListener timeListener = new ActionListener() {
//          @Override
//          public void actionPerformed(ActionEvent ae) {
//            List<AShape> losTempo = new ArrayList<>();
//            AShape shape;
//            for (AShape s : model1.getShapes()) {
//              shape = model1.getShapeAtTick(t.getTempo(), s.getName());
//              losTempo.add(shape);
//            }
//
//            System.out.println(t.getTempo() + "hi");
//            view1.setShapes(losTempo);
//            view1.refresh();
//            t.addTempo();
//          };
//
//        };
//
//      Timer timer = new Timer(500, timeListener);
//      timer.start();
//
//        AnimationFileReader fr = new AnimationFileReader();
//        try {
//          IAnimatorModel<AShape> model1 = fr.readFile("resource/toh-3.txt", new SimpleAnimatorModel.TweenBuilder());
//          IAnimatorView textview = new AnimatorTextView(model1);
//          textview.writeFile("text-transcript");
//
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//
//        AnimationFileReader fr2 = new AnimationFileReader();
//        try {
//          IAnimatorModelState<AShape> model2 = fr2.readFile("resource/toh-8.txt", new SimpleAnimatorModel.TweenBuilder());
//          IAnimatorView textview = new AnimatorSVGView(model2, 500);
//          textview.writeFile("toh-at-20");
//
//        } catch (IOException e) {
//          e.printStackTrace();
//        }




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
