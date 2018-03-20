package board.gui.component;

import board.Filter;
import board.Manager;
import board.View;
import board.observer.Handler;

import javafx.scene.control.ScrollPane;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class LabelledScroll extends ScrollPane {

  public LabelledScroll(String label, Node node) {
    VBox pane = new VBox();
    pane.getChildren().add(new Label(label));
    pane.getChildren().add(node);
    setContent(pane);
  }

}
