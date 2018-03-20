package board.gui.component;

import board.app.Prompter;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ActionComponent extends HBox {

  public ActionComponent(Node node, String buttonText, Runnable runnable) {
    var button = new Button(buttonText);
    button.setOnAction(e -> runnable.run());
    getChildren().addAll(node, button);
  }

}
