package model.io;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import model.shape.AShape;
import model.shape.Rectangle;
import model.shape.Shape;
import model.utils.Time;

public class QuickSortFile implements ICreateMotionFIle {
  private final int numShape;
  private final List<AShape> shapeList;
  private static final int xPosition = 100;
  private static final int yPosition = 100;
  private static final double WIDTH = 100;
  private static final double start = 0;
  private static final double end = 500;

  public QuickSortFile(int numShape) {
    this.numShape = numShape;
    this.shapeList = new ArrayList<>();
  }

  @Override
  public void createFile(String name) {

  }

  private String createRandomObject(int numShape) {
    for (int i = 0; i < numShape; i++) {
      AShape current = new Rectangle(String.valueOf(i), Shape.RECTANGLE,
              new Color((int) (i * Math.random()), (int) (i * Math.random()), (int) (i * Math.random())),
              xPosition, yPosition,
              WIDTH, i + 100, new Time(start, end));
      shapeList.add(current);
    }
    StringBuilder shapeCommand = new StringBuilder();
    for (AShape a : shapeList) {
      shapeCommand.append(a.getCommand()).append("\n");
    }
    return shapeCommand.toString();
  }

  private String createCommandObject() {
    return "";
  }

  public void quickSort(int arr[], int begin, int end) {
    if (begin < end) {
      int partitionIndex = partition(arr, begin, end);

      quickSort(arr, begin, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, end);
    }
  }

  private int partition(int arr[], int begin, int end) {
    int pivot = arr[end];
    int i = (begin - 1);

    for (int j = begin; j < end; j++) {
      if (arr[j] <= pivot) {
        i++;

        int swapTemp = arr[i];
        arr[i] = arr[j];
        arr[j] = swapTemp;
      }
    }

    int swapTemp = arr[i + 1];
    arr[i + 1] = arr[end];
    arr[end] = swapTemp;

    return i + 1;
  }
}
