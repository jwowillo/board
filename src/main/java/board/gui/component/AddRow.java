package board.gui.component;

import javafx.scene.control.TextArea;

import java.util.function.Consumer;

public class AddRow extends Row {

  public AddRow(Consumer<String> consumer) {
    this(consumer, new TextArea());
  }

  private AddRow(Consumer<String> consumer, TextArea text) {
    super(text, "+", () -> consumer.accept(text.getText()));
  }

}
