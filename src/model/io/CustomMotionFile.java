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
            + "rectangle name background min-x 0 min-y 0 width 800 height 800 color 1 1 0.9 from 1 to 200 \n"
            + "rectangle name rec1 min-x 100 min-y 400 width 50 height 25 color 0.1 0.1 0.9 from 1 to 200 \n"
            + "rectangle name rec2 min-x 150 min-y 400 width 50 height 25 color 0.1 0.9 0.1 from 1 to 200 \n"
            + "rectangle name rec3 min-x 200 min-y 400 width 50 height 25 color 0.1 0.1 0.9 from 1 to 200 \n"
            + "rectangle name rec4 min-x 250 min-y 400 width 50 height 25 color 0.1 0.9 0.1 from 1 to 200 \n"
            + "rectangle name rec5 min-x 300 min-y 400 width 50 height 25 color 0.1 0.1 0.9 from 1 to 200 \n"
            + "rectangle name rec6 min-x 350 min-y 400 width 50 height 25 color 0.1 0.9 0.1 from 1 to 200 \n"
            + "rectangle name rec7 min-x 125 min-y 375 width 50 height 25 color 0.1 0.9 0.1 from 1 to 200 \n"
            + "rectangle name rec8 min-x 175 min-y 375 width 50 height 25 color 0.1 0.1 0.9 from 1 to 200 \n"
            + "rectangle name rec9 min-x 225 min-y 375 width 50 height 25 color 0.1 0.9 0.1 from 1 to 200 \n"
            + "rectangle name rec10 min-x 275 min-y 375 width 50 height 25 color 0.1 0.1 0.9 from 1 to 200 \n"
            + "rectangle name rec11 min-x 325 min-y 375 width 50 height 25 color 0.1 0.9 0.1 from 1 to 200 \n"
            + "rectangle name rec12 min-x 150 min-y 350 width 50 height 25 color 0.1 0.1 0.9 from 1 to 200 \n"
            + "rectangle name rec13 min-x 200 min-y 350 width 50 height 25 color 0.1 0.9 0.1 from 1 to 200 \n"
            + "rectangle name rec14 min-x 250 min-y 350 width 50 height 25 color 0.1 0.1 0.9 from 1 to 200 \n"
            + "rectangle name rec15 min-x 300 min-y 350 width 50 height 25 color 0.1 0.9 0.1 from 1 to 200 \n"
            + "rectangle name rec16 min-x 175 min-y 325 width 50 height 25 color 0.1 0.1 0.9 from 1 to 200 \n"
            + "rectangle name rec17 min-x 225 min-y 325 width 50 height 25 color 0.1 0.9 0.1 from 1 to 200 \n"
            + "rectangle name rec18 min-x 275 min-y 325 width 50 height 25 color 0.1 0.1 0.9 from 1 to 200 \n"
            + "rectangle name rec19 min-x 200 min-y 300 width 50 height 25 color 0.1 0.1 0.9 from 1 to 200 \n"
            + "rectangle name rec20 min-x 250 min-y 300 width 50 height 25 color 0.1 0.9 0.1 from 1 to 200 \n"
            + "rectangle name rec21 min-x 225 min-y 275 width 50 height 25 color 0.1 0.9 0.1 from 1 to 200 \n"
            + "oval name moon center-x 500 center-y 100 x-radius 50 y-radius 50 color 1 0.1 0.1 from 1 to 200 \n"
            + "change-color name background colorto 1 1 0.9 0 0 0  from 1 to 199\n"
            + "change-color name moon colorto 1 0.1 0.1 1 1 1 from 1 to 199 \n"
            + "change-color name rec1 colorto 0.1 0.1 0.9 0.1 0.9 0.1 from 1 to 199 \n"
            + "change-color name rec2 colorto 0.1 0.9 0.1 0.1 0.1 0.9 from 1 to 199 \n"
            + "change-color name rec3 colorto 0.1 0.1 0.9 0.1 0.9 0.1 from 1 to 199 \n"
            + "change-color name rec4 colorto 0.1 0.9 0.1 0.1 0.1 0.9 from 1 to 199 \n"
            + "change-color name rec5 colorto 0.1 0.1 0.9 0.1 0.9 0.1 from 1 to 199 \n"
            + "change-color name rec6 colorto 0.1 0.9 0.1 0.1 0.1 0.9 from 1 to 199 \n"
            + "change-color name rec7 colorto 0.1 0.1 0.9 0.1 0.9 0.1 from 1 to 199 \n"
            + "change-color name rec8 colorto 0.1 0.9 0.1 0.1 0.1 0.9 from 1 to 199 \n"
            + "change-color name rec9 colorto 0.1 0.1 0.9 0.1 0.9 0.1 from 1 to 199 \n"
            + "change-color name rec10 colorto 0.1 0.9 0.1 0.1 0.1 0.9 from 1 to 199 \n"
            + "change-color name rec11 colorto 0.1 0.1 0.9 0.1 0.9 0.1 from 1 to 199 \n"
            + "change-color name rec12 colorto 0.1 0.9 0.1 0.1 0.1 0.9 from 1 to 199 \n"
            + "change-color name rec13 colorto 0.1 0.1 0.9 0.1 0.9 0.1 from 1 to 199 \n"
            + "change-color name rec14 colorto 0.1 0.9 0.1 0.1 0.1 0.9 from 1 to 199 \n"
            + "change-color name rec15 colorto 0.1 0.1 0.9 0.1 0.9 0.1 from 1 to 199 \n"
            + "change-color name rec16 colorto 0.1 0.9 0.1 0.1 0.1 0.9 from 1 to 199 \n"
            + "change-color name rec17 colorto 0.1 0.1 0.9 0.1 0.9 0.1 from 1 to 199 \n"
            + "change-color name rec18 colorto 0.1 0.9 0.1 0.1 0.1 0.9 from 1 to 199 \n"
            + "change-color name rec19 colorto 0.1 0.1 0.9 0.1 0.9 0.1 from 1 to 199 \n"
            + "change-color name rec20 colorto 0.1 0.9 0.1 0.1 0.1 0.9 from 1 to 199 \n"
            + "change-color name rec21 colorto 0.1 0.9 0.1 0.1 0.1 0.9 from 1 to 199 \n"

            ;
    return s;
  }
}
