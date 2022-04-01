package view;

import java.util.Collections;
import java.util.List;

import controller.IAnimatorController;
import model.IAnimatorModelState;
import model.command.ICommands;
import model.shape.AShape;

/**
 * Represents the main view class which implemented the IAnimatorView.
 */
public class AnimatorTextView implements IAnimatorView {
  private final IAnimatorModelState<?> model;

  /**
   * A constructor for AnimatorTextView class.
   *
   * @param model the given model state to operate the operations on
   */
  public AnimatorTextView(IAnimatorModelState<?> model) {
    this.model = model;
  }

  /**
   * A method that print out the state of the model.
   *
   * @return a String
   */
  @Override
  public String toString() {
    StringBuilder finalString = new StringBuilder();
    for (AShape s : model.getShapes()) {
      finalString.append("Shape: ").append(s.getName()).append(" ")
              .append(s.getType().getShapeType()).append("\n");

      finalString.append("         START                                  END \n");
      finalString.append("motion ").append(s.getName()).append(" Time ")
              .append("X ").append("Y ").append("Width ")
              .append("Height ").append("Red ")
              .append("Green ").append("Blue ")
              .append("  ").append("Time ")
              .append("X ").append("Y ")
              .append("Width ")
              .append("Height ")
              .append("Red ").append("Green ").append("Blue\n");

      List<ICommands> c = model.getCommands(s.getName());

      for (int j = 0; j < c.size(); j++) {
        ICommands com = c.get(j);
        if (j != (c.size() - 1)) {
          finalString.append("motion ").append(com.getBeginsState())
                  .append("    ").append(com.getEndsState()).append("\n");
          c.get(j).execute(c.get(j + 1).getStart());
        } else {
          finalString.append("motion ").append(com.getBeginsState())
                  .append("    ").append(com.getEndsState()).append("\n\n");
        }
      }
    }
    return finalString.toString();

//    for (AShape s : model.getShapes()) {
//      finalString.append("Shape: ").append(s).append(" ")
//              .append(this.shapes.get(s).getType().getShapeType()).append("\n");
//
//      finalString.append("         START                                  END \n");
//      //The order of what we print in one motion
//      finalString.append("motion ").append(s).append(" Time ")
//              .append("X ").append("Y ").append("Width ")
//              .append("Height ").append("Red ")
//              .append("Green ").append("Blue ")
//              .append("  ").append("Time ")
//              .append("X ").append("Y ")
//              .append("Width ")
//              .append("Height ")
//              .append("Red ").append("Green ").append("Blue\n");
//
//      Collections.sort(this.commands.get(s));
//      for (int i = 0; i < this.commands.get(s).size(); i++) {
//        ICommands com = this.commands.get(s).get(i);
//        if (i != (this.commands.get(s).size() - 1)) {
//          finalString.append("motion ").append(com.getBeginsState())
//                  .append("    ").append(com.getEndsState()).append("\n");
//          this.commands.get(s).get(i).execute(this.commands.get(s).get(i + 1).getStart());
//        } else {
//          finalString.append("motion ").append(com.getBeginsState())
//                  .append("    ").append(com.getEndsState()).append("\n\n");
//        }
//      }
//    }
//    return finalString.toString();
  }

  @Override
  public void addListener(IAnimatorController listener) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  @Override
  public void refresh() {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Make the view visible to start the game session.
   */
  @Override
  public void makeVisible() {
    throw new UnsupportedOperationException("View does not support this method");
  }
}
