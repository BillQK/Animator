package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.IAnimatorModelState;
import model.command.ICommandsState;
import model.shape.AShape;

/**
 * Represents the Main textual view class which implemented the IAnimatorView.
 */
public class AnimatorTextView implements IAnimatorView {
  protected final IAnimatorModelState model;

  /**
   * A constructor for AnimatorTextView class.
   *
   * @param model the given model state to operate the operations on
   */
  public AnimatorTextView(IAnimatorModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.model = model;

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

      AShape shape = c.get(0).getTheShape();
      for (int j = 0; j < c.size(); j++) {
        ICommandsState com = c.get(j);
        if (j != (c.size() - 1)) {
          finalString.append("motion ").append(s.getName() + " ")
                  .append(com.getStart() + " ")
                  .append(shape.getPosition().toString())
                  .append(shape.getWidth() + " ")
                  .append(shape.getHeight() + " ")
                  .append(shape.getColor().getRed() + " ")
                  .append(shape.getColor().getGreen() + " ")
                  .append(shape.getColor().getBlue() + "       ");

          shape = shape.updateShape(com);

          finalString.append(com.getEnd() + " ")
                  .append(shape.getPosition().toString() + " ")
                  .append(shape.getWidth() + " ")
                  .append(shape.getHeight() + " ")
                  .append(shape.getColor().getRed() + " ")
                  .append(shape.getColor().getGreen() + " ")
                  .append(shape.getColor().getBlue() + "\n");
        } else {
          finalString.append("motion ").append(s.getName() + " ")
                  .append(com.getStart() + " ")
                  .append(shape.getPosition().toString())
                  .append(shape.getWidth() + " ")
                  .append(shape.getHeight() + " ")
                  .append(shape.getColor().getRed() + " ")
                  .append(shape.getColor().getGreen() + " ")
                  .append(shape.getColor().getBlue() + "       ");

          shape = shape.updateShape(com);

          finalString.append(com.getEnd() + " ")
                  .append(shape.getPosition().toString() + " ")
                  .append(shape.getWidth() + " ")
                  .append(shape.getHeight() + " ")
                  .append(shape.getColor().getRed() + " ")
                  .append(shape.getColor().getGreen() + " ")
                  .append(shape.getColor().getBlue() + "\n\n");
        }
      }
    }
    return finalString.toString();

  }

  /**
   * A method to based on the given fileName to print out the according view.
   *
   * @param fileName String - a given fileName
   */
  @Override
  public void writeFile(String fileName) {
    String des = this.getDetails();
    try {
      BufferedWriter output = new BufferedWriter(new FileWriter(fileName + ".txt"));
      output.write(des);
      output.close();
    } catch (IOException e) {
      throw new IllegalStateException("Cannot write file");
    }
  }

  /**
   * Set up the button listener to handle the button click events in this view.
   *
   * @param listener the action listener
   */
  public void addListener(ActionListener listener) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Set up the key listener to handle the key pressed in this view.
   *
   * @param klistener the action listener
   */
  public void addKeyListener(KeyListener klistener) {
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

  /**
   * A method to set the list of shapes field to the given list of shape arguments.
   *
   * @param losTempo the given list of Shapes
   */
  @Override
  public void setShapes(List<AShape> losTempo) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Method to get the IsLoop current boolean.
   *
   * @return boolean - the current IsLoop boolean
   */
  @Override
  public boolean getIsLoop() {
    throw new UnsupportedOperationException("View doesn't support this method");
  }

  /**
   * Method to set the boolean IsLoop into the given boolean.
   *
   * @param loop boolean - the given boolean to set the IsLoop as
   */
  @Override
  public void setIsLoop(boolean loop) {
    throw new UnsupportedOperationException("View doesn't support this method");
  }

  /**
   * Method to get the IsOutline current boolean.
   *
   * @return boolean - the current IsOutline boolean
   */
  public boolean getIsOutline() {
    throw new UnsupportedOperationException("View doesn't support this method");
  }

  /**
   * Method to set the boolean IsOutline into the given boolean.
   *
   * @param outline boolean - the given boolean to set the IsOutline as
   */
  public void setIsOutline(boolean outline) {
    throw new UnsupportedOperationException("View doesn't support this method");
  }

  /**
<<<<<<< HEAD
   * Method to get the IsDiscreteT current boolean.
   *
   * @return boolean - the current IsDiscreteT boolean
   */
  public boolean getIsDiscreteT() {
=======
   * Method to get the IsOutline current boolean.
   *
   * @return boolean - the current IsOutline boolean
   */
  public boolean getIsOutline() {
>>>>>>> 977aa0c7e733742f5f29c4d3af2b4f8bf7fcd1f8
    throw new UnsupportedOperationException("View doesn't support this method");
  }

  /**
   * Method to set the boolean IsDiscreteT into the given boolean.
   *
   * @param discreteT boolean - the given boolean to set the IsDiscreteT as
   */
  public void setIsDiscreteT(boolean discreteT) {
    throw new UnsupportedOperationException("View doesn't support this method");
  }

<<<<<<< HEAD
=======
  /**
   * Method to get the IsDiscreteT current boolean.
   *
   * @return boolean - the current IsDiscreteT boolean
   */
  public boolean getIsDiscreteT() {
    throw new UnsupportedOperationException("View doesn't support this method");
  }

>>>>>>> 977aa0c7e733742f5f29c4d3af2b4f8bf7fcd1f8
}
