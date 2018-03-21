package board.gui.component;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * ActionComponent displays a Node with a button with configured text next to it
 * that performs a Runnable when pressed.
 */
public class ActionComponent extends HBox {

  /**
   * ActionComponent displays the Node next to a Button with the given text that
   * runs the Runnable when pressed.
   */
  public ActionComponent(Node node, String buttonText, Runnable runnable) {
    var button = new Button(buttonText);
    button.setOnAction(e -> runnable.run());
    getChildren().addAll(node, button);
  }

}
