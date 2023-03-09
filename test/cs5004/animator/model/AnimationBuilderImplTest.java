package cs5004.animator.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test AnimationBuilder class.
 *
 */
public class AnimationBuilderImplTest {

  @Test
  public void testBuild() {
    AnimationBuilderImpl builder = new AnimationBuilderImpl();
    assertEquals("", builder.build().toString());
  }

  @Test
  public void testSetBounds() {
    AnimationBuilderImpl builder = new AnimationBuilderImpl();
    assertEquals("canvas 1 2 3 4", builder.setBounds(1, 2, 3, 4).build().toString());
  }

  @Test
  public void testDeclareShape() {
    AnimationBuilderImpl builder = new AnimationBuilderImpl();
    assertEquals("shape myRect1 rectangle",
        builder.declareShape("myRect1", "rectangle").build().toString());
  }

  @Test
  public void testAddMotion() {
    AnimationBuilderImpl builder = new AnimationBuilderImpl();
    builder.declareShape("myRect1", "rectangle").build();
    assertEquals("shape myRect1 rectangle\n" + "motion myRect1 1 2 3 4 5 6 7 8 1 2 3 4 5 6 7 8",
        builder.addMotion("myRect1", 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8).build()
            .toString());
  }
}
