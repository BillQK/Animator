package model.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CustomMotionFile implements ICreateMotionFIle {
  private final int numShapes;

  public CustomMotionFile(int numShapes) {
    this.numShapes = numShapes;
  }

  @Override
  public void createFile(String name) {
    try {
      BufferedWriter output = new BufferedWriter(new FileWriter(name + ".txt"));
      output.write("");
      output.close();
    } catch (IOException e) {
      throw new IllegalStateException("Cannot write file");
    }
  }
}
