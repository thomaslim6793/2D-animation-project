package cs5004.animator.model;

/**
 * Represents the elements of 2D animation.
 *
 */
public interface AnimationElement {

  /**
   * Return the string representation of the animation element. Use this
   * method as a complete getter of all field values of the object. 
   * @return String representation of the fields of the element.
   */
  String toString(); 
  
  /**
   * Return true if element is CanvasElement.
   * @return true if canvas. 
   */
  boolean isCanvas();
  
  /**
   * Return true if element is ShapeElement.
   * @return true if shape. 
   */
  boolean isShape();
  
  /**
   * Return true if element is MotionElement. 
   * @return true if motion. 
   */
  boolean isMotion();

}
