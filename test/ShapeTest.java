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

}
