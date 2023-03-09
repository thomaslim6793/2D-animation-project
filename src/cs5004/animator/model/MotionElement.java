package cs5004.animator.model;

/**
 * Represents the motion of shapes in the animation.
 *
 */
public class MotionElement implements AnimationElement {

  /**
   * Fields of a motion. There is time, x, y, width, height, red, green blue for
   * the starting time of motion, and the same set of fields for ending time of
   * motion.
   */
  private String elementType;
  private String name;
  private int[] motionAttributes;

  private String shape;

  /**
   * Constructor for MotionElement. Initializes a total of 3 + 8 + 8 = 19 fields.
   * 
   * @param name name
   * @param t1   t1
   * @param x1   x1
   * @param y1   y1
   * @param w1   w1
   * @param h1   h1
   * @param r1   r1
   * @param g1   g1
   * @param b1   b1
   * @param t2   t2
   * @param x2   x2
   * @param y2   y2
   * @param w2   w2
   * @param h2   h2
   * @param r2   r2
   * @param g2   g2
   * @param b2   b2
   */
  public MotionElement(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
      int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {

    this.elementType = "motion";
    this.name = name;
    this.shape = "";

    this.motionAttributes = new int[16];
    this.motionAttributes[0] = t1;
    this.motionAttributes[1] = x1;
    this.motionAttributes[2] = y1;
    this.motionAttributes[3] = w1;
    this.motionAttributes[4] = h1;
    this.motionAttributes[5] = r1;
    this.motionAttributes[6] = g1;
    this.motionAttributes[7] = b1;

    this.motionAttributes[8] = t2;
    this.motionAttributes[9] = x2;
    this.motionAttributes[10] = y2;
    this.motionAttributes[11] = w2;
    this.motionAttributes[12] = h2;
    this.motionAttributes[13] = r2;
    this.motionAttributes[14] = g2;
    this.motionAttributes[15] = b2;

  }

  /**
   * Update shape field of this motion.
   * 
   * @param shape shape of this motion.
   */
  public void updateShape(String shape) {
    this.shape = shape;
  }

  public String getShape() {
    return this.shape;
  }

  public String getElementType() {
    return this.elementType;
  }

  public String getName() {
    return this.name;
  }

  public int[] getMotionAttributes() {
    return this.motionAttributes.clone();
  }

  public int getT1() {
    return this.motionAttributes[0];
  }

  public int getX1() {
    return this.motionAttributes[1];
  }

  public int getY1() {
    return this.motionAttributes[2];
  }

  public int getW1() {
    return this.motionAttributes[3];
  }

  public int getH1() {
    return this.motionAttributes[4];
  }

  public int getR1() {
    return this.motionAttributes[5];
  }

  public int getG1() {
    return this.motionAttributes[6];
  }

  public int getB1() {
    return this.motionAttributes[7];
  }

  public int getT2() {
    return this.motionAttributes[8];
  }

  public int getX2() {
    return this.motionAttributes[9];
  }

  public int getY2() {
    return this.motionAttributes[10];
  }

  public int getW2() {
    return this.motionAttributes[11];
  }

  public int getH2() {
    return this.motionAttributes[12];
  }

  public int getR2() {
    return this.motionAttributes[13];
  }

  public int getG2() {
    return this.motionAttributes[14];
  }

  public int getB2() {
    return this.motionAttributes[15];
  }

  @Override
  public String toString() {
    return String.format("%s %s %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d", this.elementType,
        this.name, this.motionAttributes[0], this.motionAttributes[1], this.motionAttributes[2],
        this.motionAttributes[3], this.motionAttributes[4], this.motionAttributes[5],
        this.motionAttributes[6], this.motionAttributes[7], this.motionAttributes[8],
        this.motionAttributes[9], this.motionAttributes[10], this.motionAttributes[11],
        this.motionAttributes[12], this.motionAttributes[13], this.motionAttributes[14],
        this.motionAttributes[15]);
  }

  /**
   * Another string returning method that includes the element type as the first word of the string.
   * @return The string data. 
   */
  public String toStringFull() {
    return String.format("%s %s %s %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d",
        this.elementType, this.name, this.shape, this.motionAttributes[0], this.motionAttributes[1],
        this.motionAttributes[2], this.motionAttributes[3], this.motionAttributes[4],
        this.motionAttributes[5], this.motionAttributes[6], this.motionAttributes[7],
        this.motionAttributes[8], this.motionAttributes[9], this.motionAttributes[10],
        this.motionAttributes[11], this.motionAttributes[12], this.motionAttributes[13],
        this.motionAttributes[14], this.motionAttributes[15]);
  }

  @Override
  public boolean isCanvas() {
    return false;
  }

  @Override
  public boolean isShape() {
    return false;
  }

  @Override
  public boolean isMotion() {
    return true;
  }

}
