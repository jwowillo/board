package board.gui.component;

import javafx.scene.text.Text;

/**
 * AddComponent displays text and a remove-button which triggers a Runnable that
 * removes what the text represents.
 */
public class RemoveComponent extends ActionComponent {

  public RemoveComponent(String text, Runnable runnable) {
    super(new Text(text), "X", runnable);
  }

}
