package cs5004.animator.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;

import cs5004.animator.model.MotionElement;

/**
 * Improved GUI view that contains additional features in the JFrame, such as
 * buttons that can resume, pause, and toggle loop.
 *
 */
public class AnimationGUIViewImprovedImpl implements AnimationGUIViewImproved, ActionListener {

  private AnimationGUIViewImpl basicView;
  private JButton resumeButton;

  /**
   * Constructor class that creates the JFrame and all the necessary panels.
   * 
   * @param tweenedMotionList Data from model
   * @param canvas            Data from model
   * @param ticksPerSecond    Speed unit
   */
  public AnimationGUIViewImprovedImpl(LinkedList<MotionElement> tweenedMotionList, String[] canvas,
      int ticksPerSecond) {

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

    this.basicView = new AnimationGUIViewImpl(tweenedMotionList, canvas, ticksPerSecond);
    this.basicView.setSize(Integer.parseInt(canvas[3]) + biggestX + 250,
        Integer.parseInt(canvas[4]) + biggestY);

    JPanel buttonPanel;
    buttonPanel = new JPanel(true); // argument 'true' to make buttonPanel visible.
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.setSize(200, 500);
    buttonPanel.setLocation(0, 1000);
    buttonPanel.setLayout(null);
    basicView.add(buttonPanel);
    basicView.validate();

    // Toggle loop button
    JButton toggleLoopButton;
    toggleLoopButton = new JButton("Enable/Disable loop");
    toggleLoopButton.setBounds(Integer.parseInt(canvas[3]) + biggestX + 25, 100, 150, 50);
    buttonPanel.add(toggleLoopButton);
    toggleLoopButton.setName("loop");
    toggleLoopButton.addActionListener(this);
    toggleLoopButton.validate();
    // Pause Button
    JButton pauseButton;
    pauseButton = new JButton("Pause");
    pauseButton.setBounds(Integer.parseInt(canvas[3]) + biggestX + 25, 200, 150, 50);
    buttonPanel.add(pauseButton);
    pauseButton.setName("pause");
    pauseButton.addActionListener(this);
    pauseButton.validate();
    // Resume Button
    JButton resumeButton;
    resumeButton = new JButton("Resume");
    resumeButton.setBounds(Integer.parseInt(canvas[3]) + biggestX + 25, 300, 150, 50);
    buttonPanel.add(resumeButton);
    resumeButton.setName("resume");
    resumeButton.addActionListener(this);
    resumeButton.validate();

    // Add this line after I add all my components to the frame, because before, my
    // JButtons would
    // sometimes flicker or not show until I hover over their location.
    this.basicView.validate();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Component c = (Component) e.getSource(); 
    if (c.getName().equals("loop")) {
      this.basicView.getAnimationPanel().flipLoopToggle();
    } else if (c.getName().equals("pause")) {
      this.basicView.getAnimationPanel().pause();
    } else if (c.getName().equals("resume")) {
      this.basicView.getAnimationPanel().resume();
    }

  }

}
