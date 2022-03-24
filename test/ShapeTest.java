import org.junit.Test;

import java.awt.*;

import model.shape.AShape;
import model.shape.Ellipse;
import model.shape.Rectangle;
import model.shape.Shape;
import model.utils.Posn;
import model.utils.Time;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Shape and AShape class.
 */
public class ShapeTest {
  String recN = "R";
  Color recCol = new Color(0, 0, 225);
  double recPosX = 10;
  double recPosY = 10;
  double recW = 40;
  double recH = 20;
  Time recT = new Time(10, 40);
  Shape recS = Shape.RECTANGLE;
  AShape recShape = new Rectangle(recN, Shape.RECTANGLE, recCol, recPosX, recPosY,
          recW, recH, recT);

  String ellipN = "E";
  Color ellipCol = new Color(225, 0, 0);
  double ellipPosX = 20;
  double ellipPosY = 10;
  double ellipW = 30;
  double ellipH = 20;
  Time ellipT = new Time(20, 30);
  Shape ellipS = Shape.ELLIPSE;
  AShape ellipShape = new Ellipse(ellipN, Shape.ELLIPSE, ellipCol, ellipPosX, ellipPosY,
          ellipW, ellipH, ellipT);

  double DELTA = Integer.MIN_VALUE;
  //-----------------------TEST FOR SHAPE INTERFACE----------------------------//

  @Test
  public void testGetTheShape() {
    assertEquals(recShape.getTheShape(), new Rectangle(recN, Shape.RECTANGLE, recCol, recPosX,
            recPosY, recW, recH, recT));
    assertEquals(ellipShape.getTheShape(), new Ellipse(ellipN, Shape.ELLIPSE, ellipCol, ellipPosX,
            ellipPosY, ellipW, ellipH, ellipT));
  }

  @Test
  public void testGetName() {
    assertEquals(recShape.getName(), "R");
    assertEquals(ellipShape.getName(), "E");
  }

  @Test
  public void testGetType() {
    assertEquals(recShape.getType(), Shape.RECTANGLE);
    assertEquals(ellipShape.getType(), Shape.ELLIPSE);
  }

  @Test
  public void testGetPosition() {
    assertEquals(recShape.getPosition(), new Posn(recPosX, recPosY));
    assertEquals(ellipShape.getPosition(), new Posn(ellipPosX, ellipPosY));
  }

  @Test
  public void testGetColor() {
    assertEquals(recShape.getColor(), new Color(recCol.getRGB()));
    assertEquals(ellipShape.getColor(), new Color(ellipCol.getRGB()));
  }

  @Test
  public void testGetHeight() {
    assertEquals(recShape.getHeight(), 20, DELTA);
    assertEquals(ellipShape.getHeight(), 20, DELTA);
  }

  @Test
  public void testGetWidth() {
    assertEquals(recShape.getWidth(), 40, DELTA);
    assertEquals(ellipShape.getWidth(), 30, DELTA);
  }

  @Test
  public void testSetWidth() {
    int givw = 50;
    recShape.setWidth(givw);
    assertEquals(recShape.getWidth(), 50, DELTA);
  }

  @Test
  public void testSetHeight() {
    int givh = 5;
    ellipShape.setHeight(givh);
    assertEquals(ellipShape.getHeight(), 5, DELTA);
  }

  @Test
  public void testSetColor() {
    Color col = new Color(10, 10, 10);
    recShape.setColor(col);
    assertEquals(recShape.getColor(), new Color(10, 10, 10));
  }

  @Test
  public void testSetPosn() {
    Posn pos = new Posn(60, 60);
    ellipShape.setPosn(pos);
    assertEquals(ellipShape.getPosition(), new Posn(60, 60));
  }

  //Test given a null Name
  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    AShape nT = new Rectangle(null, Shape.RECTANGLE, recCol, recPosX, recPosY,
            recW, recH, recT);
    assertEquals(nT.getColor(), recCol);
  }

  //Test given a empty Name
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyStringName() {
    AShape nT = new Rectangle("", Shape.RECTANGLE, recCol, recPosX, recPosY,
            recW, recH, recT);
    assertEquals(nT.getColor(), recCol);
  }

  //Test given a null Type
  @Test(expected = IllegalArgumentException.class)
  public void testNullType() {
    AShape nT = new Rectangle(recN, null, recCol, recPosX, recPosY, recW, recH, recT);
    assertEquals(nT.getColor(), recCol);
  }

  //Test given a null Color
  @Test(expected = IllegalArgumentException.class)
  public void testNullColor() {
    AShape nC = new Rectangle(recN, Shape.RECTANGLE, null, recPosX, recPosY,
            recW, recH, recT);
    assertEquals(nC.getType(), Shape.RECTANGLE);
  }

  //Test setColor with a given invalid parameter Color
  @Test(expected = IllegalArgumentException.class)
  public void testSetWidthWInvalidParameterColor() {
    AShape nC = new Rectangle(recN, Shape.RECTANGLE, new Color(-10, 0, 255),
            recPosX, recPosY, recW, recH, recT);
    assertEquals(nC.getType(), Shape.RECTANGLE);
  }

  //Test setColor with a given invalid parameter Color
  @Test(expected = IllegalArgumentException.class)
  public void testSetWidthWInvalidParameterColor1() {
    AShape nC = new Rectangle(recN, Shape.RECTANGLE, new Color(10, 0, 256),
            recPosX, recPosY, recW, recH, recT);
    assertEquals(nC.getType(), Shape.RECTANGLE);
  }

  //Test given a negative x position
  @Test
  public void testNegPosX() {
    AShape nnPosX = new Ellipse(ellipN, Shape.ELLIPSE, ellipCol, -10, ellipPosY,
            ellipW, ellipH, ellipT);
    assertEquals(nnPosX.getPosition().getX(), -10,Integer.MIN_VALUE);
  }

  //Test given a negative y position
  @Test
  public void testNegPosY() {
    AShape nnPosY = new Ellipse(ellipN, Shape.ELLIPSE, ellipCol, ellipPosX, -10,
            ellipW, ellipH, ellipT);
    assertEquals(nnPosY.getPosition().getY(), -10, Integer.MIN_VALUE);
  }

  //Test given a negative Width
  @Test(expected = IllegalArgumentException.class)
  public void testNegWidth() {
    AShape negW = new Rectangle(recN, Shape.RECTANGLE, recCol, recPosX, recPosY,
            -10, recH, recT);
    assertEquals(negW.getPosition(), new Posn(recPosX, recPosY));
  }

  //Test given a negative Height
  @Test(expected = IllegalArgumentException.class)
  public void testNegHeight() {
    AShape negH = new Ellipse(ellipN, Shape.ELLIPSE, ellipCol, ellipPosX, ellipPosY,
            ellipW, -10, ellipT);
    assertEquals(negH.getPosition(), new Posn(ellipPosX, ellipPosY));
  }

  //Test given a null Time
  @Test(expected = IllegalArgumentException.class)
  public void testNullTime() {
    AShape negH = new Ellipse(ellipN, Shape.ELLIPSE, ellipCol, ellipPosX, ellipPosY,
            ellipW, ellipH, null);
    assertEquals(negH.getPosition(), new Posn(ellipPosX, ellipPosY));
  }

  //Test setWidth with a given negative width
  @Test(expected = IllegalArgumentException.class)
  public void testSetWidthWNegWidth() {
    recShape.setWidth(-10);
    assertEquals(recShape.getType(), Shape.RECTANGLE);
  }


  //Test setHeight with a given negative height
  @Test(expected = IllegalArgumentException.class)
  public void testSetWidthWNegHeight() {
    recShape.setHeight(-10);
    assertEquals(recShape.getType(), Shape.RECTANGLE);
  }

  //Test setColor with a given null Color
  @Test(expected = IllegalArgumentException.class)
  public void testSetWidthWNullColor() {
    ellipShape.setColor(null);
    assertEquals(ellipShape.getType(), Shape.ELLIPSE);
  }

  //Test setPosn with a given null Posn
  @Test(expected = IllegalArgumentException.class)
  public void testSetWidthWNullPosn() {
    ellipShape.setPosn(null);
    assertEquals(ellipShape.getType(), Shape.ELLIPSE);
  }

  //-------------------------TEST FOR ENUM SHAPE-------------------------//

  @Test
  public void testRectangleShapeinEnum() {
    assertEquals(recS.getShapeType(), "Rectangle");
  }

  @Test
  public void testEllipseShapeinEnum() {
    assertEquals(ellipS.getShapeType(), "Ellipse");
  }

}
