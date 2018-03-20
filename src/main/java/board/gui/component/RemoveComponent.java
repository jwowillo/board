package board.gui.component;

import javafx.scene.text.Text;

public class RemoveComponent extends ActionComponent {

  public RemoveComponent(String text, Runnable runnable) {
    super(new Text(text), "X", runnable);
  }

}
