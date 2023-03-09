package cs5004.animator.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;

import cs5004.animator.model.AnimationBuilder;
import cs5004.animator.model.AnimationBuilderImpl;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.AnimationReader;
import cs5004.animator.model.MotionElement;
import cs5004.animator.view.AnimationGUIViewImpl;
import cs5004.animator.view.AnimationGUIViewImprovedImpl;
import cs5004.animator.view.AnimationSVGViewImpl;
import cs5004.animator.view.AnimationTextViewImpl;

/**
 * Controller class. It contains three methods, each corresponding to playing a particular 
 * view based on the command line inputs.
 */
public class AnimationControllerImpl implements AnimationController {
  
  private String inArg;
  private String outArg;
  private String speedArg;

  /**
   * Constructor for this controller. 
   * @param inArg Input for the animation.
   * @param outArg Output of the animation.
   * @param viewArg View of the animation
   * @param speedArg speed of the animation
   */
  public AnimationControllerImpl(String inArg, String outArg, String viewArg, String speedArg) {
    this.inArg = inArg;
    this.outArg = outArg;
    this.speedArg = speedArg;
    

  }
  

  @Override
  public void playTextView() {
    AnimationBuilder<AnimationModel> builder = new AnimationBuilderImpl();
    FileReader f;
    try {
      f = new FileReader(this.inArg);
      AnimationModelImpl m = (AnimationModelImpl) AnimationReader.parseFile(f, builder);
      String data = m.toStringFull();
      AnimationTextViewImpl textView = new AnimationTextViewImpl(this.speedArg);
      textView.addWholeData(data);
      if (outArg.equals("System.out")) {
        System.out.println(textView.toString());
      }
      else {
        PrintWriter out = new PrintWriter(this.outArg);
        out.println(textView.toString());
        out.close();
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

  @Override
  public void playVisualView() {
    AnimationBuilder<AnimationModel> builder = new AnimationBuilderImpl();
    FileReader f;
    try {
      f = new FileReader(this.inArg);
      AnimationModelImpl m = (AnimationModelImpl) AnimationReader.parseFile(f, builder);
      LinkedList<MotionElement> tweenedMotionList = m.tweenedMotionList(m.getMotionList());
      
      
      
      tweenedMotionList = m.sortMotionsByTime(tweenedMotionList); // First argument
      
      String[] canvas = m.getCanvas().toString().split(" "); // second argument
      new AnimationGUIViewImpl(tweenedMotionList, canvas, Integer.parseInt(this.speedArg));
   
      
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

  @Override
  public void playSVGView() {
    AnimationBuilder<AnimationModel> builder = new AnimationBuilderImpl();
    FileReader f;
    try {
      f = new FileReader(this.inArg);
      AnimationModelImpl m = (AnimationModelImpl) AnimationReader.parseFile(f, builder);
      AnimationSVGViewImpl svgView = new AnimationSVGViewImpl(m, Integer.parseInt(this.speedArg));
      if (outArg.equals("System.out")) {
        System.out.println(svgView.getFinalSVG(true));
      }
      else {
        PrintWriter out = new PrintWriter(this.outArg);
        out.println(svgView.getFinalSVG(true));
        out.close();
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  @Override
  public void playVisualViewImproved() {
    AnimationBuilder<AnimationModel> builder = new AnimationBuilderImpl();
    FileReader f;
    try {
      f = new FileReader(this.inArg);
      AnimationModelImpl m = (AnimationModelImpl) AnimationReader.parseFile(f, builder);
      LinkedList<MotionElement> tweenedMotionList = m.tweenedMotionList(m.getMotionList());
      
      
      
      tweenedMotionList = m.sortMotionsByTime(tweenedMotionList); //First argument
      
      String[] canvas = m.getCanvas().toString().split(" "); // second argument
      new AnimationGUIViewImprovedImpl(tweenedMotionList, canvas, Integer.parseInt(this.speedArg));
   
      
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
}
