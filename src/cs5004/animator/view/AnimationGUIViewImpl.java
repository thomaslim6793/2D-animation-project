package cs5004.animator.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import cs5004.animator.model.MotionElement;

/**
 * GUI view of our model. Uses JFrame. Does not yet require ActionListner, as
 * the animation plays automatically when this object is called.
 *
 */
public class AnimationGUIViewImpl extends JFrame implements AnimationGUIView, ActionListener {
  
  /**
   * Fields. Important ones are tweenedMotionList which comes from the model, and 
   * ticksPerSecond which comes from the command line argument. 
   */
  LinkedList<MotionElement> tweenedMotionList; 
  int ticksPerSecond; 
  
  protected JMenuBar menuBar;
  protected JMenu file;
  protected JMenuItem exit;
  
  private AnimationPanel animationPanel;

  
  /**
   * Constructor. Make JFrame, and create a JPanel that plays the animation 
   * immediately when its created here. 
   * @param tweenedMotionList Data from our model.
   * @param canvas Data from our model to initialize the JFrame dimensions. 
   * @param ticksPerSecond Unit of tempo of our animation. 
   */
  public AnimationGUIViewImpl(LinkedList<MotionElement> tweenedMotionList, 
      String[] canvas, int ticksPerSecond) {   

    super("Visual Animation");
    
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
   
    
    
    this.setSize(Integer.parseInt(canvas[3]) + biggestX, Integer.parseInt(canvas[4]) + biggestY);
    this.setLocation(Integer.parseInt(canvas[1]), Integer.parseInt(canvas[2]));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    this.menuBar = new JMenuBar();
    this.setJMenuBar(this.menuBar);
   
    this.file = new JMenu("File");
    this.menuBar.add(this.file);
    
    this.exit = new JMenuItem("Exit");
    this.exit.setName("Exit program");
    this.file.add(this.exit);
    this.exit.addActionListener(this);
    

    this.tweenedMotionList = tweenedMotionList;
    this.ticksPerSecond = ticksPerSecond; 
    this.animationPanel = new AnimationPanel(this.tweenedMotionList, canvas, this.ticksPerSecond);
    
    
    this.add(this.animationPanel);
    
    this.setVisible(true);
    
  }
  
  @Override 
  public AnimationPanel getAnimationPanel() {
    return this.animationPanel;
  }

  
  @Override
  public void actionPerformed(ActionEvent e) {
    Component c = (Component) e.getSource();
    
    if (c.getName().equals("Exit program")) {
      System.exit(0);
    }
    
  }
  
 
  
}
