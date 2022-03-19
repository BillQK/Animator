import org.junit.Test;

import java.awt.*;

import model.AShape;
import model.Ellipse;
import model.IShape;
import model.Posn;
import model.Rectangle;
import model.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShapeTest {
  Color recCol = new Color(0, 0, 225);
  int recPosX = 10;
  int recPosY = 10;
  int recW = 40;
  int recH = 20;
  IShape recShape = new Rectangle(Shape.RECTANGLE, recCol, recPosX, recPosY, recW, recH);

  Color ellipCol = new Color(225, 0,0);
  int ellipPosX = 20;
  int ellipPosY = 10;
  int ellipW = 30;
  int ellipH = 20;
  IShape ellipShape = new Ellipse(Shape.ELLIPSE, ellipCol, ellipPosX, ellipPosY,
          ellipW, ellipH);

  //-----------------------TEST for SHAPE INTERFACE----------------------------//
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

  //Test given a null Type
  @Test(expected = IllegalArgumentException.class)
  public void testNullType() {
    IShape nT = new Rectangle(null, recCol, recPosX, recPosY, recW, recH);
    assertEquals(nT.getColor(), recCol);
  }

  //Test given a null Color
  @Test(expected = IllegalArgumentException.class)
  public void testNullColor() {
    IShape nC = new Rectangle(Shape.RECTANGLE, null, recPosX, recPosY, recW, recH);
    assertEquals(nC.getType(), Shape.RECTANGLE);
  }

  //Test given a negative x position
  @Test(expected = IllegalArgumentException.class)
  public void testNegPosX() {
    IShape nnPosX = new Ellipse(Shape.ELLIPSE, ellipCol, -10, ellipPosY, ellipW, ellipH);
    assertEquals(nnPosX.getType(), Shape.ELLIPSE);
  }

  //Test given a negative y position
  @Test(expected = IllegalArgumentException.class)
  public void testNegPosY() {
    IShape nnPosY = new Ellipse(Shape.ELLIPSE, ellipCol, ellipPosX, -10, ellipW, ellipH);
    assertEquals(nnPosY.getType(), Shape.ELLIPSE);
  }

  //Test given a negative Width
  @Test(expected = IllegalArgumentException.class)
  public void testNegWidth() {
    IShape negW = new Rectangle(Shape.RECTANGLE, recCol, recPosX, recPosY, -10, recH);
    assertEquals(negW.getPosition(), new Posn(recPosX, recPosY));
  }

  //Test given a negative Height
  @Test(expected = IllegalArgumentException.class)
  public void testNegHeight() {
    IShape negH = new Ellipse(Shape.ELLIPSE, ellipCol, ellipPosX, ellipPosY, ellipW, -10);
    assertEquals(negH.getPosition(), new Posn(ellipPosX, ellipPosY));
  }

  //

}
