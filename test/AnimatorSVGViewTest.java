import org.junit.Before;
import org.junit.Test;

import java.io.File;

import model.IAnimatorModelState;
import model.SimpleAnimatorModel;
import model.shape.AShape;
import view.AnimatorSVGView;
import view.IAnimatorView;

import static org.junit.Assert.*;

public class AnimatorSVGViewTest {
  IAnimatorModelState<AShape> model;
  IAnimatorView view;

  @Before
  public void setUp() {
    model = new SimpleAnimatorModel.TweenBuilder()
            .addRectangle("1", 10,10,100,100,10,10,10,0,100)
            .addColorChange("1", 10,10,10,15,15,15,0,5)
            .addMove("1", 10,10,15,40, 6, 7)
            .addMove("1", 15,40,10,10, 8, 15)
            .addScaleToChange("1", 100, 100, 200, 200,15, 30)
            .addOval("2", 10,10,10,50, 10, 10,10, 0,50)
            .addMove("2", 10,10,50,50,0,10)
            .build();

    view = new AnimatorSVGView(model, 10);
  }
  @Test
  public void testGetDetails() {
    assertEquals(view.getDetails(),
            "<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"1\" x=\"10.0\" y=\"10.0\" width=\"100.0\" height=\"100.0\" fill=\"rgb(10,10,10)\" visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"500.0ms\" attributeName=\"rgb\" from=\"(10,10,10)\" to=\"(15,15,15)\" fill=\"freeze\" /> \n" +
            "<animate attributeType=\"xml\" begin=\"600.0ms\" dur=\"100.0ms\" attributeName=\"x\" from=\"10.0\" to=\"15.0\" fill=\"freeze\" /> \n" +
            "<animate attributeType=\"xml\" begin=\"600.0ms\" dur=\"100.0ms\" attributeName=\"y\" from=\"10.0\" to=\"40.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"800.0ms\" dur=\"700.0ms\" attributeName=\"x\" from=\"10.0\" to=\"10.0\" fill=\"freeze\" /> \n" +
            "<animate attributeType=\"xml\" begin=\"800.0ms\" dur=\"700.0ms\" attributeName=\"y\" from=\"10.0\" to=\"10.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1500.0ms\" dur=\"1500.0ms\" attributeName=\"width\" from=\"100.0\" to=\"200.0\" fill=\"freeze\" /> \n" +
            "<animate attributeType=\"xml\" begin=\"1500.0ms\" dur=\"1500.0ms\" attributeName=\"height\" from=\"100.0\" to=\"200.0\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "<ellipse id=\"2\" cx=\"10.0\" cy=\"10.0\" rx=\"10.0\" ry=\"50.0\" fill=\"rgb(10,10,10)\" visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"1000.0ms\" attributeName=\"cx\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" /> \n" +
            "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"1000.0ms\" attributeName=\"cy\" from=\"10.0\" to=\"50.0\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "</svg>");
  }

  @Test
  public void testWriteFile() {
    view.writeFile("test");
    File file = new File("test.svg");
    assertTrue(file.exists());
  }
}