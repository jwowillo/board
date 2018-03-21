package board.gui.component;

import board.Manager;
import board.View;
import board.observer.Handler;

import javafx.scene.layout.VBox;

/** ViewComponent displays a BoardComponent and a FiltersComponent. */
public class ViewComponent extends VBox {

  /**
   * ViewComponent that modifies the Manager, displays from the View, and
   * handles errors with the Handler.
   */
  public ViewComponent(Manager manager, View view, Handler handler) {
    var board = new BoardComponent(manager, view, handler);
    var filters = new FiltersComponent(manager, view, handler);
    getChildren().addAll(board, filters);
  }

}
