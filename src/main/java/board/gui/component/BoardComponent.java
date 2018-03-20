package board.gui.component;

import board.Manager;
import board.View;
import board.observer.Handler;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class BoardComponent extends ScrollPane {

  public BoardComponent(Manager manager, View view, Handler handler) {
    setHbarPolicy(ScrollBarPolicy.NEVER);
    setFitToWidth(true);
    setFitToHeight(true);
    var pane = new FlowPane();
    for (var topic : view.topics()) {
      var component = new TopicAndNotesComponent(manager, handler, topic,
          view.notes(topic));
      pane.getChildren().add(component);
    }
    pane.getChildren().add(new AddTopicComponent(manager, handler));
    var root = new VBox();
    root.getChildren().addAll(new Label("Board:"), pane);
    setContent(root);
  }

}
