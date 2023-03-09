package cs5004.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

import cs5004.animator.model.MotionElement;

/**
 * JPanel object that paints our animation frame by frame. The mechanism of how
 * it works is this: It is an ActionListener, that creates and starts a timer
 * that fires at a rate of 1000ms * ticksPerSecond. Then it calls the
 * actionPerformed method whenever the timer fires its pulse, which calls
 * paintComponent method once. So if the timer fires 10 times, the
 * actionPerformed is called 10 times, which calls paintComponent once each time
 * it is called, so paintComponent will be called 1 * 10 times.
 */
public class AnimationPanel extends JPanel implements ActionListener {
  /**
   * Fields. Important ones are timer, tweeenedMotionList, ticksPerSecond which
   * are like the main fields. motionIndex and currentTick are used to modify the
   * behavior of paintComponent each time it is called so that it paints a new
   * data every time it is called.
   */
  Timer timer;

  LinkedList<MotionElement> tweenedMotionList;
  int ticksPerSecond;

  int motionIndex;
  int currentTick;
  int toggleLoopOn = 0;

  /**
   * Constructor. It builds the panel and starts the timer.
   * 
   * @param tweenedMotionList Data from our model.
   * @param canvas            Data from our model.
   * @param ticksPerSecond    Unit of our animation speed.
   */
  public AnimationPanel(LinkedList<MotionElement> tweenedMotionList, String[] canvas,
      int ticksPerSecond) {

    super(true);

    this.tweenedMotionList = tweenedMotionList;
    this.ticksPerSecond = ticksPerSecond;

    this.motionIndex = 0;
    this.currentTick = this.tweenedMotionList.get(motionIndex).getT1();

    int biggestX = tweenedMotionList.get(0).getX1();
    int biggestY = tweenedMotionList.get(0).getY1();
    for (int index = 0; index < tweenedMotionList.size(); index++) {
      if (tweenedMotionList.get(index).getX2() > biggestX) {
        biggestX = tweenedMotionList.get(index).getX2();
      }
      if (tweenedMotionList.get(index).getY2() > biggestY) {
        biggestY = tweenedMotionList.get(index).getY2();
      }
    }

    this.setBackground(Color.WHITE);
    this.setSize(Integer.parseInt(canvas[3] + biggestX), Integer.parseInt(canvas[4]) + biggestY);
    this.setLocation(Integer.parseInt(canvas[1]), Integer.parseInt(canvas[2]));

    this.timer = new Timer(1000 / this.ticksPerSecond, this); 
    this.timer.start();

  }

  /**
   * Flip loop toggle. 
   */
  public void flipLoopToggle() {
    this.toggleLoopOn = (this.toggleLoopOn + 1) % 2;
  }

  /**
   * Restart timer.
   */
  public void restart() {

    if (this.motionIndex == tweenedMotionList.size() && this.toggleLoopOn == 1) {
      this.motionIndex = 0;
    }

  }

  /**
   * Pause the timer.
   */
  public void pause() {
    this.timer.stop();
  }

  /**
   * Resume the timer.
   */
  public void resume() {
    this.timer.start();
  }

  @Override
  public void paintComponent(Graphics g) {

    Graphics2D g2D = (Graphics2D) g;
    super.paintComponent(g2D); // Erases everything in this JPanel to prepare for new drawing.

    int tick = this.currentTick;
    while (tick == this.currentTick) {

      if (this.motionIndex >= tweenedMotionList.size()) {
        break;
      }

      Color c = new Color(tweenedMotionList.get(motionIndex).getR1(),
          tweenedMotionList.get(motionIndex).getG1(), tweenedMotionList.get(motionIndex).getB1());
      g2D.setColor(c);

      if (tweenedMotionList.get(motionIndex).getShape().equals("rectangle")) {
        g2D.fillRect(tweenedMotionList.get(motionIndex).getX1(),
            tweenedMotionList.get(motionIndex).getY1(), tweenedMotionList.get(motionIndex).getW1(),
            tweenedMotionList.get(motionIndex).getH1());
      } else if (tweenedMotionList.get(motionIndex).getShape().equals("ellipse")
          || tweenedMotionList.get(motionIndex).getShape().equals("oval")) {
        g2D.fillOval(tweenedMotionList.get(motionIndex).getX1(),
            tweenedMotionList.get(motionIndex).getY1(), tweenedMotionList.get(motionIndex).getW1(),
            tweenedMotionList.get(motionIndex).getH1());
      } else {
        g2D.fillRect(tweenedMotionList.get(motionIndex).getX1(),
            tweenedMotionList.get(motionIndex).getY1(), tweenedMotionList.get(motionIndex).getW1(),
            tweenedMotionList.get(motionIndex).getH1());
      }

      this.motionIndex++;
      if (this.motionIndex >= tweenedMotionList.size()) {
        break;
      } else {
        this.currentTick = tweenedMotionList.get(motionIndex).getT1();
      }
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.repaint();
    this.restart();
  }

}
