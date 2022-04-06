
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.IAnimatorModel;
import model.IAnimatorModelState;
import model.SimpleAnimatorModel;
import model.command.ICommands;
import model.io.AnimationFileReader;
import model.shape.AShape;
import view.AnimatorViewCreator;
import view.IAnimatorView;

/**
 * The main method class to run the model and view.
 */
public class Main {

  /**
   * The main method to run the model.
   *
   * @param args the given array of arguments to run the main method through
   */
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
      popUp.showMessageDialog(new Panel(), "Invalid Command",
              "Error", JOptionPane.ERROR_MESSAGE);
    }

    // case in
    AnimationFileReader fr = new AnimationFileReader();
    try {
      model = fr.readFile(commandLine.get("-in"), new SimpleAnimatorModel.TweenBuilder());

    } catch (IOException e) {
      popUp.showMessageDialog(new Panel(), "File Not Found",
              "Error", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }

    // case -view
    try {
      view = AnimatorViewCreator.create(commandLine.get("-view"), model, sec);
    } catch (IllegalArgumentException e) {
      popUp.showMessageDialog(new Panel(), "View can't be null",
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
      ActionListener timeListener = ae -> {
        List<AShape> losTempo = new ArrayList<>();
//          AShape shape;
        for (AShape s : finalModel.getShapes()) {
//            shape = finalModel.getShapeAtTick(t.getTempo(), s.getName());
          for (ICommands c : finalModel.getExecutableCommand(s.getName())) {
            c.execute(t.getTempo());
          }
          losTempo.add(s);
        }
        System.out.println(t.getTempo());
        finalView.setShapes(losTempo);
        finalView.refresh();
        t.addTempo();
      };

      Timer timer = new Timer(1000, timeListener);
      timer.start();

    }

  }

  /**
   * Represent the time class of the model state.
   */
  public static class Tempo {
    double tempo = 0;

    /**
     * A method to get the current tempo.
     *
     * @return double - the current method
     */
    public double getTempo() {
      return this.tempo;
    }

    /**
     * A method to add one on the tempo everytime it is called.
     */
    public void addTempo() {
      tempo++;
    }
  }
}
