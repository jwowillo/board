package board.gui.component;

import board.app.Prompter;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Row extends HBox {

  public Row(Text text, String buttonText, Runnable runnable) {
    Button button = new Button(buttonText);
    button.setOnAction(e -> runnable.run());
    getChildren().add(text);
    getChildren().add(button);

  }

}
