import org.junit.Test;

import java.awt.*;

import model.shape.Ellipse;
import model.utils.Posn;
import model.shape.Rectangle;
import model.shape.Shape;
import model.shape.AShape;
import model.utils.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShapeTest {
  String recN = "R";
  Color recCol = new Color(0, 0, 225);
  int recPosX = 10;
  int recPosY = 10;
  int recW = 40;
  int recH = 20;
  Time recT = new Time(10, 40);
  AShape recShape = new Rectangle(recN, Shape.RECTANGLE, recCol, recPosX, recPosY,
          recW, recH, recT);

  String ellipN = "E";
  Color ellipCol = new Color(225, 0,0);
  int ellipPosX = 20;
  int ellipPosY = 10;
  int ellipW = 30;
  int ellipH = 20;
  Time ellipT = new Time(20, 30);
  AShape ellipShape = new Ellipse(ellipN, Shape.ELLIPSE, ellipCol, ellipPosX, ellipPosY,
          ellipW, ellipH, ellipT);

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
    assertEquals(recShape.getHeight(), 20);
    assertEquals(ellipShape.getHeight(), 20);
  }

  @Test
  public void testGetWidth() {
    assertEquals(recShape.getWidth(), 40);
    assertEquals(ellipShape.getWidth(), 30);
  }

  @Test
  public void testSetWidth() {
    int givw = 50;
    recShape.setWidth(givw);
    assertEquals(recShape.getWidth(), 50);
  }

  @Test
  public void testSetHeight() {
    int givh = 5;
    ellipShape.setHeight(givh);
    assertEquals(ellipShape.getHeight(), 5);
  }

  @Test
  public void testSetColor() {
    Color col = new Color(10,10, 10);
    recShape.setColor(col);
    assertEquals(recShape.getColor(), new Color(10, 10,10));
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

  //Test given a negative x position
  @Test(expected = IllegalArgumentException.class)
  public void testNegPosX() {
    AShape nnPosX = new Ellipse(ellipN, Shape.ELLIPSE, ellipCol, -10, ellipPosY,
            ellipW, ellipH, ellipT);
    assertEquals(nnPosX.getType(), Shape.ELLIPSE);
  }

  //Test given a negative y position
  @Test(expected = IllegalArgumentException.class)
  public void testNegPosY() {
    AShape nnPosY = new Ellipse(ellipN, Shape.ELLIPSE, ellipCol, ellipPosX, -10,
            ellipW, ellipH, ellipT);
    assertEquals(nnPosY.getType(), Shape.ELLIPSE);
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

}
