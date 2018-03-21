package board.gui.component;

import javafx.scene.control.TextArea;

import java.util.function.Consumer;

/**
 * AddComponent has a text-area and an add-button which triggers a Consumer that
 * is passed the text-area's text.
 */
public class AddComponent extends ActionComponent {

  public AddComponent(Consumer<String> consumer) {
    this(consumer, new TextArea());
  }

  private AddComponent(Consumer<String> consumer, TextArea text) {
    super(text, "+", () -> consumer.accept(text.getText()));
  }

}
