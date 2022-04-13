import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import model.command.ChangeColor;
import model.command.ChangeDimension;
import model.command.CommandType;
import model.command.ICommandsState;
import model.command.Move;
import model.shape.AShape;
import model.shape.Rectangle;
import model.shape.Shape;
import model.utils.Posn;
import model.utils.Time;

import static org.junit.Assert.assertEquals;

/**
 * Test class ICommandsState class.
 */
public class ICommandsStateTest {
  AShape rec;
  ICommandsState changeColor;
  ICommandsState changeDimension;
  ICommandsState changePosn;

  @Before
  public void setUp() {
    rec = new Rectangle("1", Shape.RECTANGLE, new Color(100, 100, 100), 10,
            10, 100, 100, new Time(0, 20));
    changeColor = new ChangeColor(rec, 0, 10, new Color(100, 100, 100),
            new Color(120, 120, 120));
    changeDimension = new ChangeDimension(rec, 10, 15, 100,
            100, 150, 150);
    changePosn = new Move(rec, 15, 20, new Posn(10,
            10), new Posn(40, 40));
  }

  @Test
  public void getBeginsState() {
    assertEquals(changePosn.getBeginsState(), "1 15.0 10.0 10.0 100.0 100.0 100 100 100 ");
    assertEquals(changeColor.getBeginsState(), "1 0.0 10.0 10.0 100.0 100.0 100 100 100 ");
    assertEquals(changeDimension.getBeginsState(),
            "1 10.0 10.0 10.0 100.0 100.0 100 100 100 ");
  }

  @Test
  public void getEndsState() {
    assertEquals(changePosn.getEndsState(), "20.0 40.0 40.0 100.0 100.0 100 100 100 ");
    assertEquals(changeColor.getEndsState(), "10.0 10.0 10.0 100.0 100.0 120 120 120 ");
    assertEquals(changeDimension.getEndsState(), "15.0 10.0 10.0 150.0 150.0 100 100 100 ");

  }

  @Test
  public void getState() {
    assertEquals(changePosn.getState(), "1 15.0 10.0 10.0 100.0 100.0 100 100 100 20.0 40.0"
            + " 40.0 100.0 100.0 100 100 100 ");
    assertEquals(changeColor.getState(), "1 0.0 10.0 10.0 100.0 100.0 100 100 100 10.0 10.0"
            + " 10.0 100.0 100.0 120 120 120 ");
    assertEquals(changeDimension.getState(), "1 10.0 10.0 10.0 100.0 100.0 100 100 100 15.0"
            + " 10.0 10.0 150.0 150.0 100 100 100 ");
  }

  @Test
  public void getTheShape() {
    assertEquals(changePosn.getTheShape(), rec);
    assertEquals(changeColor.getTheShape(), rec);
    assertEquals(changeDimension.getTheShape(), rec);
  }

  @Test
  public void getType() {
    assertEquals(changePosn.getType(), CommandType.MOVE);
    assertEquals(changeColor.getType(), CommandType.CHANGE_COLOR);
    assertEquals(changeDimension.getType(), CommandType.CHANGE_DIMENSION);
  }

  @Test
  public void getStart() {
    assertEquals(changePosn.getStart(), 15, Integer.MIN_VALUE);
    assertEquals(changeColor.getStart(), 0, Integer.MIN_VALUE);
    assertEquals(changeDimension.getStart(), 10, Integer.MIN_VALUE);
  }

  @Test
  public void getEnd() {
    assertEquals(changePosn.getEnd(), 20, Integer.MIN_VALUE);
    assertEquals(changeColor.getEnd(), 10, Integer.MIN_VALUE);
    assertEquals(changeDimension.getEnd(), 15, Integer.MIN_VALUE);
  }

  @Test
  public void getOldPosn() {
    assertEquals(changePosn.getOldPosn(), rec.getPosition());
    assertEquals(changeColor.getOldPosn(), null);
    assertEquals(changeDimension.getOldPosn(), null);

  }

  @Test
  public void getNewPosn() {
    assertEquals(changePosn.getNewPosn(), new Posn(40, 40));
    assertEquals(changeColor.getNewPosn(), null);
    assertEquals(changeDimension.getNewPosn(), null);
  }

  @Test
  public void getOldWidth() {
    assertEquals(changePosn.getOldWidth(), -1, Integer.MIN_VALUE);
    assertEquals(changeColor.getOldWidth(), -1, Integer.MIN_VALUE);
    assertEquals(changeDimension.getOldWidth(), rec.getWidth(), Integer.MIN_VALUE);
  }

  @Test
  public void getNewWidth() {
    assertEquals(changePosn.getNewWidth(), -1, Integer.MIN_VALUE);
    assertEquals(changeColor.getNewWidth(), -1, Integer.MIN_VALUE);
    assertEquals(changeDimension.getNewWidth(), 150, Integer.MIN_VALUE);
  }

  @Test
  public void getOldHeight() {
    assertEquals(changePosn.getOldHeight(), -1, Integer.MIN_VALUE);
    assertEquals(changeColor.getOldHeight(), -1, Integer.MIN_VALUE);
    assertEquals(changeDimension.getOldHeight(), rec.getHeight(), Integer.MIN_VALUE);
  }

  @Test
  public void getNewHeight() {
    assertEquals(changePosn.getNewHeight(), -1, Integer.MIN_VALUE);
    assertEquals(changeColor.getNewHeight(), -1, Integer.MIN_VALUE);
    assertEquals(changeDimension.getNewHeight(), 150, Integer.MIN_VALUE);
  }

  @Test
  public void getOldColor() {
    assertEquals(changePosn.getNewWidth(), -1, Integer.MIN_VALUE);
    assertEquals(changeColor.getNewWidth(), -1, Integer.MIN_VALUE);
    assertEquals(changeDimension.getNewWidth(), 150, Integer.MIN_VALUE);
  }

  @Test
  public void getNewColor() {
    assertEquals(changePosn.getNewWidth(), -1, Integer.MIN_VALUE);
    assertEquals(changeColor.getNewWidth(), -1, Integer.MIN_VALUE);
    assertEquals(changeDimension.getNewWidth(), 150, Integer.MIN_VALUE);
  }

  @Test
  public void getShapeAtTick() {
    assertEquals(changePosn.getShapeAtTick(20, rec).getPosition(), new Posn(40, 40));
    assertEquals(changeColor.getShapeAtTick(10, rec).getColor(),
            new Color(120, 120, 120));
    assertEquals(changeDimension.getShapeAtTick(15, rec).getHeight(),
            150, Integer.MIN_VALUE);
    assertEquals(changeDimension.getShapeAtTick(15, rec).getWidth(),
            150, Integer.MIN_VALUE);
  }

  @Test
  public void getSVG() {
    assertEquals(changePosn.getSVG(10),
            "<animate attributeType=\"xml\" begin=\"1500.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"x\" from=\"10.0\" to=\"40.0\" fill=\"freeze\" /> \n" +
                    "<animate attributeType=\"xml\" begin=\"1500.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"y\" from=\"10.0\" to=\"40.0\" fill=\"freeze\" />\n");
    assertEquals(changeColor.getSVG(10),
            "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"1000.0ms\" "
                    + "attributeName=\"rgb\" from=\"(100,100,100)\" to=\"(120,120,120)\" "
                    + "fill=\"freeze\" /> \n");
    assertEquals(changeDimension.getSVG(10),
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"width\" from=\"100.0\" to=\"150.0\" fill=\"freeze\" /> \n"
                    + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"500.0ms\""
                    + " attributeName=\"height\" from=\"100.0\" to=\"150.0\" fill=\"freeze\""
                    + " />\n");
  }
}