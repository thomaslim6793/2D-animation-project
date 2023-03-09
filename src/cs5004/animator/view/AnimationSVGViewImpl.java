package cs5004.animator.view;

import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.MotionElement;

/**
 * SVG view class. Using our model and speed parameter from command line, it
 * builds a String data of SVG format.
 */
public class AnimationSVGViewImpl implements AnimationSVGView {

  /**
   * Fields. m is the model, and ticksPerSecond is the unit of speed of animation.
   */
  AnimationModelImpl m;
  int ticksPerSecond;

  /**
   * Constructor that initializes the fields.
   * 
   * @param m              Our model which contains the data.
   * @param ticksPerSecond Speed of animation.
   * @throws IllegalArgumentException If ticksPerSecond is 0, to prevent division
   *                                  by 0.
   */
  public AnimationSVGViewImpl(AnimationModelImpl m, int ticksPerSecond)
      throws IllegalArgumentException {
    if (ticksPerSecond <= 0) {
      throw new IllegalArgumentException("ticksPerSecond must be a positive number");
    }
    this.m = m;
    this.ticksPerSecond = ticksPerSecond;
  }

  @Override
  public String getFinalSVG(boolean loopBack) {
    String s = "";

    // Add canvas SVG.
    s += this.getCanvasSVG() + "\n\n";

    // Add loop back if loopBack ==true;
    if (loopBack) {
      int animationDuration = 0;
      for (int i = 0; i < m.getShapeList().size(); i++) {
        if (m.getShapeList().get(i).getFinalMotion().getT2() > animationDuration) {
          animationDuration = m.getShapeList().get(i).getFinalMotion().getT2();
        }
      }
      s += "<rect>\n";
      s += String.format(
          "<animate id=\"base\" begin=\"0;base.end\" dur=\"%dms\" attributeName=\"visib"
              + "ility\" from=\"hide\" to=\"hide\"/>",
          (animationDuration + 1) * 1000 / this.ticksPerSecond) + "\n";
      s += "</rect>\n\n";
    }

    // For each shape, append shape SVG and all of its motionSVG.
    for (int shapeIndex = 0; shapeIndex < m.getShapeList().size(); shapeIndex++) {
      String name = this.m.getShapeList().get(shapeIndex).getName();
      s += this.getShapeSVG(shapeIndex, loopBack) + "\n";
      for (int motionIndex = 0; motionIndex < m.getMotionList().size(); motionIndex++) {
        if (m.getMotionList().get(motionIndex).getName().equals(name)) {
          s += this.getMotionSVG(motionIndex, loopBack) + "\n";
        }
      }

      if (this.m.getShapeList().get(shapeIndex).getShape().equals("rectangle")) {
        // If loopBack == true; reset to initial motion at the end for each shape.
        if (loopBack) {
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attribut"
                  + "eName=\"x\" to=\"%d\" fill=\"freeze\" />",
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getX1()) + "\n";
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attribute"
                  + "Name=\"y\" to=\"%d\" fill=\"freeze\" />",
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getY1()) + "\n";
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attribut"
                  + "eName=\"width\" to=\"%d\" fill=\"freeze\" />",
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getW1()) + "\n";
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attribute"
                  + "Name=\"height\" to=\"%d\" fill=\"freeze\" />",
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getH1()) + "\n";
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attribut"
                  + "eName=\"fill\" to=\"rgb(%d,%d,%d)\" fill=\"freeze\" />",
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getR1(),
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getG1(),
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getB1()) + "\n";
          s += "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attribut"
              + "eName=\"visibility\" to=\"hidden\" fill=\"freeze\" />" + "\n";
        }
        s += "</rect>\n\n"; // Close shape SVG
      } else if (this.m.getShapeList().get(shapeIndex).getShape().equals("ellipse")
          || this.m.getShapeList().get(shapeIndex).getShape().equals("oval")) {
        // If loopBack == true; reset to initial motion at the end for each shape.
        if (loopBack) {
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attri"
                  + "buteName=\"cx\" to=\"%d\" fill=\"freeze\" />",
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getX1()
                  + this.m.getShapeList().get(shapeIndex).getInitialMotion().getW1() / 2)
              + "\n";
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attri"
                  + "buteName=\"cy\" to=\"%d\" fill=\"freeze\" />",
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getY1()
                  + this.m.getShapeList().get(shapeIndex).getInitialMotion().getH1() / 2)
              + "\n";
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attri"
                  + "buteName=\"rx\" to=\"%d\" fill=\"freeze\" />",
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getW1() / 2) + "\n";
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attrib"
                  + "uteName=\"ry\" to=\"%d\" fill=\"freeze\" />",
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getH1() / 2) + "\n";
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attrib"
                  + "uteName=\"fill\" to=\"rgb(%d,%d,%d)\" fill=\"freeze\" />",
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getR1(),
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getG1(),
              this.m.getShapeList().get(shapeIndex).getInitialMotion().getB1()) + "\n";
          s += "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attribu"
              + "teName=\"visibility\" to=\"hidden\" fill=\"freeze\" />" + "\n";
        }
        s += "</ellipse>\n\n"; // Close shape SVG
      }
    }
    s += "\n</svg>";
    // Close canvas SVG

    return s;
  }

  @Override
  public String getCanvasSVG() {
    String[] canvasString = this.m.getCanvas().toString().split(" ");

    int biggestX = m.getMotionList().get(0).getX1();
    int biggestY = m.getMotionList().get(0).getY1();
    for (int index = 0; index < m.getMotionList().size(); index++) {
      if (m.getMotionList().get(index).getX2() > biggestX) {
        biggestX = m.getMotionList().get(index).getX2();
      }
      if (m.getMotionList().get(index).getY2() > biggestY) {
        biggestY = m.getMotionList().get(index).getY2();
      }
    }

    String s = String.format(
        "<svg width=\"%d\" height= \"%d\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" >",
        Integer.parseInt(canvasString[3]) + biggestX, Integer.parseInt(canvasString[4]) + biggestY);
    return s;
  }

  @Override
  public String getShapeSVG(int index, boolean loopBack) {
    String name = this.m.getShapeList().get(index).getInitialMotion().getName();
    String shape = "rect";
    if (m.getShapeList().get(index).getInitialMotion().getShape().equals("rectangle")) {
      shape = "rect";
    } else if (m.getShapeList().get(index).getInitialMotion().getShape().equals("ellipse")
        || m.getShapeList().get(index).getInitialMotion().getShape().equals("oval")) {
      shape = "ellipse";
    }
    int t1 = m.getShapeList().get(index).getInitialMotion().getT1();
    int x1 = m.getShapeList().get(index).getInitialMotion().getX1();
    int y1 = m.getShapeList().get(index).getInitialMotion().getY1();
    int w1 = m.getShapeList().get(index).getInitialMotion().getW1();
    int h1 = m.getShapeList().get(index).getInitialMotion().getH1();
    int r1 = m.getShapeList().get(index).getInitialMotion().getR1();
    int g1 = m.getShapeList().get(index).getInitialMotion().getG1();
    int b1 = m.getShapeList().get(index).getInitialMotion().getB1();

    if (shape.equals("rect")) {
      if (loopBack) {
        String s1 = String.format(
            "<%s id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fil"
                + "l=\"rgb(%d,%d,%d)\" visibility=\"hidden\" >",
            shape, name, x1, y1, w1, h1, r1, g1, b1);
        String s2 = String.format(
            "<animate attributeType=\"xml\" begin=\"base.begin+%dms\" d"
                + "ur=\"1.0ms\" attributeName=\"visibility\" "
                + "from=\"hidden\" to=\"visible\" fill=\"freeze\" />",
            t1 * 1000 / this.ticksPerSecond);
        return s1 + "\n" + s2;
      } else {
        String s1 = String
            .format(
                "<%s id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill=\"rg"
                    + "b(%d,%d,%d)\" visibility=\"hidden\" >",
                shape, name, x1, y1, w1, h1, r1, g1, b1);
        String s2 = String.format(
            "<animate attributeType=\"xml\" begin=\"%dms\" dur=\"1.0ms\" attribute"
            + "Name=\"visibility\" "
                + "from=\"hidden\" to=\"visible\" fill=\"freeze\" />",
            t1 * 1000 / this.ticksPerSecond);
        return s1 + "\n" + s2;
      }
    } else if (shape.equals("ellipse")) {
      if (loopBack) {
        String s1 = String.format(
            "<%s id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" fill=\"r"
                + "gb(%d,%d,%d)\" visibility=\"hidden\" >",
            shape, name, x1 + w1 / 2, y1 + h1 / 2, w1 / 2, h1 / 2, r1, g1, b1);
        String s2 = String.format(
            "<animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"1.0ms\" att"
                + "ributeName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />",
            t1 * 1000 / this.ticksPerSecond);

        return s1 + "\n" + s2;
      } else {
        String s1 = String.format(
            "<%s id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" fill=\"rgb(%d,%d,%d)\" vis"
                + "ibility=\"hidden\" >",
            shape, name, x1 + w1 / 2, y1 + h1 / 2, w1 / 2, h1 / 2, r1, g1, b1);
        String s2 = String.format(
            "<animate attributeType=\"xml\" begin=\"%dms\" dur=\"1.0ms\" attributeNa"
                + "me=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />",
            t1 * 1000 / this.ticksPerSecond);

        return s1 + "\n" + s2;
      }
    } else {
      if (loopBack) {
        String s1 = String.format(
            "<%s id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fil"
                + "l=\"rgb(%d,%d,%d)\" visibility=\"hide\" >",
            shape, name, x1, y1, w1, h1, r1, g1, b1);
        String s2 = String.format(
            "<animate attributeType=\"xml\" begin=\"base.begin+%dms\" du"
                + "r=\"1.0ms\" attributeName=\"visibility\" "
                + "from=\"hide\" to=\"visible\" fill=\"freeze\" />",
            t1 * 1000 / this.ticksPerSecond);
        return s1 + "\n" + s2;
      } else {
        String s1 = String.format(
            "<%s id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fil"
                + "l=\"rgb(%d,%d,%d)\" visibility=\"hide\" >",
            shape, name, x1, y1, w1, h1, r1, g1, b1);
        String s2 = String.format("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"1.0ms\" a"
            + "ttributeName=\"visibility\" " + "from=\"hide\" to=\"visible\" fill=\"freeze\" />",
            t1 * 1000 / this.ticksPerSecond);
        return s1 + "\n" + s2;
      }
    }

  }

  @Override
  public String getMotionSVG(int index, boolean loopBack) {
    MotionElement motion = this.m.getMotionList().get(index);
    int t1; 
    int x1; 
    int y1;
    int w1;
    int h1; 
    int r1;
    int g1; 
    int b1;
    int t2;
    int x2;
    int y2;
    int w2;
    int h2;
    int r2;
    int g2;
    int b2;
    t1 = motion.getT1();
    x1 = motion.getX1();
    y1 = motion.getY1();
    w1 = motion.getW1();
    h1 = motion.getH1();
    r1 = motion.getR1();
    g1 = motion.getG1();
    b1 = motion.getB1();

    t2 = motion.getT2();
    x2 = motion.getX2();
    y2 = motion.getY2();
    w2 = motion.getW2();
    h2 = motion.getH2();
    r2 = motion.getR2();
    g2 = motion.getG2();
    b2 = motion.getB2();

    if (motion.getShape().equals("rectangle")) {
      String s = "";
      if (x1 != x2) {
        s += svgAttributeMotionHelper("x", x1, x2, t1, t2, loopBack) + "\n";
      }
      if (y1 != y2) {
        s += svgAttributeMotionHelper("y", y1, y2, t1, t2, loopBack) + "\n";
      }
      if (w1 != w2) {
        s += svgAttributeMotionHelper("width", w1, w2, t1, t2, loopBack) + "\n";
      }
      if (h1 != h2) {
        s += svgAttributeMotionHelper("height", h1, h2, t1, t2, loopBack) + "\n";
      }
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        if (loopBack) {
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" "
                  + "attributeName=\"fill\" from=\"rgb(%d,%d,%d)\" to=\"r"
                  + "gb(%d,%d,%d)\" fill=\"freeze\" />",
              t1 * 1000 / this.ticksPerSecond, (t2 - t1) * 1000 / this.ticksPerSecond, r1, g1, b1,
              r2, g2, b2) + "\n";
        } else {
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                  + "attributeName=\"fill\" from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" fi"
                  + "ll=\"freeze\" />",
              t1 * 1000 / this.ticksPerSecond, (t2 - t1) * 1000 / this.ticksPerSecond, r1, g1, b1,
              r2, g2, b2) + "\n";
        }
      }
      return s;
    }

    else {
      String s = "";
      if (x1 != x2) {
        s += svgAttributeMotionHelper2("cx", x1 + w1 / 2, x2 + w2 / 2, t1, t2, loopBack) + "\n";
      }
      if (y1 != y2) {
        s += svgAttributeMotionHelper2("cy", y1 + h1 / 2, y2 + h2 / 2, t1, t2, loopBack) + "\n";
      }
      if (w1 != w2) {
        s += svgAttributeMotionHelper2("rx", w1 / 2, w2 / 2, t1, t2, loopBack) + "\n";
      }
      if (h1 != h2) {
        s += svgAttributeMotionHelper2("ry", h1 / 2, h2 / 2, t1, t2, loopBack) + "\n";
      }
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        if (loopBack) {
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" "
                  + "attributeName=\"fill\" from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" f"
                  + "ill=\"freeze\" />",
              t1 * 1000 / this.ticksPerSecond, (t2 - t1) * 1000 / this.ticksPerSecond, r1, g1, b1,
              r2, g2, b2) + "\n";
        } else {
          s += String.format(
              "<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
                  + "attributeName=\"fill\" from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\" fi"
                  + "ll=\"freeze\" />",
              t1 * 1000 / this.ticksPerSecond, (t2 - t1) * 1000 / this.ticksPerSecond, r1, g1, b1,
              r2, g2, b2) + "\n";
        }
      }
      return s;
    }
  }

  /**
   * Helper method.
   * 
   * @param attribute Attribute being changes
   * @param value1    Initial value of attribute.
   * @param value2    Final value of attribute.
   * @param t1        Initial time.
   * @param t2        Final time.
   * @return String in SVG format.
   */
  private String svgAttributeMotionHelper(String attribute, int value1, int value2, int t1, int t2,
      boolean loopBack) {
    if (loopBack) {
      return String.format(
          "<animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" "
              + "attributeName=\"%s\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
          t1 * 1000 / this.ticksPerSecond, (t2 - t1) * 1000 / this.ticksPerSecond, attribute,
          value1, value2);
    } else {
      return String.format(
          "<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
              + "attributeName=\"%s\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
          t1 * 1000 / this.ticksPerSecond, (t2 - t1) * 1000 / this.ticksPerSecond, attribute,
          value1, value2);
    }
  }

  /**
   * Helper method.
   * 
   * @param attribute Attribute being changes
   * @param value1    Initial value of attribute.
   * @param value2    Final value of attribute.
   * @param t1        Initial time.
   * @param t2        Final time.
   * @return String in SVG format.
   */
  private String svgAttributeMotionHelper2(String attribute, int value1, int value2, int t1, int t2,
      boolean loopBack) {
    if (loopBack) {
      return String.format(
          "<animate attributeType=\"xml\" begin=\"base.begin+%dms\" dur=\"%dms\" "
              + "attributeName=\"%s\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
          t1 * 1000 / this.ticksPerSecond, (t2 - t1) * 1000 / this.ticksPerSecond, attribute,
          value1, value2);
    } else {
      return String.format(
          "<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" "
              + "attributeName=\"%s\" from=\"%d\" to=\"%d\" fill=\"freeze\" />",
          t1 * 1000 / this.ticksPerSecond, (t2 - t1) * 1000 / this.ticksPerSecond, attribute,
          value1, value2);
    }
  }

}
