package cs5004.animator.view;

/**
 * Interface for our SVG view. 
 *
 */
public interface AnimationSVGView {

  /**
   * Final SVG of the animation.
   * 
   * @param loopBack Descriptor if there is loopBack or not.
   * @return SVG with loopBack if loopBack is true.
   */
  String getFinalSVG(boolean loopBack);

  /**
   * SVG string for the canvas. It is made such that the dimensions = (canvas
   * width + maximum x between all motions, canvas height + maximum y between all
   * motions).
   * 
   * @return SVG string for canvas.
   */
  String getCanvasSVG();

  /**
   * SVG string for a motion. 
   * @param index Index of motion from the model. 
   * @return SVG string for a motion. 
   */
  String getMotionSVG(int index, boolean loopBack);

  /**
   * SVG for a shape. This is a composite method that calls getMotionSVG, such that for 
   * each shape, each SVG for all of its motions.
   * are appended in order of time. 
   * @param index Index of shape from the model. 
   * @return SVG string for a shape. 
   */
  String getShapeSVG(int index, boolean loopBack);

}
