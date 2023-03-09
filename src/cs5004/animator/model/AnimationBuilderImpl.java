package cs5004.animator.model;

/**
 * This is the class that builds a model using AnimationModelImpl. 
 * I.e. it is composition on AnimationModelImpl. 
 *
 */
public class AnimationBuilderImpl implements AnimationBuilder<AnimationModel> {

  /** 
   * This is the only field that really matters. 
   */
  private AnimationModel model; 
  
  /**
   * Constructor that creates a model object. 
   */
  public AnimationBuilderImpl() {
    this.model = new AnimationModelImpl();
  }
  
  @Override
  public AnimationModel build() {
    
    return (AnimationModelImpl) this.model; // Cast it so I have access to all the methods. 
  }

  @Override
  public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
    String s = String.format("canvas %d %d %d %d", x, y, width, height); 
    this.model.addElement(s); // Add canvasElement given something like "canvas 200 70 360 360"
    return this; 
  }

  @Override
  public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
    String s = String.format("shape %s %s", name, type);
    this.model.addElement(s); // Add shapeElement given something like "shape R rectangle"
    return this;
  }

  @Override
  public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, 
      int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, 
      int h2, int r2, int g2, int b2) {
    String s = String.format("motion %s %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d %d", 
        name, t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2, h2, r2, g2, b2);
    this.model.addElement(s); 
    return this;
  }

}
