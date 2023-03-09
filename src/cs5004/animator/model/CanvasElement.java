package cs5004.animator.model;

/**
 * Represents the canvas of the animation.
 *
 */
public class CanvasElement implements AnimationElement {

  /**
   * This is really confusing, but x denotes the left-most x value of the canvas,
   * and y denotes the top-most y value of the canvas. So to go up width means x
   * increases, but to go up in height means that y would decrease (super
   * counter-intuitive, but this is the standard this project expects).
   */
  private String elementType;
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Constructor that initializes the fields of the canvas.
   * 
   * @param x      x value.
   * @param y      y value.
   * @param width  width value.
   * @param height height value.
   */
  public CanvasElement(int x, int y, int width, int height) {
    this.elementType = "canvas";
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public String toString() {
    return String.format("%s %d %d %d %d", this.elementType, this.x, this.y, this.width,
        this.height);
  }

  @Override
  public boolean isCanvas() {
    return true;
  }

  @Override
  public boolean isShape() {
    return false;
  }

  @Override
  public boolean isMotion() {
    return false;
  }

}
