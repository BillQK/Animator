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
import view.AnimatorSVGView;
import view.IAnimatorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test class for AnimatorSVGView class.
 */
public class AnimatorSVGViewTest {
  IAnimatorModelState model;
  IAnimatorModelState modelWithPlus;
  IAnimatorView view;
  IAnimatorView viewWithPlus;
  IAnimatorModel inputFileToModel;
  IAnimatorView inputFileView;
  IAnimatorModel inputFileToModelWithPlus;
  IAnimatorView inputFileViewWithPlus;

  @Before
  public void setUp() {
    model = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10, 10, 100, 100,
                    10, 10, 10, 0, 100)
            .addColorChange("1", 10, 10, 10,
                    15, 15, 15, 0, 5)
            .addMove("1", 10, 10,
                    15, 40, 6, 7)
            .addMove("1", 15, 40,
                    10, 10, 8, 15)
            .addScaleToChange("1", 100, 100,
                    200, 200, 15, 30)
            .addOval("2", 10, 10, 10, 50,
                    10, 10, 10, 0, 50)
            .addMove("2", 10, 10,
                    50, 50, 0, 10)
            .build();

    modelWithPlus = new SimpleAnimatorModel.TweenBuilder()
            .addPlus("1", 10,10, 30, 30, 120,120,120, 0, 100)
            .addMove("1", 10, 10,50,50,0,15)
            .addColorChange("1", 120,120,120, 30,30,30,10,20)
            .addOval("2", 10,10,40, 50, 2,2,2,0,50)
            .addScaleToChange("2", 40,50, 20,20, 0,40)
            .build();

    try {
      inputFileToModel = new AnimationFileReader().readFile("resource/toh-3.txt",
              new SimpleAnimatorModel.TweenBuilder());
    } catch (FileNotFoundException e) {
      fail();
    }

    inputFileView = new AnimatorSVGView(model, 20);

    try {
      inputFileToModelWithPlus = new AnimationFileReader().readFile("resource/pyramid-with-plus-shape.txt",
              new SimpleAnimatorModel.TweenBuilder());
    } catch (FileNotFoundException e) {
      fail();
    }

    inputFileViewWithPlus = new AnimatorSVGView(modelWithPlus,20);

    view = new AnimatorSVGView(model, 1);
    viewWithPlus = new AnimatorSVGView(modelWithPlus, 1);
  }

  @Test
  public void testGetDetails() {
    assertEquals(view.getDetails(),
            "<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
                    "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "<rect id=\"1\" x=\"10.0\" y=\"10.0\" width=\"100.0\" height=\"100.0\" " +
                    "fill=\"rgb(10,10,10)\" visibility=\"visible\" >\n" +
                    "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"5000.0ms\" " +
                    "attributeName=\"rgb\" from=\"(10,10,10)\" to=\"(15,15,15)\"" +
                    " fill=\"freeze\" /> \n" +
                    "<animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"1000.0ms\" " +
                    "attributeName=\"x\" from=\"10.0\" to=\"15.0\" fill=\"freeze\" /> \n" +
                    "<animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"1000.0ms\" " +
                    "attributeName=\"y\" from=\"10.0\" to=\"40.0\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"8000.0ms\" dur=\"7000.0ms\" " +
                    "attributeName=\"x\" from=\"15.0\" to=\"10.0\" fill=\"freeze\" /> \n" +
                    "<animate attributeType=\"xml\" begin=\"8000.0ms\" dur=\"7000.0ms\" " +
                    "attributeName=\"y\" from=\"40.0\" to=\"10.0\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"15000.0ms\" dur=\"15000.0ms\" " +
                    "attributeName=\"width\" from=\"100.0\" to=\"200.0\" fill=\"freeze\" /> \n" +
                    "<animate attributeType=\"xml\" begin=\"15000.0ms\" dur=\"15000.0ms\" " +
                    "attributeName=\"height\" from=\"100.0\" to=\"200.0\" fill=\"freeze\" />\n" +
                    "</rect>\n" +
                    "<ellipse id=\"2\" cx=\"10.0\" cy=\"10.0\" rx=\"10.0\" ry=\"50.0\" " +
                    "fill=\"rgb(10,10,10)\" visibility=\"visible\" >\n" +
                    "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0ms\" " +
                    "attributeName=\"cx\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" /> \n" +
                    "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0ms\" " +
                    "attributeName=\"cy\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" />\n" +
                    "</ellipse>\n" +
                    "</svg>");
  }

  @Test
  public void testGetDetailsWithPlusShape() {
    assertEquals(viewWithPlus.getDetails(), "<svg width=\"700\" height=\"500\" "
            + "version=\"1.1\"\n" + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"1\" x=\"17.5\" y=\"10.0\" width=\"15.0\" height=\"30.0\" "
            + "fill=\"rgb(120,120,120)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"15000.0ms\" "
            + "attributeName=\"x\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"15000.0ms\" "
            + "attributeName=\"y\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"rgb\" from=\"(120,120,120)\" to=\"(30,30,30)\" " +
            "fill=\"freeze\" /> \n" + "</rect>\n"
            + "<rect id=\"1\" x=\"10.0\" y=\"17.5\" width=\"30.0\" height=\"15.0\" "
            + "fill=\"rgb(120,120,120)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"15000.0ms\" "
            + "attributeName=\"x\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"15000.0ms\" "
            + "attributeName=\"y\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"rgb\" from=\"(120,120,120)\" to=\"(30,30,30)\" "
            + "fill=\"freeze\" /> \n" + "</rect>\n"
            + "<ellipse id=\"2\" cx=\"10.0\" cy=\"10.0\" rx=\"40.0\" ry=\"50.0\" "
            + "fill=\"rgb(2,2,2)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"40000.0ms\" "
            + "attributeName=\"xR\" from=\"40.0\" to=\"20.0\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"40000.0ms\" "
            + "attributeName=\"yR\" from=\"50.0\" to=\"20.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>");
  }

  @Test
  public void testWriteFile() {
    view.writeFile("test");
    File file = new File("test.svg");
    assertTrue(file.exists());
  }


  @Test
  public void testWriteFileContents() {
    view.writeFile("test");
    File file = new File("test.svg");
    String content = "";
    try {
      content = Files.readString(Path.of("test.svg"), StandardCharsets.US_ASCII);
    } catch (IOException e) {
      fail();
    }
    assertEquals(view.getDetails(), content);
  }

  @Test
  public void testWriteInputFileContents() {
    inputFileView.writeFile("test1");
    File file = new File("test1.svg");
    String content = "";
    try {
      content = Files.readString(Path.of("test1.svg"), StandardCharsets.US_ASCII);
    } catch (IOException e) {
      fail();
    }
    assertEquals(inputFileView.getDetails(), content);
  }

  @Test
  public void testInputFileSVF() {
    assertEquals(inputFileView.getDetails(),
            "<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
                    "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "<rect id=\"1\" x=\"10.0\" y=\"10.0\" width=\"100.0\" height=\"100.0\" " +
                    "fill=\"rgb(10,10,10)\" visibility=\"visible\" >\n" +
                    "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"250.0ms\" " +
                    "attributeName=\"rgb\" from=\"(10,10,10)\" to=\"(15,15,15)\" " +
                    "fill=\"freeze\" /> \n" +
                    "<animate attributeType=\"xml\" begin=\"300.0ms\" dur=\"50.0ms\" " +
                    "attributeName=\"x\" from=\"10.0\" to=\"15.0\" fill=\"freeze\" /> \n" +
                    "<animate attributeType=\"xml\" begin=\"300.0ms\" dur=\"50.0ms\" " +
                    "attributeName=\"y\" from=\"10.0\" to=\"40.0\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"400.0ms\" dur=\"350.0ms\" " +
                    "attributeName=\"x\" from=\"15.0\" to=\"10.0\" fill=\"freeze\" /> \n" +
                    "<animate attributeType=\"xml\" begin=\"400.0ms\" dur=\"350.0ms\" " +
                    "attributeName=\"y\" from=\"40.0\" to=\"10.0\" fill=\"freeze\" />\n" +
                    "<animate attributeType=\"xml\" begin=\"750.0ms\" dur=\"750.0ms\" " +
                    "attributeName=\"width\" from=\"100.0\" to=\"200.0\" fill=\"freeze\" /> \n" +
                    "<animate attributeType=\"xml\" begin=\"750.0ms\" dur=\"750.0ms\" " +
                    "attributeName=\"height\" from=\"100.0\" to=\"200.0\" fill=\"freeze\" />\n" +
                    "</rect>\n" +
                    "<ellipse id=\"2\" cx=\"10.0\" cy=\"10.0\" rx=\"10.0\" ry=\"50.0\" " +
                    "fill=\"rgb(10,10,10)\" visibility=\"visible\" >\n" +
                    "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"500.0ms\" " +
                    "attributeName=\"cx\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" /> \n" +
                    "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"500.0ms\" " +
                    "attributeName=\"cy\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" />\n" +
                    "</ellipse>\n" +
                    "</svg>");
  }

  @Test
  public void testInputFileSVFWithPlus() {
    assertEquals(inputFileViewWithPlus.getDetails(), "<svg width=\"700\" height=\"500\" " +
            "version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"1\" x=\"17.5\" y=\"10.0\" width=\"15.0\" height=\"30.0\" " +
            "fill=\"rgb(120,120,120)\" visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"750.0ms\" " +
            "attributeName=\"x\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" /> \n" +
            "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"750.0ms\" " +
            "attributeName=\"y\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"500.0ms\" " +
            "attributeName=\"rgb\" from=\"(120,120,120)\" to=\"(30,30,30)\" fill=\"freeze\" /> \n" +
            "</rect>\n" +
            "<rect id=\"1\" x=\"10.0\" y=\"17.5\" width=\"30.0\" height=\"15.0\" " +
            "fill=\"rgb(120,120,120)\" visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"750.0ms\" " +
            "attributeName=\"x\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" /> \n" +
            "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"750.0ms\" " +
            "attributeName=\"y\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"500.0ms\" " +
            "attributeName=\"rgb\" from=\"(120,120,120)\" to=\"(30,30,30)\" " +
            "fill=\"freeze\" /> \n" +
            "</rect>\n" +
            "<ellipse id=\"2\" cx=\"10.0\" cy=\"10.0\" rx=\"40.0\" ry=\"50.0\" " +
            "fill=\"rgb(2,2,2)\" visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"2000.0ms\" " +
            "attributeName=\"xR\" from=\"40.0\" to=\"20.0\" fill=\"freeze\" /> \n" +
            "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"2000.0ms\" " +
            "attributeName=\"yR\" from=\"50.0\" to=\"20.0\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "</svg>");}
}