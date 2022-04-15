import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import model.IAnimatorModel;
import model.IAnimatorModelState;
import model.SimpleAnimatorModel;
import model.io.AnimationFileReader;
import view.AnimatorTextView;
import view.IAnimatorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class test for IAnimatorView class.
 */
public class AnimatorTextViewTest {
  IAnimatorModelState model;
  IAnimatorModelState model1;
  IAnimatorView view;
  IAnimatorView view1;
  IAnimatorModel inputFileToModel;
  IAnimatorView inputFileView;


  @Before
  public void setUp() {
    model = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 10, 100, 100,
                    10, 10, 10, 0, 100)
            .addColorChange("1", 10, 10, 10,
                    15, 15, 15, 0, 5)
            .addMove("1", 10, 10,
                    15, 40, 6, 7)
            .addColorChange("1", 5, 5, 5, 200, 200, 200,
                    6, 7)
            .addMove("1", 15, 40,
                    10, 10, 8, 15)
            .addScaleToChange("1", 100, 100,
                    200, 200, 15, 30)
            .addOval("2", 10, 10, 10, 50,
                    10, 10, 10, 0, 50)
            .addMove("2", 10, 10,
                    50, 50, 0, 10)
            .build();

    model1 = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10,10,30,30, 250,250,250,
                    0, 30)
            .addMove("1", 2,2, 30,30, 0,10)
            .addColorChange("1", 2,2,2, 100,100,100,5, 20)
            .addScaleToChange("1", 4,4, 20,20,0,10)
            .addMove("1", 2,2,13,13, 15, 30)
            .build();

    view = new AnimatorTextView(model);
    view1 = new AnimatorTextView(model1);

    try {
      inputFileToModel = new AnimationFileReader().readFile("resource/toh-3.txt",
              new SimpleAnimatorModel.TweenBuilder());
    } catch (FileNotFoundException e) {
      System.out.println("fail");
    }

    inputFileView = new AnimatorTextView(inputFileToModel);
  }

  @Test
  public void testToStringTest() {
    assertEquals(view.getDetails(),
            "Shape: 1 Rectangle\n" +
                    "         START                                  END \n" +
                    "motion 1 Time X Y Width Height Red Green Blue  " +
                    " Time X Y Width Height Red Green Blue\n" +
                    "motion 1 0.0 10.0 10.0 100.0 100.0 10 10 10      " +
                    " 5.0 10.0 10.0  100.0 100.0 15 15 15\n" +
                    "motion 1 5.0 10.0 10.0 100.0 100.0 15 15 15      " +
                    " 6.0 10.0 10.0  100.0 100.0 15 15 15\n" +
                    "motion 1 6.0 10.0 10.0 100.0 100.0 15 15 15      " +
                    " 7.0 15.0 40.0  100.0 100.0 15 15 15\n" +
                    "motion 1 6.0 15.0 40.0 100.0 100.0 15 15 15      " +
                    " 7.0 15.0 40.0  100.0 100.0 200 200 200\n" +
                    "motion 1 7.0 15.0 40.0 100.0 100.0 200 200 200     " +
                    "  8.0 15.0 40.0  100.0 100.0 200 200 200\n" +
                    "motion 1 8.0 15.0 40.0 100.0 100.0 200 200 200     " +
                    "  15.0 10.0 10.0  100.0 100.0 200 200 200\n" +
                    "motion 1 15.0 10.0 10.0 100.0 100.0 200 200 200      " +
                    " 30.0 10.0 10.0  200.0 200.0 200 200 200\n" +
                    "\n" +
                    "Shape: 2 Ellipse\n" +
                    "         START                                  END \n" +
                    "motion 2 Time X Y Width Height Red Green Blue   " +
                    "Time X Y Width Height Red Green Blue\n" +
                    "motion 2 0.0 10.0 10.0 10.0 50.0 10 10 10      " +
                    " 10.0 50.0 50.0  10.0 50.0 10 10 10\n" +
                    "\n");

    assertEquals(view1.getDetails(),
            "Shape: 1 Rectangle\n" +
                    "         START                                  END \n" +
                    "motion 1 Time X Y Width Height Red Green Blue   " +
                    "Time X Y Width Height Red Green Blue\n" +
                    "motion 1 0.0 10.0 10.0 30.0 30.0 250 250 250    " +
                    "   10.0 30.0 30.0  30.0 30.0 250 250 250\n" +
                    "motion 1 0.0 30.0 30.0 30.0 30.0 250 250 250     " +
                    "  10.0 30.0 30.0  20.0 20.0 250 250 250\n" +
                    "motion 1 5.0 30.0 30.0 20.0 20.0 250 250 250   " +
                    "    20.0 30.0 30.0  20.0 20.0 100 100 100\n" +
                    "motion 1 15.0 30.0 30.0 20.0 20.0 100 100 100    " +
                    "   30.0 13.0 13.0  20.0 20.0 100 100 100\n" +
                    "\n");
  }

  @Test
  public void testInputFileGetDescription() {
    assertEquals(inputFileView.getDetails(),
            "Shape: disk1 Rectangle\n" +
                    "         START                                  END \n" +
                    "motion disk1 Time X Y Width Height Red Green Blue   " +
                    "Time X Y Width Height Red Green Blue\n" +
                    "motion disk1 25.0 190.0 180.0 20.0 30.0 0 49 90     " +
                    "  35.0 190.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 35.0 190.0 50.0 20.0 30.0 0 49 90      " +
                    " 36.0 190.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 36.0 190.0 50.0 20.0 30.0 0 49 90      " +
                    " 46.0 490.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 46.0 490.0 50.0 20.0 30.0 0 49 90      " +
                    " 47.0 490.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 47.0 490.0 50.0 20.0 30.0 0 49 90      " +
                    " 57.0 490.0 240.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 57.0 490.0 240.0 20.0 30.0 0 49 90    " +
                    "   89.0 490.0 240.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 89.0 490.0 240.0 20.0 30.0 0 49 90    " +
                    "   99.0 490.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 99.0 490.0 50.0 20.0 30.0 0 49 90     " +
                    "  100.0 490.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 100.0 490.0 50.0 20.0 30.0 0 49 90    " +
                    "   110.0 340.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 110.0 340.0 50.0 20.0 30.0 0 49 90    " +
                    "   111.0 340.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 111.0 340.0 50.0 20.0 30.0 0 49 90    " +
                    "   121.0 340.0 210.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 121.0 340.0 210.0 20.0 30.0 0 49 90    " +
                    "   153.0 340.0 210.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 153.0 340.0 210.0 20.0 30.0 0 49 90    " +
                    "   163.0 340.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 163.0 340.0 50.0 20.0 30.0 0 49 90     " +
                    "  164.0 340.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 164.0 340.0 50.0 20.0 30.0 0 49 90     " +
                    "  174.0 190.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 174.0 190.0 50.0 20.0 30.0 0 49 90    " +
                    "   175.0 190.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 175.0 190.0 50.0 20.0 30.0 0 49 90    " +
                    "   185.0 190.0 240.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 185.0 190.0 240.0 20.0 30.0 0 49 90    " +
                    "   217.0 190.0 240.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 217.0 190.0 240.0 20.0 30.0 0 49 90    " +
                    "   227.0 190.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 227.0 190.0 50.0 20.0 30.0 0 49 90    " +
                    "   228.0 190.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 228.0 190.0 50.0 20.0 30.0 0 49 90    " +
                    "   238.0 490.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 238.0 490.0 50.0 20.0 30.0 0 49 90   " +
                    "    239.0 490.0 50.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 239.0 490.0 50.0 20.0 30.0 0 49 90    " +
                    "   249.0 490.0 180.0  20.0 30.0 0 49 90\n" +
                    "motion disk1 249.0 490.0 180.0 20.0 30.0 0 49 90    " +
                    "   257.0 490.0 180.0  20.0 30.0 0 255 0\n" +
                    "\n" +
                    "Shape: disk2 Rectangle\n" +
                    "         START                                  END \n" +
                    "motion disk2 Time X Y Width Height Red Green Blue " +
                    "  Time X Y Width Height Red Green Blue\n" +
                    "motion disk2 57.0 167.5 210.0 65.0 30.0 6 247 41  " +
                    "     67.0 167.5 50.0  65.0 30.0 6 247 41\n" +
                    "motion disk2 67.0 167.5 50.0 65.0 30.0 6 247 41   " +
                    "    68.0 167.5 50.0  65.0 30.0 6 247 41\n" +
                    "motion disk2 68.0 167.5 50.0 65.0 30.0 6 247 41   " +
                    "    78.0 317.5 50.0  65.0 30.0 6 247 41\n" +
                    "motion disk2 78.0 317.5 50.0 65.0 30.0 6 247 41   " +
                    "    79.0 317.5 50.0  65.0 30.0 6 247 41\n" +
                    "motion disk2 79.0 317.5 50.0 65.0 30.0 6 247 41   " +
                    "    89.0 317.5 240.0  65.0 30.0 6 247 41\n" +
                    "motion disk2 89.0 317.5 240.0 65.0 30.0 6 247 41   " +
                    "    185.0 317.5 240.0  65.0 30.0 6 247 41\n" +
                    "motion disk2 185.0 317.5 240.0 65.0 30.0 6 247 41   " +
                    "    195.0 317.5 50.0  65.0 30.0 6 247 41\n" +
                    "motion disk2 195.0 317.5 50.0 65.0 30.0 6 247 41   " +
                    "    196.0 317.5 50.0  65.0 30.0 6 247 41\n" +
                    "motion disk2 196.0 317.5 50.0 65.0 30.0 6 247 41    " +
                    "   206.0 467.5 50.0  65.0 30.0 6 247 41\n" +
                    "motion disk2 206.0 467.5 50.0 65.0 30.0 6 247 41   " +
                    "    207.0 467.5 50.0  65.0 30.0 6 247 41\n" +
                    "motion disk2 207.0 467.5 50.0 65.0 30.0 6 247 41   " +
                    "    217.0 467.5 210.0  65.0 30.0 6 247 41\n" +
                    "motion disk2 217.0 467.5 210.0 65.0 30.0 6 247 41   " +
                    "    225.0 467.5 210.0  65.0 30.0 0 255 0\n" +
                    "\n" +
                    "Shape: disk3 Rectangle\n" +
                    "         START                                  END \n" +
                    "motion disk3 Time X Y Width Height Red Green Blue  " +
                    " Time X Y Width Height Red Green Blue\n" +
                    "motion disk3 121.0 145.0 240.0 110.0 30.0 11 45 175  " +
                    "     131.0 145.0 50.0  110.0 30.0 11 45 175\n" +
                    "motion disk3 131.0 145.0 50.0 110.0 30.0 11 45 175   " +
                    "    132.0 145.0 50.0  110.0 30.0 11 45 175\n" +
                    "motion disk3 132.0 145.0 50.0 110.0 30.0 11 45 175   " +
                    "    142.0 445.0 50.0  110.0 30.0 11 45 175\n" +
                    "motion disk3 142.0 445.0 50.0 110.0 30.0 11 45 175   " +
                    "    143.0 445.0 50.0  110.0 30.0 11 45 175\n" +
                    "motion disk3 143.0 445.0 50.0 110.0 30.0 11 45 175   " +
                    "    153.0 445.0 240.0  110.0 30.0 11 45 175\n" +
                    "motion disk3 153.0 445.0 240.0 110.0 30.0 11 45 175   " +
                    "    161.0 445.0 240.0  110.0 30.0 0 255 0\n" +
                    "\n");
  }


  @Test
  public void testWriteInputFileContents() {
    inputFileView.writeFile("test2");
    File file = new File("test2.txt");
    String content = "";
    try {
      content = Files.readString(Path.of("test2.txt"), StandardCharsets.US_ASCII);
    } catch (IOException e) {
      fail();
    }
    assertEquals(inputFileView.getDetails(), content);
  }


  @Test
  public void testWriteFile() {
    view.writeFile("test");
    File file = new File("test.txt");
    assertTrue(file.exists());
  }

  @Test
  public void testWriteFileContents() {
    view.writeFile("test1");
    File file = new File("test1.txt");
    String content = "";
    try {
      content = Files.readString(Path.of("test1.txt"), StandardCharsets.US_ASCII);
    } catch (IOException e) {
      fail();
    }
    assertEquals(view.getDetails(), content);
  }




}