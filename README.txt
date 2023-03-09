This program is used for creating animations. It is a java program that uses the MVC (Model - View - Controller) design architecture, which are three distinct components of a program that are loosely coupled and have distinct roles. 

To use this program, you follow these steps:

1. Open command line, and first change current directory to the directory that contains the JAR file.

2. In the command line prompt, type in 'java -cp animator.jar cs5004.animator.EasyAnimator -view <view option> -in <input file> -out <output file> -speed <speed of animation>'

Example is: java -cp animator.jar cs5004.animator.EasyAnimator -view playback -in code/smalldemo.txt -speed 10

3. The option for <view option> are: 'text', 'visual', 'svg', and 'playback'. The arguments for '-view' and '-in' are mandatory. 

4. After executing the command line instruction, the program will execute, and animation will be provided to you, either as a GUI animation, a text file, or System.out. 

Overview of purpose for every class interface:

1. EasyAnimator.java: This class is the class that contains the main method, and thus is the main entry to our program. This class first validates the input arguments, and then based on the input arguments from the command line, executes one of the four views specified by the useerr.

2. AnimationController.java: This is the interface for our controller class.
AnimatiionControllerImpl.java: This is the class for our controller. It contains four methods, each executing one of the four distinct views. Each of these methods first reads in the input file, creates a model from that file, and then calls the corresponding view class to produce the view. 

3. AnimationBuilder.java: Interface for building our model. 
AnimationBuilderImpl.java: Class that builds our model. It contains our model as its attribute, and then fills up our model by processing data about the animation.

4. AnimationReader.java: Class for parsing a text file that contains our animation data. A factory for producing new animations, given a source of shapes and a builder for constructing animations.

5. AnimationElement.java: Interface that represents the elements of a 2D animation.
CanvasElement.java: Class that inplements AnimationElement. It contains the data that represents the
canvas of an animation.
ShapeElement.java: Class that implements AnimationElement. It contains data that represents the shape of an animation. Two important attrributes of this element is starting motion and final motion. 
MotionElement.java: Class that implements AnimationElement. It contains data that represents a motion of a shapee for a given time frame. It contains a set of motion properties at starting time, and the same set of motion properties but for ending time of the motion.

6. AnimationModel.java: Interface for our model. 
AnimationModelImpl.java: The class that implements AnimationModel.java. It constructs the model object, which consists of three LinkedList objects as attributes. One list is for the canvasElement, so it contains a single node. One list is for shapeElement, which consists of nodes each representing a shape in the animation. One list is for motionElement, which consists of nodes each representing a motion of some shape at a given time frame. 

7. AnimationGUIView.java: Interface for our GUI view.
AnimationGUIViewImpl.java: Class that implements our GUI view interface. This class implements JFrame class from javax.swing, and implements ActionListener. It creates a JFrame object, and alson creates a JPanel object which starts the animation when it is created. 
AnimationPanel.java: Class that extends JPanel class and implements ActionListener. This is the class that paints the animation, frame by frame, in the large panel section of the JFrame. When it is created, it starts a timer, each tick of the timer creating an action event. Since AnimationPanel is an ActionListener, this timer ticking event triggers its actionPerformed method, which calls the repaint method which calls the paintComponent method. The paintComponent method is the method that erases the current image in the panel, and draws a new image onto the panel depending on the values of the attributes which selects the part of the data from the model. In short, for each tick of the timer, the paintComponent draws a motion from the model in chronological order, I.e. all motions from t = 1 is painted in the first frame, then all the motion from t = 2 is painted in the second frame, and so on and so forth.

8. AnimationGUIViewImproved.java: Interface for our improved GUI view which allows for pause, resume, and loop functionalities. 
AnimationGUIViewImprovedImpl.java: Class that implements the above interface. This is an improved view, it has more functionalities. This class extends the original component, and calls its super constructor. Then it adds an extra panel region to add three buttons for the three functionalities. 
The AnimationPanel class had to be modified to add three extra methods, one method that pauses the timer, one method that starts the timer, and one method that toggles the looping functionality. 

9. AnimationTextView.java: Interface for our text view. 
AnimationTextViewImpl.java: Class implementing our text view. The way it works is, it takes the 
string return values from some of our methods from our model, and it parses those string data 
into proper format. 

10. AnimationSVGView.java: Interface for our SVG view.
AnimationSVGViewImpl.java: Class implementing our SVG view. The way it works is same as the text view. It takes results from our model and parses it into SVG format. 

