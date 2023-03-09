package cs5004.animator.controller;

/**
 * Interface for the controller class.
 *
 */
public interface AnimationController {

  /**
   * Plays text view.
   */
  void playTextView();

  /**
   * Plays visual view.
   */
  void playVisualView();

  /**
   * Plays SVG view.
   */
  void playSVGView();

  /**
   * Plays visual view that allows playback. 
   */
  void playVisualViewImproved();
}
