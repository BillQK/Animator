package model.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CustomMotionFile implements ICreateMotionFIle {

  @Override
  public void createFile(String name) {
    try {
      BufferedWriter output = new BufferedWriter(new FileWriter(name + ".txt"));
      output.write(createCustomAnimation());
      output.close();
    } catch (IOException e) {
      throw new IllegalStateException("Cannot write file");
    }
  }

  private String createCustomAnimation() {
    String s = "canvas 400 500 \n"
    + "rectangle name background min-x 0 min-y 0 width 800 height 800 color 0.1333 0.37 0.976 from 1 to 200 \n"
    + "";
  }
}
