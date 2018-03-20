package board.gui.component;

import board.Manager;
import board.View;
import board.observer.Handler;

import javafx.scene.layout.VBox;

public class ViewComponent extends VBox {

  public ViewComponent(Manager manager, View view, Handler handler) {
    BoardComponent board = new BoardComponent(manager, view, handler);
    FiltersComponent filters = new FiltersComponent(manager, view, handler);
    getChildren().addAll(board, filters);
  }

}
