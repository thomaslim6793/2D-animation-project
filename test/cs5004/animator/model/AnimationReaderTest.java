package cs5004.animator.model;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

/**
 * Test for animation reader.
 */
public class AnimationReaderTest {

  @Test
  public void testParseFile() {
    AnimationBuilder<AnimationModel> builder = new AnimationBuilderImpl();
    FileReader f;
    try {
      f = new FileReader("code/smalldemo.txt");
      AnimationModelImpl m = (AnimationModelImpl) AnimationReader.parseFile(f, builder);
      assertEquals("canvas 200 70 360 360\n" + "shape R rectangle\n"
          + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
          + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
          + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
          + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
          + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n" + "shape C ellipse\n"
          + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
          + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
          + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
          + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
          + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0", m.toString());

    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
