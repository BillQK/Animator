package view;

import java.util.ArrayList;
import java.util.List;

import controller.IAnimatorController;
import model.IAnimatorModelState;
import model.command.ICommandsState;
import model.shape.AShape;

/**
 * Represents the main view class which implemented the IAnimatorView.
 */
public class AnimatorTextView implements IAnimatorView {
  protected final IAnimatorModelState<?> model;
  private final List<ICommandsState> stateList;

  /**
   * A constructor for AnimatorTextView class.
   *
   * @param model the given model state to operate the operations on
   */
  public AnimatorTextView(IAnimatorModelState<?> model) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.model = model;
    stateList = new ArrayList<>();

  }

  /**
   * A method that print out the state of the model.
   *
   * @return a String
   */
  @Override
  public String getDetails() {
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

      List<ICommandsState> c = model.getCommands(s.getName());

      for (int j = 0; j < c.size(); j++) {
        ICommandsState com = c.get(j);
        if (j != (c.size() - 1)) {
          AShape g = com.getTheShape();
          finalString.append("motion ").append(com.getStart())
                  .append("       ")
                  .append(g.getName())
                  .append(g.getPosition().toString())
                  .append(g.getWidth())
                  .append(g.getHeight())
                  .append(g.getColor().getRed())
                  .append(g.getColor().getGreen())
                  .append(g.getColor().getBlue())
                  .append("      ")
                  .append(com.getEnd());
          stateList.add(com);
          g = com.getShapeAtTick(c.get(j + 1).getStart(), stateList);
          finalString.append(g.getPosition().toString())
                  .append(g.getWidth())
                  .append(g.getHeight())
                  .append(g.getColor().getRed())
                  .append(g.getColor().getGreen())
                  .append(g.getColor().getBlue() + "\n");

        } else {
          finalString.append("motion ").append(com.getBeginsState())
                  .append("    ").append(com.getEndsState()).append("\n\n");
        }
      }

//      for (int j = 0; j < c.size(); j++) {
//        ICommandsState com = c.get(j);
//        if (j != (c.size() - 1)) {
//          finalString.append("motion ").append(com.getBeginsState())
//                  .append("    ").append(com.getEndsState()).append("\n");
//          stateList.add(com);
//          c.get(j).getShapeAtTick(c.get(j + 1).getStart(), stateList);
//        } else {
//          finalString.append("motion ").append(com.getBeginsState())
//                  .append("    ").append(com.getEndsState()).append("\n\n");
//        }
//      }
    }
    return finalString.toString();

  }

  @Override
  public void writeFile(String fileName) {

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
