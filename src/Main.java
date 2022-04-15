
import java.awt.Panel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import javax.swing.JOptionPane;

import controller.IAnimatorController;
import controller.InteractiveCtrl;
import controller.SVGCtrl;
import controller.TextCtrl;
import controller.VisualCtrl;
import model.IAnimatorModel;
import model.SimpleAnimatorModel;
import model.io.AnimationFileReader;
import model.io.CustomMotionFile;
import model.io.QuickSortFile;
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
    new CustomMotionFile().createFile("ADayInEgypt");
    Map<String, String> commandLine = new HashMap<>();
    IAnimatorView view = null;
    JOptionPane popUp = new JOptionPane();
    IAnimatorModel model = null;
    int sec = 1;

    IAnimatorController ctrl = null;

    for (int i = 0; i < args.length - 1; i++) {
      String key = args[i];
      String value = args[i + 1];
      commandLine.put(key, value);
    }

    //check if exist in, out, and view
    if (!commandLine.containsKey("-in") && !commandLine.containsKey("-out")
            && !commandLine.containsKey("-view")) {
      JOptionPane.showMessageDialog(new Panel(), "Invalid Command",
              "Error", JOptionPane.ERROR_MESSAGE);
    }

    // case in
    AnimationFileReader fr = new AnimationFileReader();
    try {
      model = fr.readFile(commandLine.get("-in"), new SimpleAnimatorModel.TweenBuilder());

    } catch (IOException e) {
      JOptionPane.showMessageDialog(new Panel(), "File Not Found",
              "Error", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }

    // case -view
    try {
      view = AnimatorViewCreator.create(commandLine.get("-view"), model, sec);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(new Panel(), "View can't be null",
              "Error", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }

    // case speed
    if (commandLine.containsKey("-speed")) {
      sec = Integer.parseInt(commandLine.get("-speed"));
    }

    // case out
    switch (commandLine.get("-view")) {
      case "text":
        ctrl = new TextCtrl(view, commandLine.get("-in"));
        ctrl.start();
        break;
      case "svg":
        if (!commandLine.containsKey("-out")) {
          System.out.println(view.getDetails());
        } else {
//          view.writeFile(commandLine.get("-out"));
          ctrl = new SVGCtrl(view, commandLine.get("-in"));
          ctrl.start();
          break;
        }
        break;
      case "visual":
        ctrl = new VisualCtrl(model, view, sec);
        ctrl.start();
        break;
      case "interactive":
        ctrl = new InteractiveCtrl(model, view, sec);
        ctrl.start();
        break;
      default:
        throw new IllegalArgumentException("Controller cannot be null");

    }


  }
}