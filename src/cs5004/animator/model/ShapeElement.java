package cs5004.animator.model;

/**
 * Represents the various shapes of the animation.
 *
 */
public class ShapeElement implements AnimationElement {

  /**
   * Attributes name and type of shape.
   */
  private String elementType;
  private String name;
  private String shape;
  private MotionElement initialMotion;
  private MotionElement finalMotion;

  /**
   * Constructor that initializes the name and shape type.
   * 
   * @param name  Name of shape
   * @param shape Type of shape.
   */
  public ShapeElement(String name, String shape) {
    this.elementType = "shape";
    this.name = name; // e.g. "R1"
    this.shape = shape; // e.g. "rectangle"

    this.initialMotion = null;
    this.finalMotion = null;
  }

  /**
   * Update initial motion of a shape.
   * 
   * @param m motion
   * @return ShapeElement object.
   */
  public ShapeElement updateInitMotion(MotionElement m) {
    this.initialMotion = m;
    return this;
  }

  /**
   * Update final motion of a shape.
   * 
   * @param m motion
   * @return ShapeElement object.
   */
  public ShapeElement updateFinalMotion(MotionElement m) {
    this.finalMotion = m;
    return this;
  }

  /**
   * Getter for name field.
   * 
   * @return this.name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getter for shape field.
   * 
   * @return this.shape
   */
  public String getShape() {
    return this.shape;
  }

  /**
   * Getter for initialMotion field.
   * 
   * @return this.initialMotion
   */
  public MotionElement getInitialMotion() {
    return this.initialMotion;
  }

  /**
   * Getter for finalMotion field.
   * 
   * @return this.finalMotion
   */
  public MotionElement getFinalMotion() {
    return this.finalMotion;
  }


  @Override
  public String toString() {
    return String.format("%s %s %s", this.elementType, this.name, this.shape);
  }

  /**
   * Full string format of ShapeElement. It contains everything that the view
   * needs to create animation.
   * 
   * @return Full string format.
   */
  public String toStringFull() {
    return String.format("%s %s %s %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d",
        this.elementType, this.name, this.shape, this.initialMotion.getT1(),
        this.initialMotion.getX1(), this.initialMotion.getY1(), this.initialMotion.getW1(),
        this.initialMotion.getH1(), this.initialMotion.getR1(), this.initialMotion.getG1(),
        this.initialMotion.getB1(), this.finalMotion.getT2(), this.finalMotion.getX2(),
        this.finalMotion.getY2(), this.finalMotion.getW2(), this.finalMotion.getH2(),
        this.finalMotion.getR2(), this.finalMotion.getG2(), this.finalMotion.getB2());
  }

  @Override
  public boolean isCanvas() {
    return false;
  }

  @Override
  public boolean isShape() {
    return true;
  }

  @Override
  public boolean isMotion() {
    return false;
  }

}
