package model;

public class Shape {
  int tick;
  Positions pos;
  Dimensions dim;
  Color col;

  Shape(int tick, Positions pos, Dimensions dim, Color col) {
    this.tick = tick;
    this.pos = pos;
    this.dim = dim;
    this.col = col;
  }

}
