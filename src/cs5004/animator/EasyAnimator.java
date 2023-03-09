package cs5004.animator;

import javax.swing.JOptionPane;

import cs5004.animator.controller.AnimationControllerImpl;

/**
 * This is the driver class of my program.
 */
public final class EasyAnimator {

  /**
   * The main method.
   * 
   * @param args Command line arguments.
   * @throws IllegalArgumentException If invalid arguments.
   */
  public static void main(String[] args) throws IllegalArgumentException {

    /*
     * Fields for the command line inputs.
     */
    String inArg = "";
    String viewArg = "";
    String outArg = "System.out"; // default out
    String speedArg = "1"; // default value

    // Invalid if even number argument index are not -in, -out, -view, - speed.
    for (int i = 0; i < args.length; i++) {
      if (i % 2 == 0 && args[i].charAt(0) != '-') {
        throw new IllegalArgumentException(
            "-in, -out, -view, -speed specifiers before actual content.");
      }
    }

    // Invalid if at least -in and -view pairs are not present.
    boolean inArgPresent = false;
    boolean viewArgPresent = false;
    for (int i = 0; i < args.length; i++) {
      if (i % 2 == 0 && args[i].equals("-in")) {
        inArgPresent = true;
      }
      if (i % 2 == 0 && args[i].equals("-view")) {
        viewArgPresent = true;
      }
    }
    if (!(inArgPresent && viewArgPresent)) {
      throw new IllegalArgumentException("Must have at least -in and -view specifiers.");
    }

    // Now that I've thrown IllegalArgumentException for any invalid case, simply
    // parse the arguments to the four String variables.
    for (int i = 0; i < args.length; i++) {
      if (i % 2 == 0 && args[i].equals("-in")) {
        inArg = args[i + 1];
      } else if (i % 2 == 0 && args[i].equals("-view")) {
        viewArg = args[i + 1];
      } else if (i % 2 == 0 && args[i].equals("-out")) {
        outArg = args[i + 1];
      } else if (i % 2 == 0 && args[i].equals("-speed")) {
        speedArg = args[i + 1];
      }
    }
    /*
     * System.out.println("In argument is: " + inArg);
     * System.out.println("View argument is: " + viewArg);
     * System.out.println("Out argument is: " + outArg);
     * System.out.println("Speed argument is: " + speedArg);
     */

    if (viewArg.equals("text")) {
      new AnimationControllerImpl(inArg, outArg, viewArg, speedArg).playTextView();
    } else if (viewArg.equals("visual")) {
      new AnimationControllerImpl(inArg, outArg, viewArg, speedArg).playVisualView();
    } else if (viewArg.equals("svg")) {
      new AnimationControllerImpl(inArg, outArg, viewArg, speedArg).playSVGView();
    } else if (viewArg.equals("playback")) {
      new AnimationControllerImpl(inArg, outArg, viewArg, speedArg).playVisualViewImproved();
    }
  }
}
