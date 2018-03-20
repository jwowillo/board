package board.gui.component;

import board.app.Prompter;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ActionComponent extends HBox {

  public ActionComponent(Node node, String buttonText, Runnable runnable) {
    Button button = new Button(buttonText);
    button.setOnAction(e -> runnable.run());
    getChildren().add(node);
    getChildren().add(button);

  }

}
