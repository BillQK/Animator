package model;

import java.awt.Color;

public class AShape implements IShape {
  String type;
  Color col;
  int posX;
  int posY;


  AShape(String type, Color col, int posX, int posY) {
    this.type = type;
    this.col = col;
    this.posX = posX;
    this.posY = posY;
  }


  @Override
  public void getType() {

  }

  @Override
  public void getPosition() {

  }

  @Override
  public void getColor() {

  }

  @Override
  public void getTick() {

  }
}
