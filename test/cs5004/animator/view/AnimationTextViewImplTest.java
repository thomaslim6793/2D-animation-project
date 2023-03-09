package cs5004.animator.view;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import cs5004.animator.model.AnimationBuilder;
import cs5004.animator.model.AnimationBuilderImpl;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.AnimationReader;

/**
 * Test class for testing text view class. 
 *
 */
public class AnimationTextViewImplTest {
  

  @Test
  public void testTextViewManuallyAddingExample() {

    AnimationTextView v = new AnimationTextViewImpl("1");

    AnimationModel m = new AnimationModelImpl();
    m.addElement("canvas 200 70 360 360");
    m.addElement("shape R rectangle");
    m.addElement("motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0");
    m.addElement("motion R 10 200 200 50 100 255 0  0    50  300 300 50 100 255 0  0");
    m.addElement("motion R 50 300 300 50 100 255 0  0    51  300 300 50 100 255 0  0");
    m.addElement("motion R 51 300 300 50 100 255 0  0    70  300 300 25 100 255 0  0");
    m.addElement("motion R 70 300 300 25 100 255 0  0    100 200 200 25 100 255 0  0");
    m.addElement("shape C ellipse");
    m.addElement("motion C 6  440 70 120 60 0 0 255 20 440 70 120 60 0 0 255 ");
    m.addElement("motion C 20 440 70 120 60 0 0 255      50 440 250 120 60 0 0 255");
    m.addElement("motion C 50 440 250 120 60 0 0 255     70 440 370 120 60 0 170 85");
    m.addElement("motion C 70 440 370 120 60 0 170 85    80 440 370 120 60 0 255 0");
    m.addElement("motion C 80 440 370 120 60 0 255 0     100 440 370 120 60 0 255 0");

    String refinedData = m.toStringFull();
    v.addWholeData(refinedData);
    assertEquals("Create rectangle R with corner (x,y) at (200, 200), (width, heigh"
        + "t) of (50, 100), and color (r, g, b) of (255, 0, 0)\n"
        + "Create ellipse C with center (x,y) at (500, 100), (radius x, radius y) o"
        + "f(60, 30), and color (r, g, b) of (0, 0, 255)\n" + "\n"
        + "R appears at time t=1 and disappears at time t=100\n"
        + "C appears at time t=6 and disappears at time t=100\n" + "\n"
        + "rectangle R moves corner (x, y) from (200, 200) to (300, 300) from time t=10 to t=50\n"
        + "ellipse C moves center (x, y) from (500, 100) to (500, 280) from time t=20 to t=50\n"
        + "ellipse C moves center (x, y) from (500, 280) to (500, 400) from time t=50 to t=70\n"
        + "ellipse C changes color from (0, 0, 255) to (0, 170, 85) from time t=50 to t=70\n"
        + "rectangle R scales (width, height) from (50, 100) to (25, 100) from time t=51 to t=70\n"
        + "rectangle R moves corner (x, y) from (300, 300) to (200, 200) from time t=70 to t=100\n"
        + "ellipse C changes color from (0, 170, 85) to (0, 255, 0) from time t=70 to t=80\n" + "",
        v.toString());
  }

  @Test
  public void testTextViewReadFromFile() {

    AnimationTextView v = new AnimationTextViewImpl("1");

    AnimationBuilder<AnimationModel> builder = new AnimationBuilderImpl();
    FileReader f;
    try {
      f = new FileReader("code/smalldemo.txt");
      AnimationModelImpl m = (AnimationModelImpl) AnimationReader.parseFile(f, builder);
      String data = m.toStringFull();
      v.addWholeData(data);
      assertEquals("Create rectangle R with corner (x,y) at (200, 200), (width, heigh"
          + "t) of (50, 100), and color (r, g, b) of (255, 0, 0)\n"
          + "Create ellipse C with center (x,y) at (500, 100), (radius x, radiu"
          + "s y) of(60, 30), and color (r, g, b) of (0, 0, 255)\n" + "\n"
          + "R appears at time t=1 and disappears at time t=100\n"
          + "C appears at time t=6 and disappears at time t=100\n" + "\n"
          + "rectangle R moves corner (x, y) from (200, 200) to (300, 300) from ti"
          + "me t=10 to t=50\n"
          + "ellipse C moves center (x, y) from (500, 100) to (500, 280) from ti"
          + "me t=20 to t=50\n"
          + "ellipse C moves center (x, y) from (500, 280) to (500, 400) from tim"
          + "e t=50 to t=70\n" + "ellipse C changes color from (0, 0, 255) t"
          + "o (0, 170, 85) from tim" + "e t=50 to t=70\n"
          + "rectangle R scales (width, height) from (50, 100) to (25, 100) from tim"
          + "e t=51 to t=70\n"
          + "rectangle R moves corner (x, y) from (300, 300) to (200, 200) from tim"
          + "e t=70 to t=100\n" + "ellipse C changes color from (0, 170, 85) to (0, 255, 0) fro"
          + "m tim" + "e t=70 to t=80\n" + "", v.toString());
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
