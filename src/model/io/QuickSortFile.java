package model.io;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.command.ChangeColor;
import model.command.ICommands;
import model.command.Move;
import model.shape.AShape;
import model.shape.Rectangle;
import model.shape.Shape;
import model.utils.Posn;
import model.utils.Time;

public class QuickSortFile implements ICreateMotionFIle {
  private final int numShape;
  private final AShape[] shapeList;
  private final double[] areaList;
  private final List<ICommands> animationList;
  private static final int xPosition = 100;
  private static final int yPosition = 150;
  private static final double WIDTH = 10;
  private static final int end = 500;
  private Random random;
  private static int startTime = 0;
  private static int endTime = 1;

  public QuickSortFile(int numShape) {
    this.numShape = numShape;
    this.areaList = new double[numShape];
    this.shapeList = new AShape[numShape];
    this.animationList = new ArrayList<>();
    this.random = new Random();
  }

  @Override
  public void createFile(String name) {
    try {
      BufferedWriter output = new BufferedWriter(new FileWriter(name + ".txt"));
      output.write("canvas " + 400 +" " + 500);
      output.write(createRandomObject(numShape));
      output.write(createCommandObject());
      output.close();
    } catch (IOException e) {
      throw new IllegalStateException("Cannot write file");
    }
  }

  private void createChangeColor() {
    for (AShape a : shapeList) {
      ICommands changeColor = new ChangeColor(a, startTime, endTime,
              a.getColor(), new Color(2, 255, 2));
      startTime++;
      endTime++;
      animationList.add(changeColor);
    }
  }

  private String createRandomObject(int numShape) {
    for (int i = 0; i < numShape; i++) {
      int rand = random.nextInt(10) + 1;

      AShape current = new Rectangle(String.valueOf(i), Shape.RECTANGLE,
              new Color(rand, 0, 0),
              xPosition + i * WIDTH, yPosition,
              WIDTH, rand * 50, new Time(0, end));
      shapeList[i] = current;
    }
    StringBuilder shapeCommand = new StringBuilder();
    for (AShape a : shapeList) {
      shapeCommand.append(a.getCommand()).append("\n");
    }
    return shapeCommand.toString();
  }

  private String createCommandObject() {
    for (int i = 0; i < shapeList.length; i++) {
      areaList[i] = shapeList[i].getHeight() * shapeList[i].getWidth();
    }
    quickSort(areaList, 0, numShape - 1);
    createChangeColor();
    StringBuilder animationCommand = new StringBuilder();
    for (ICommands c : this.animationList) {
      animationCommand.append(c.getCommandString()).append("\n");
    }
    return animationCommand.toString();
  }

  // A utility function to swap two elements
  private void swap(double[] arr, int i, int j) {

    ICommands c1;
    ICommands c2;
    if (i != j) {
      AShape s1 = shapeList[i];
      AShape s2 = shapeList[j];
      Posn p1 = new Posn(s1.getPosition());
      Posn p2 = new Posn(s2.getPosition());

      c1 = new Move(s1, startTime, endTime, p1, p2);
      s1.setPosn(p2);
      startTime++;
      endTime++;
      c2 = new Move(s2, startTime, endTime, p2, p1);
      s2.setPosn(p1);
      startTime++;
      endTime++;
      this.animationList.add(c1);
      this.animationList.add(c2);
    }

    double temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
    AShape temp1 = this.shapeList[i];
    shapeList[i] = shapeList[j];
    shapeList[j] = temp1;


  }

  private int partition(double[] arr, int low, int high) {
    // pivot
    double pivot = arr[high];

    int i = (low - 1);

    for (int j = low; j <= high - 1; j++) {

      // If current element is smaller
      // than the pivot
      if (arr[j] < pivot) {

        // Increment index of
        // smaller element
        i++;
        swap(arr, i, j);
      }
    }
    swap(arr, i + 1, high);
    return (i + 1);
  }

  /* The main function that implements QuickSort
            arr[] --> Array to be sorted,
            low --> Starting index,
            high --> Ending index
   */
  private void quickSort(double[] arr, int low, int high) {
    if (low < high) {

      // pi is partitioning index, arr[p]
      // is now at right place
      int pi = partition(arr, low, high);

      // Separately sort elements before
      // partition and after partition
      quickSort(arr, low, pi - 1);
      quickSort(arr, pi + 1, high);
    }
  }
}
