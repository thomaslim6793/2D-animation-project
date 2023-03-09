package cs5004.animator.model;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * AnimationModel represents an animation, by structuring animation elements,
 * canvas, shapes, and motions, using a linked list.
 *
 */
public class AnimationModelImpl implements AnimationModel {

  /**
   * Three separate fields. Canvas object, linked list for shapes, and linked list
   * for motions.
   */
  private LinkedList<AnimationElement> animationElementsList;

  private CanvasElement canvas;
  private LinkedList<ShapeElement> shapeList;
  private LinkedList<MotionElement> motionList;

  /**
   * Constructor creates an empty linked list.
   */
  public AnimationModelImpl() {
    this.animationElementsList = new LinkedList<AnimationElement>();

    this.canvas = null;
    this.shapeList = new LinkedList<ShapeElement>();
    this.motionList = new LinkedList<MotionElement>();
  }

  @Override
  public int[][] dataIntArray(LinkedList<MotionElement> tweenedMotionList) {

    String data = this.motionListToStringFull(tweenedMotionList);
    String[][] dataArray = this.dataArray2D(data);
    int[][] intArray2D = new int[dataArray.length][9];

    for (int i = 0; i < dataArray.length; i++) {
      for (int j = 2; j < 11; j++) {
        if (j == 2 && dataArray[i][j].toLowerCase().equals("rectangle")) {
          intArray2D[i][j - 2] = 0; // 0 means rectangle
        } else if (j == 2 && (dataArray[i][j].toLowerCase().equals("oval")
            || dataArray[i][j].toLowerCase().equals("ellipse"))) {
          intArray2D[i][j - 2] = 1; // 1 means ellipse
        } else {
          intArray2D[i][j - 2] = -1; // -1 means some other shape.
        }

        if (j > 2) {
          intArray2D[i][j - 2] = Integer.parseInt(dataArray[i][j]);
        }
      }
    }

    return intArray2D;
  }

  @Override
  public LinkedList<MotionElement> tweenedMotionList(LinkedList<MotionElement> motionList) {
    LinkedList<MotionElement> tweenedList = new LinkedList<MotionElement>();

    int len = motionList.size();
    for (int i = 0; i < len; i++) {
      MotionElement current = motionList.get(i);
      double t1 = current.getT1();
      double t2 = current.getT2();
      String name = current.getName();
      String shape = current.getShape();
      int t;
      int x;
      int y; 
      int w;
      int h;
      int r;
      int g;
      int b;
      for (int j = current.getT1(); j < (t2 + 1); j++) {
        t = j;
        x = (int) (Math.round(
            current.getX1() * ((t2 - t) / (t2 - t1)) + current.getX2() * ((t - t1) / (t2 - t1))));
        y = (int) (Math.round(
            current.getY1() * ((t2 - t) / (t2 - t1)) + current.getY2() * ((t - t1) / (t2 - t1))));
        w = (int) (Math.round(
            current.getW1() * ((t2 - t) / (t2 - t1)) + current.getW2() * ((t - t1) / (t2 - t1))));
        h = (int) (Math.round(
            current.getH1() * ((t2 - t) / (t2 - t1)) + current.getH2() * ((t - t1) / (t2 - t1))));
        r = (int) (Math.round(
            current.getR1() * ((t2 - t) / (t2 - t1)) + current.getR2() * ((t - t1) / (t2 - t1))));
        g = (int) (Math.round(
            current.getG1() * ((t2 - t) / (t2 - t1)) + current.getG2() * ((t - t1) / (t2 - t1))));
        b = (int) (Math.round(
            current.getB1() * ((t2 - t) / (t2 - t1)) + current.getB2() * ((t - t1) / (t2 - t1))));

        MotionElement tweenState = new MotionElement(name, t, x, y, w, h, r, g, b, -1, -1, -1, -1,
            -1, -1, -1, -1);
        tweenState.updateShape(shape);
        tweenedList.add(tweenState);

      }
    }

    return tweenedList;
  }

  @Override
  public String toString() {
    return this.animationElementsList.stream().map(element -> element.toString())
        .reduce("", (a, b) -> a + "\n" + b).trim();
  }

  @Override
  public String toStringFull() {
    String s1 = this.canvas.toString();
    String s2 = this.shapeList.stream().map(element -> element.toStringFull())
        .reduce("", (a, b) -> a + "\n" + b).trim();
    String s3 = this.sortMotionsByTime(this.motionList).stream()
        .map(element -> element.toStringFull()).reduce("", (a, b) -> a + "\n" + b).trim();
    return s1 + "\n" + s2 + "\n" + s3;
  }

  @Override
  public CanvasElement getCanvas() {
    return this.canvas;
  }

  @Override
  public LinkedList<ShapeElement> getShapeList() {
    return this.shapeList;
  }

  @Override
  public LinkedList<MotionElement> getMotionList() {
    return this.motionList;
  }

  @Override
  public String shapeListToString() {
    return this.shapeList.stream().map(element -> element.toString())
        .reduce("", (a, b) -> a + "\n" + b).trim();
  }

  @Override
  public String shapeListToStringFull() {
    return this.shapeList.stream().map(element -> element.toStringFull())
        .reduce("", (a, b) -> a + "\n" + b).trim();
  }

  @Override
  public String motionListToString(LinkedList<MotionElement> motionList) {
    return motionList.stream().map(element -> element.toString()).reduce("", (a, b) -> a + "\n" + b)
        .trim();
  }

  @Override
  public String motionListToStringFull(LinkedList<MotionElement> motionList) {
    return motionList.stream().map(element -> element.toStringFull())
        .reduce("", (a, b) -> a + "\n" + b).trim();
  }

  @Override
  public LinkedList<MotionElement> sortMotionsByTime(LinkedList<MotionElement> unsortedMotionList) {
    return unsortedMotionList.stream()
        .sorted((object1, object2) -> object1.getT1() - object2.getT1())
        .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  public String[][] dataArray2D(String fullString) {
    String data = fullString;
    int rows = data.split("\n").length;
    int cols = 19;
    String[][] dataArray = new String[rows][cols];

    for (int i = 0; i < rows; i++) {
      String ithLine = data.split("\n")[i];
      int numElements = ithLine.split(" ").length;
      for (int j = 0; j < numElements; j++) {
        String jthElement = ithLine.split(" ")[j];
        dataArray[i][j] = jthElement;
      }
    }

    return dataArray;
  }

  @Override
  public void addElement(String userInput) throws IllegalArgumentException {
    // E.g. userInput for canvas = "canvas 200 70 360 360"
    // E.g. UI for shape = "shape R rectangle"
    // E.g. UI for motion = "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255
    // 0 0"

    String[] splittedUI = userInput.split("\\s+"); // split string by any number of whitespaces.
    String firstWord = splittedUI[0].toLowerCase();

    if (!firstWord.equals("canvas") && !firstWord.equals("shape") && !firstWord.equals("motion")) {
      throw new IllegalArgumentException(
          "Incorrect user input. First word must be either 'canvas', 'shape', or 'motion'.");
    }

    if (firstWord.equals("canvas")) {
      CanvasElement node = makeCanvasElement(splittedUI);
      this.canvas = node;
      this.animationElementsList.add(node);
    } else if (firstWord.equals("shape")) {
      ShapeElement node = makeShapeElement(splittedUI);
      this.shapeList.add(node);
      this.animationElementsList.add(node);
    } else if (firstWord.equals("motion")) {
      // Get shape of the motion and assign it as motion's field value.
      MotionElement node = makeMotionElement(splittedUI);
      String motionShape = this.shapeList.stream()
          .filter(shape -> shape.getName().equals(node.getName())).findFirst().get().getShape();
      node.updateShape(motionShape);
      this.motionList.add(node);
      // For each shape, if shape name equal to name of object of motion, if the t1 of
      // motion is less than t1 of shape, set this as the initial motion
      // of that shape.
      this.shapeList.stream().filter(shape -> shape.getName().equals(node.getName())).map(shape -> {
        if (shape.getInitialMotion() == null
            || node.getMotionAttributes()[0] < shape.getInitialMotion().getMotionAttributes()[0]) {
          return shape.updateInitMotion(node);
        } else {
          return shape;
        }
      }).collect(Collectors.toCollection(LinkedList::new));
      // For each shape, if shape name equal to name of object of motion, if the t2 of
      // motion is greater than t2 of shape, set this as the final motion
      // of that shape.
      this.shapeList.stream().filter(shape -> shape.getName().equals(node.getName())).map(shape -> {
        if (shape.getFinalMotion() == null
            || node.getMotionAttributes()[8] > shape.getFinalMotion().getMotionAttributes()[8]) {
          return shape.updateFinalMotion(node);
        } else {
          return shape;
        }
      }).collect(Collectors.toCollection(LinkedList::new));

      this.animationElementsList.add(node);
    }
  }

  /**
   * Helper method that returns a MotionElement object given a string array.
   * 
   * @param splittedUI User input which is split by whitespace.
   * @return MotionElement object made from elements of splittedUI array.
   * @throws IllegalArgumentException If splittedUI does not have proper format or
   *                                  provides invalid arguments.
   */
  private MotionElement makeMotionElement(String[] splittedUI) throws IllegalArgumentException {
    int arrayLength = splittedUI.length;
    if (arrayLength != 18) {
      throw new IllegalArgumentException("Motion element UI has 18 fields.");
    }

    for (int i = 2; i < arrayLength; i++) {
      try {
        Integer.parseInt(splittedUI[i]);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("The last 16 arguments must be integer values.");
      }
    }

    return new MotionElement(splittedUI[1], Integer.parseInt(splittedUI[2]),
        Integer.parseInt(splittedUI[3]), Integer.parseInt(splittedUI[4]),
        Integer.parseInt(splittedUI[5]), Integer.parseInt(splittedUI[6]),
        Integer.parseInt(splittedUI[7]), Integer.parseInt(splittedUI[8]),
        Integer.parseInt(splittedUI[9]), Integer.parseInt(splittedUI[10]),
        Integer.parseInt(splittedUI[11]), Integer.parseInt(splittedUI[12]),
        Integer.parseInt(splittedUI[13]), Integer.parseInt(splittedUI[14]),
        Integer.parseInt(splittedUI[15]), Integer.parseInt(splittedUI[16]),
        Integer.parseInt(splittedUI[17]));

  }

  /**
   * Helper method that returns a ShapeElement object given a string array.
   * 
   * @param splittedUI User input which is split by whitespace.
   * @return ShapeElement object made from elements of splittedUI array.
   * @throws IllegalArgumentException If splittedUI does not have proper format or
   *                                  provides invalid arguments.
   */
  private ShapeElement makeShapeElement(String[] splittedUI) throws IllegalArgumentException {
    int arrayLength = splittedUI.length;
    if (arrayLength != 3) {
      throw new IllegalArgumentException("Shape element has 3 attributes.");
    }

    return new ShapeElement(splittedUI[1], splittedUI[2]);
  }

  /**
   * Helper method that returns a CanvasElement object given a string array.
   * 
   * @param splittedUI User input which is split by whitespace.
   * @return CanvasElement object made from elements of splittedUI array.
   * @throws IllegalArgumentException If splittedUI does not have proper format or
   *                                  provides invalid arguments.
   */
  private CanvasElement makeCanvasElement(String[] splittedUI) throws IllegalArgumentException {
    int arrayLength = splittedUI.length;
    if (arrayLength != 5) {
      throw new IllegalArgumentException("Canvas element UI 5 attributes.");
    }

    for (int i = 1; i < arrayLength; i++) {
      try {
        Integer.parseInt(splittedUI[i]);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("The last 4 arguments must be integer values.");
      }
    }

    return new CanvasElement(Integer.parseInt(splittedUI[1]), Integer.parseInt(splittedUI[2]),
        Integer.parseInt(splittedUI[3]), Integer.parseInt(splittedUI[4]));
  }

}