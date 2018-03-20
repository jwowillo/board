package board.gui.component;

import javafx.scene.control.TextArea;

import java.util.function.Consumer;

public class AddComponent extends ActionComponent {

  public AddComponent(Consumer<String> consumer) {
    this(consumer, new TextArea());
  }

  private AddComponent(Consumer<String> consumer, TextArea text) {
    super(text, "+", () -> consumer.accept(text.getText()));
  }

}
