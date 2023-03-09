package cs5004.animator.view;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import cs5004.animator.model.AnimationBuilder;
import cs5004.animator.model.AnimationBuilderImpl;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.AnimationReader;

/**
 * Test SVG view by testing smalldemo file with and without playback loop
 * enabled.
 */
public class AnimationSVGViewImplTest {

  /**
   * Test smalldemo.txt file prints out the correct string value.
   */
  @Test
  public void testSmallDemoWithoutLoop() {
    AnimationBuilder<AnimationModel> builder = new AnimationBuilderImpl();
    FileReader f;
    try {
      f = new FileReader("code/smalldemo.txt");
      AnimationModelImpl m = (AnimationModelImpl) AnimationReader.parseFile(f, builder);
      int ticksPerSecond = 20;
      AnimationSVGViewImpl svgView = new AnimationSVGViewImpl(m, ticksPerSecond);
      assertEquals("<svg width=\"800\" height= \"730\" version=\"1.1\" xmlns=\"http://www."
          + "w3.org/2000/svg\" >\n" + "\n"
          + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fi"
          + "ll=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
          + "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"1.0ms\" attri"
          + "buteName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" + "\n"
          + "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"2000ms\" attri"
          + "buteName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"2000ms\" attribute"
          + "Name=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n" + "\n" + "\n"
          + "<animate attributeType=\"xml\" begin=\"2550ms\" dur=\"950ms\" attributeNa"
          + "me=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n" + "\n"
          + "<animate attributeType=\"xml\" begin=\"3500ms\" dur=\"1500ms\" attributeN"
          + "ame=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"3500ms\" dur=\"1500ms\" attributeN"
          + "ame=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n" + "\n" + "</rect>\n" + "\n"
          + "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fil"
          + "l=\"rgb(0,0,255)\" visibility=\"hidden\" >\n"
          + "<animate attributeType=\"xml\" begin=\"300ms\" dur=\"1.0ms\" attrib"
          + "uteName=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" + "\n"
          + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"1500ms\" at"
          + "tributeName=\"cy\" from=\"100\" to=\"280\" fill=\"freeze\" />\n" + "\n"
          + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"1000ms\" attrib"
          + "uteName=\"cy\" from=\"280\" to=\"400\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"2500ms\" dur=\"1000ms\" attrib"
          + "uteName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" fill=\"freeze\" />\n"
          + "\n" + "<animate attributeType=\"xml\" begin=\"3500ms\" dur=\"500ms\" attributeNam"
          + "e=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n" + "\n"
          + "\n" + "</ellipse>\n" + "\n" + "\n" + "</svg>", svgView.getFinalSVG(false));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Verify that smalldemo.txt file with looping enabled works properly.
   */
  @Test
  public void testSmallDemoWithLoop() {
    AnimationBuilder<AnimationModel> builder = new AnimationBuilderImpl();
    FileReader f;
    try {
      f = new FileReader("code/smalldemo.txt");
      AnimationModelImpl m = (AnimationModelImpl) AnimationReader.parseFile(f, builder);
      int ticksPerSecond = 20;
      AnimationSVGViewImpl svgView = new AnimationSVGViewImpl(m, ticksPerSecond);
      assertEquals("<svg width=\"800\" height= \"730\" version=\"1.1\" xml"
          + "ns=\"http://www.w3.org/20" + "00/svg\" >\n" + "\n" + "<rect>\n"
          + "<animate id=\"base\" begi" + "n=\"0;base.end\" dur=\"5" + "050"
          + "ms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" + "</rect>\n" + "\n"
          + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"10"
          + "0\" fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+50ms\" dur"
          + "=\"1.0ms\" attributeName=\"visibility\" from=\"hidden\" to=\"visi"
          + "ble\" fill=\"freeze\" />\n" + "\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+"
          + "500ms\" dur=\"2000ms\" attributeName=\"x\" from=\"200\" to=\"300\" fill=\"f"
          + "reeze\" />\n" + "<animate attributeType=\"xml\" begin=\"base.begin+"
          + "500ms\" dur=\"2000ms\" attributeName=\"y\" from=\"200\" to=\"300\" fi"
          + "ll=\"freeze\" />\n" + "\n" + "\n" + "<animate attribut"
          + "eType=\"xml\" begin=\"base.begin"
          + "+2550ms\" dur=\"950ms\" attributeName=\"width\" fro"
          + "m=\"50\" to=\"25\" fill=\"freeze\" />\n" + "\n"
          + "<animate attributeType=\"xml\" begin=\"base.be"
          + "gin+3500ms\" dur=\"1500ms\" attributeName=\"x\" fro"
          + "m=\"300\" to=\"200\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+3500ms\" du"
          + "r=\"1500ms\" attributeName=\"y\" from=\"300\" t" + "o=\"200\" fill=\"freeze\" />\n"
          + "\n" + "<animate attributeType=\"xml\" begin=\"base.end\" d" + "ur=\"1ms\" attribu"
          + "teName=\"x\" to=\"200\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end\" d" + "ur=\"1ms\" attributeNa"
          + "me=\"y\" to=\"200\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end\" d" + "ur=\"1ms\" attributeNa"
          + "me=\"width\" to=\"50\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end\" d" + "ur=\"1ms\" attributeNam"
          + "e=\"height\" to=\"100\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end\" d" + "ur=\"1ms\" attributeNam"
          + "e=\"fill\" to=\"rgb(255,0,0)\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end\" du" + "r=\"1ms\" attributeNa"
          + "me=\"visibility\" to=\"hidden\" fill=\"freeze\" />\n" + "</re" + "ct>\n" + "\n"
          + "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" f"
          + "ill=\"rgb(0,0,255)\" vis" + "ibility=\"hidden\" >\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+300ms\" d"
          + "ur=\"1.0ms\" attributeNa"
          + "me=\"visibility\" from=\"hidden\" to=\"visible\" fill=\"free" + "ze\" />\n" + "\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+1000ms\" du"
          + "r=\"1500ms\" attributeNa" + "me=\"cy\" from=\"100\" to=\"280\" fill=\"freeze\" />\n"
          + "\n" + "<animate attributeType=\"xml\" begin=\"base.begin+2500ms\" d"
          + "ur=\"1000ms\" attrib" + "uteName=\"cy\" from=\"280\" to=\"400\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.begin+2500ms\" d"
          + "ur=\"1000ms\" attributeNa"
          + "me=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" fil" + "l=\"freeze\" />\n"
          + "\n" + "<animate attributeType=\"xml\" begin=\"base.begin+3500ms\" d"
          + "ur=\"500ms\" attributeNa"
          + "me=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fil" + "l=\"freeze\" />\n"
          + "\n" + "\n" + "<animate attributeType=\"xml\" begin=\"base.end\" d"
          + "ur=\"1ms\" attributeN" + "ame=\"cx\" to=\"500\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end\" d" + "ur=\"1ms\" attributeNa"
          + "me=\"cy\" to=\"100\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end\" du" + "r=\"1ms\" attribute"
          + "Name=\"rx\" to=\"60\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attribu"
          + "teName=\"ry\" to=\"30\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeNa"
          + "me=\"fill\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeN"
          + "ame=\"visibility\" to=\"hidden\" fill=\"freeze\" />\n" + "</ellipse>\n" + "\n" + "\n"
          + "</svg>", svgView.getFinalSVG(true));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
