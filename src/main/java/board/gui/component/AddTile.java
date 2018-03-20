package board.gui.component;

import board.Manager;
import board.observer.Handler;

import javafx.scene.layout.VBox;

public class AddTile extends VBox {

  public AddTile(Manager manager, Handler handler) {
    getChildren().add(new AddTopicRow(manager, handler));
  }

}
