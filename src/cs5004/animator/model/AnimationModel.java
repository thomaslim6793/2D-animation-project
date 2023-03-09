package cs5004.animator.model;

import java.util.LinkedList;

/**
 * AnimationModel represents an animation, by structuring animation elements,
 * canvas, shapes, and motions.
 *
 */
public interface AnimationModel {

  /**
   * Takes a string, and adds animationElement to the linked list based on what
   * type of element it is.
   * 
   * @param userInput String in the format such as "canvas 200 70 360 360" or
   *                  "shape R rectangle" or "motion R 1 200 200 50 100 255 0 0 10
   *                  200 200 50 100 255 0 0"
   * @throws IllegalArgumentException If the user input is not a valid string
   *                                  format.
   */
  public void addElement(String userInput) throws IllegalArgumentException;

  /**
   * Get canvas object of the animation.
   * 
   * @return CanvasElement object.
   */
  CanvasElement getCanvas();

  /**
   * Get shapeList value.
   * 
   * @return Value of shapeList.
   */
  LinkedList<ShapeElement> getShapeList();

  /**
   * Get motionList value.
   * 
   * @return Value of motionList
   */
  LinkedList<MotionElement> getMotionList();

  /**
   * String representation of shapeList.
   * 
   * @return String representation of shapeList.
   */
  String shapeListToString();

  /**
   * String representation of shapeList, including initial motion of shape and
   * final motion of shape.
   * 
   * @return String representation of shapeList, including initial motion of shape
   *         and final motion of shape.
   */
  String shapeListToStringFull();

  /**
   * String representation of a motionList object.
   * 
   * @param motionList A motionList object.
   * @return String representation of the motionList object.
   */
  String motionListToString(LinkedList<MotionElement> motionList);

  /**
   * Linked list of motionList sorted in chronological order.
   * 
   * @return LinkedList of motionList sorted in chronological order.
   */
  LinkedList<MotionElement> sortMotionsByTime(LinkedList<MotionElement> unsortedMotionList);

  /**
   * Complete string representation of animation data.
   * 
   * @return String representation of animation data in a complete format.
   */
  String toStringFull();

  /**
   * String representation of motionList in a complete format.
   * 
   * @param motionList List of motions.
   * @return String representation of motionList in a complete format.
   */
  String motionListToStringFull(LinkedList<MotionElement> motionList);

  /**
   * Unused. Method that converts string data of the model into a 2D array. Not
   * practical because way too slow on large data.
   * 
   * @param fullString String data of model.
   * @return 2D string array.
   */
  String[][] dataArray2D(String fullString);

  /**
   * Method that converts motionList into the "in-betweened" data representation
   * that is actually used for building animation with javax.swing.
   * 
   * @param motionList List of all the motions.
   * @return List of motions that have been in-betweened.
   */
  LinkedList<MotionElement> tweenedMotionList(LinkedList<MotionElement> motionList);

  /**
   * Unused. Method that converts tweened motionlist to array of ints.
   * 
   * @param tweenedMotionList As the name suggests.
   * @return Int array.
   */
  int[][] dataIntArray(LinkedList<MotionElement> tweenedMotionList);

}
