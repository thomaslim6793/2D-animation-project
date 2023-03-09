package cs5004.animator.view;

/**
 * Interface for text view.
 *
 */
public interface AnimationTextView {

  /**
   * Return text description of creation of a shape.
   * 
   * @param shapeListStringFull String data of a shape formatted as follows:
   *                            "elementType name type t1 x1 y1 w1 h1 r1 g1 b1 t2
   *                            x2 y2 w2 h2 r2 g2 b2".
   * @return text description of creation of a shape.
   */
  String textViewShapeCreation(String shapeListStringFull);

  /**
   * Return text description of lifetime of a shape.
   * 
   * @param shapeToStringFull String data of a shape formatted as follows:
   *                            "elementType name type t1 x1 y1 w1 h1 r1 g1 b1 t2
   *                            x2 y2 w2 h2 r2 g2 b2".
   * @return text description of life time of a shape.
   */
  String textViewShapeLifeTime(String shapeToStringFull);

  /**
   * Return text description of motion for moving in space.
   * 
   * @param motionToString String format for motion as follows: "motion R 1 200
   *                       200 50 100 255 0 0 10 200 200 50 100 255 0 0"
   * @return text description of motion for moving in space.
   */
  String textViewMotionMoves(String motionToString);

  /**
   * Return text description of motion for changing color.
   * 
   * @param motionToString String format for motion as follows: "motion R 1 200
   *                       200 50 100 255 0 0 10 200 200 50 100 255 0 0"
   * @return text description of motion for color change.
   */
  String textViewMotionChangesColor(String motionToString);

  /**
   * Return text description of motion for changing width and height, aka scaling.
   * 
   * @param motionToString String format for motion as follows: "motion R 1 200
   *                       200 50 100 255 0 0 10 200 200 50 100 255 0 0"
   * @return text description of motion for scaling.
   */
  String textViewMotionScales(String motionToString);

  /**
   * This is the driver method that appends log using the above 5 helper methods.
   * 
   * @param shapeOrMotionString A line that is either a shape full string or
   *                            motion string.
   */
  void addToLogs(String shapeOrMotionString);

  /**
   * This is the final driver method that iterates over a whole data set and
   * appends each line by calling addToLogs.
   * 
   * @param entireString Entire set of strings for the document that I get from
   *                     the model.toStringFull()
   */
  void addWholeData(String entireString);

}
