import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;

import model.IAnimatorModel;
import model.SimpleAnimatorModel;
import model.io.AnimationFileReader;
import model.shape.AShape;
import view.AnimatorViewCreator;
import view.AnimatorVisualView;
import view.IAnimatorView;

public class Main {
  public static void main(String[] args) {
    Map<String, String> commandLine = new HashMap<>();

    IAnimatorView view = null;
    JOptionPane popUp = new JOptionPane();
    IAnimatorModel<AShape> model = null;
    int sec = 1000;

    for (int i = 0; i < args.length - 1; i++) {
      String key = args[i];
      String value = args[i + 1];
      commandLine.put(key, value);
    }

    if (!commandLine.containsKey("-in") && !commandLine.containsKey("-out")
            && !commandLine.containsKey("-view")) {
      popUp.showMessageDialog(new AnimatorVisualView(), "Invalid Command",
              "Error", JOptionPane.ERROR_MESSAGE);
    }

    // case in
    AnimationFileReader fr = new AnimationFileReader();
    try {
      model = fr.readFile(commandLine.get("-in"), new SimpleAnimatorModel.TweenBuilder());

    } catch (IOException e) {
      popUp.showMessageDialog(new AnimatorVisualView(), "File Not Found",
              "Error", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }

    // case -view
    try {
      view = AnimatorViewCreator.create(commandLine.get("-view"), model, sec);
    } catch (IllegalArgumentException e) {
      popUp.showMessageDialog(new AnimatorVisualView(), "Model can't be null",
              "Error", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    // case speed
    if (commandLine.containsKey("-speed")) {
      sec /= Integer.parseInt(commandLine.get("-speed"));
    }

    // case out
    switch (commandLine.get("-view")) {
      case "text":
      case "svg":
        if (!commandLine.containsKey("-out")) {
          System.out.println(view.getDetails());
        } else {
          view.writeFile(commandLine.get("-out"));
        }
        break;
      default:
        view.makeVisible();
    }

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
          System.out.println(t.getTempo());
          finalView.setShapes(losTempo);
          finalView.refresh();
          t.addTempo();
        }
      };

      Timer timer = new Timer(sec, timeListener);
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
