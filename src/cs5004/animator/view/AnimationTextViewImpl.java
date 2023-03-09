package cs5004.animator.view;

/**
 * Implement textual view of the animation.
 *
 */
public class AnimationTextViewImpl implements AnimationTextView {

  /**
   * Attributes of our animation are stored here, formatted properly.
   */
  private String shapeCreationLog;
  private String shapeLifeTimeLog;
  private String motionLog;
  private String ticksPerSpeed;

  /**
   * Constructor method, initializing our attributes.
   * 
   * @param ticksPerSpeed Speed of animation.
   */
  public AnimationTextViewImpl(String ticksPerSpeed) {
    this.shapeCreationLog = "";
    this.shapeLifeTimeLog = "";
    this.motionLog = "";
    this.ticksPerSpeed = ticksPerSpeed;

  }

  @Override
  public String toString() {
    return this.shapeCreationLog + "\n" + this.shapeLifeTimeLog + "\n" + this.motionLog;
  }

  // This method is the driver.
  @Override
  public void addWholeData(String entireString) {
    String[] splittedByNewLine = entireString.split("\n");
    int numLines = splittedByNewLine.length;
    for (int i = 0; i < numLines; i++) {
      addToLogs(splittedByNewLine[i]);
    }
  }

  @Override
  public void addToLogs(String shapeOrMotionString) {
    String[] splitted = shapeOrMotionString.split("\\s+");
    if (splitted[0].equals("shape")) {
      this.shapeCreationLog += this.textViewShapeCreation(shapeOrMotionString) + "\n";
      this.shapeLifeTimeLog += this.textViewShapeLifeTime(shapeOrMotionString) + "\n";
    }

    else if (splitted[0].equals("motion")) {
      if (!(splitted[4].equals(splitted[12]) && splitted[5].equals(splitted[13]))) {
        this.motionLog += this.textViewMotionMoves(shapeOrMotionString) + "\n";
      }
      if (!(splitted[6].equals(splitted[14]) && splitted[7].equals(splitted[15]))) {
        this.motionLog += this.textViewMotionScales(shapeOrMotionString) + "\n";
      }
      if (!(splitted[8].equals(splitted[16]) && splitted[9].equals(splitted[17])
          && splitted[10].equals(splitted[18]))) {
        this.motionLog += this.textViewMotionChangesColor(shapeOrMotionString) + "\n";
      }
    }
  }

  @Override
  public String textViewShapeCreation(String shapeToStringFull) {
    String[] splitted = shapeToStringFull.split("\\s+");
    String name = splitted[1];
    String shape = splitted[2];
    if (shape.toLowerCase().equals("circle") || shape.toLowerCase().equals("oval")
        || shape.toLowerCase().equals("ellipse")) {
      String s = String.format(
          "Create %s %s with center (x,y) at (%d, %d), (radius x, radius y) of"
              + "(%d, %d), and color (r, g, b) of (%d, %d, %d)",
          shape, name, Integer.parseInt(splitted[4]) + Integer.parseInt(splitted[6]) / 2,
          Integer.parseInt(splitted[5]) + Integer.parseInt(splitted[7]) / 2,
          Integer.parseInt(splitted[6]) / 2, Integer.parseInt(splitted[7]) / 2,
          Integer.parseInt(splitted[8]), Integer.parseInt(splitted[9]),
          Integer.parseInt(splitted[10]));
      return s;
    } else {
      String s = String.format(
          "Create %s %s with corner (x,y) at (%d, %d), (width, height) of"
              + " (%d, %d), and color (r, g, b) of (%d, %d, %d)",
          shape, name, Integer.parseInt(splitted[4]), Integer.parseInt(splitted[5]),
          Integer.parseInt(splitted[6]), Integer.parseInt(splitted[7]),
          Integer.parseInt(splitted[8]), Integer.parseInt(splitted[9]),
          Integer.parseInt(splitted[10]));
      return s;
    }
  }

  @Override
  public String textViewShapeLifeTime(String shapeToStringFull) {
    String[] splitted = shapeToStringFull.split("\\s+");
    String name = splitted[1];
    String s = String.format("%s appears at time t=%d and disappears at time t=%d", name,
        Integer.parseInt(splitted[3]) / Integer.parseInt(this.ticksPerSpeed),
        Integer.parseInt(splitted[11]) / Integer.parseInt(this.ticksPerSpeed));
    return s;
  }

  @Override
  public String textViewMotionMoves(String motionToStringFull) {
    String[] splitted = motionToStringFull.split("\\s+");

    if (splitted[2].toLowerCase().equals("circle") || splitted[2].toLowerCase().equals("oval")
        || splitted[2].toLowerCase().equals("ellipse")) {
      String s = String.format(
          "%s %s moves center (x, y) from (%d, %d) to (%d, %d) from time t=%d to t=%d", splitted[2],
          splitted[1], Integer.parseInt(splitted[4]) + Integer.parseInt(splitted[6]) / 2,
          Integer.parseInt(splitted[5]) + Integer.parseInt(splitted[7]) / 2,
          Integer.parseInt(splitted[12]) + Integer.parseInt(splitted[14]) / 2,
          Integer.parseInt(splitted[13]) + Integer.parseInt(splitted[15]) / 2,
          Integer.parseInt(splitted[3]) / Integer.parseInt(this.ticksPerSpeed),
          Integer.parseInt(splitted[11]) / Integer.parseInt(this.ticksPerSpeed));
      return s;
    }

    else {
      String s = String.format(
          "%s %s moves corner (x, y) from (%d, %d) to (%d, %d) from time t=%d to t=%d", splitted[2],
          splitted[1], Integer.parseInt(splitted[4]), Integer.parseInt(splitted[5]),
          Integer.parseInt(splitted[12]), Integer.parseInt(splitted[13]),
          Integer.parseInt(splitted[3]) / Integer.parseInt(this.ticksPerSpeed),
          Integer.parseInt(splitted[11]) / Integer.parseInt(this.ticksPerSpeed));
      return s;
    }

  }

  @Override
  public String textViewMotionChangesColor(String motionToStringFull) {
    String[] splitted = motionToStringFull.split("\\s+");
    String s = String.format(
        "%s %s changes color from (%d, %d, %d) to (%d, %d, %d) from time t=%d to t=%d", splitted[2],
        splitted[1], Integer.parseInt(splitted[8]), Integer.parseInt(splitted[9]),
        Integer.parseInt(splitted[10]), Integer.parseInt(splitted[16]),
        Integer.parseInt(splitted[17]), Integer.parseInt(splitted[18]),
        Integer.parseInt(splitted[3]) / Integer.parseInt(this.ticksPerSpeed),
        Integer.parseInt(splitted[11]) / Integer.parseInt(this.ticksPerSpeed));
    return s;
  }

  @Override
  public String textViewMotionScales(String motionToStringFull) {
    String[] splitted = motionToStringFull.split("\\s+");
    String s = String.format(
        "%s %s scales (width, height) from (%d, %d) to (%d, %d) from time t=%d to t=%d",
        splitted[2], splitted[1], Integer.parseInt(splitted[6]), Integer.parseInt(splitted[7]),
        Integer.parseInt(splitted[14]), Integer.parseInt(splitted[15]),
        Integer.parseInt(splitted[3]) / Integer.parseInt(this.ticksPerSpeed),
        Integer.parseInt(splitted[11]) / Integer.parseInt(this.ticksPerSpeed));
    return s;
  }
}
