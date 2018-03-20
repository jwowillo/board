package board.gui.component;

import board.Manager;
import board.View;
import board.observer.Handler;

import javafx.scene.layout.VBox;

public class ViewComponent extends VBox {

  public ViewComponent(Manager manager, View view, Handler handler) {
    var board = new BoardComponent(manager, view, handler);
    var filters = new FiltersComponent(manager, view, handler);
    getChildren().addAll(board, filters);
  }

}
