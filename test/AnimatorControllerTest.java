import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.IAnimatorController;
import controller.InteractiveCtrl;
import model.IAnimatorModel;
import model.IAnimatorModelState;
import model.SimpleAnimatorModel;
import view.IAnimatorView;
import view.ViewMock;

import static org.junit.Assert.assertEquals;

public class AnimatorControllerTest {

  StringBuilder log = new StringBuilder();
  IAnimatorView view = new ViewMock(log);
  IAnimatorModel model = new SimpleAnimatorModel.TweenBuilder()
          .addRectangle("1", 10, 15, 100, 200,
                  10, 10, 10,
                  0, 10)
          .addScaleToChange("1", 5, 5, 10, 10,
                  1, 5)
          .addRectangle("2", 10, 15, 100, 200,
                  10, 10, 10,
                  0, 10)
          .addMove("2", 10, 10, 20, 20,
                  1, 5)
          .addColorChange("2", 10, 10, 10,
                  100, 100, 100, 5, 10)
          .build();

  IAnimatorModelState model2 = new SimpleAnimatorModel.TweenBuilder()
          .setBounds(100,100)
          .addRectangle("1", 10, 10, 100, 100, 10, 10, 10, 0, 100)
          .addColorChange("1", 10, 10, 10, 15, 15, 15, 0, 5)
          .addMove("1", 10, 10, 15, 40, 6, 7)
          .addColorChange("1", 5,5,5,200,200,200, 6,7)
          .addMove("1", 15, 40, 10, 10, 8, 15)
          .addScaleToChange("1", 100, 100, 200, 200, 15, 30)
          .addOval("2", 10, 10, 10, 50, 10, 10, 10, 0, 50)
          .addMove("2", 10, 10, 50, 50, 0, 10)
          .build();

  IAnimatorController ctrl = new InteractiveCtrl(model, view, 20);

  @Test
  public void testMockViewStart() {
    ctrl.start();
    assertEquals("ButtonaddListenerMethod "
            + "AddKeyListenerMethod MakeVisibleMethod ", log.toString());
  }

  @Test
  public void testMockViewGetDetails() {
    ctrl.start();
    view.getDetails();
    assertEquals("ButtonaddListenerMethod AddKeyListenerMethod "
            + "MakeVisibleMethod getDetailsMethod ", log.toString());
  }

  @Test
  public void testMockViewwriteFile() {
    ctrl.start();
    view.writeFile("hi");
    assertEquals("ButtonaddListenerMethod AddKeyListenerMethod "
            + "MakeVisibleMethod writeFileMethod ", log.toString());
  }

  @Test
  public void testMockViewAddListener() {
    ctrl.start();
    view.addListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        e.getActionCommand();
      }
    });

    assertEquals("ButtonaddListenerMethod AddKeyListenerMethod"
            + " MakeVisibleMethod ButtonaddListenerMethod ", log.toString());
  }

  @Test
  public void testMockViewAddKeyListener() {
    ctrl.start();
    view.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        e.getKeyCode();
      }

      @Override
      public void keyPressed(KeyEvent e) {
        e.getKeyCode();
      }

      @Override
      public void keyReleased(KeyEvent e) {
        e.getKeyCode();
      }
    });

    assertEquals("ButtonaddListenerMethod AddKeyListenerMethod"
            + " MakeVisibleMethod AddKeyListenerMethod ", log.toString());
  }

  @Test
  public void testMockViewRefresh() {
    ctrl.start();
    view.refresh();
    assertEquals("ButtonaddListenerMethod AddKeyListenerMethod "
            + "MakeVisibleMethod RefreshMethod ", log.toString());
  }

  @Test
  public void testMockViewMakeVisible() {
    ctrl.start();
    view.makeVisible();
    assertEquals("ButtonaddListenerMethod AddKeyListenerMethod"
            + " MakeVisibleMethod MakeVisibleMethod ", log.toString());
  }

  @Test
  public void testMockViewsetShapes() {
    ctrl.start();
    view.setShapes(model2.getShapes());
    assertEquals("ButtonaddListenerMethod AddKeyListenerMethod "
            + "MakeVisibleMethod setShapesMethod ", log.toString());
  }

  @Test
  public void testMockViewsetIsLoop() {
    ctrl.start();
    assertEquals(false, view.getIsLoop());
    view.setIsLoop(true);
    assertEquals("ButtonaddListenerMethod AddKeyListenerMethod MakeVisibleMethod"
            + " getIsLoopMethod setIsLoopMethod ", log.toString());
  }

  @Test
  public void testMockViewgetIsLoop() {
    ctrl.start();
    view.getIsLoop();
    assertEquals("ButtonaddListenerMethod AddKeyListenerMethod"
            + " MakeVisibleMethod getIsLoopMethod ", log.toString());
  }

}
