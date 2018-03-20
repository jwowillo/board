package board.gui.component;

import board.Board;
import board.Manager;
import board.View;
import board.observer.Handler;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ViewComponent extends VBox {

  private final Handler handler;

  private final Board initialBoard = new Board();

  private Manager manager = new Manager(initialBoard, new ArrayList<>());

  private View view = new View(initialBoard);

  public ViewComponent(Handler handler) {
    this.handler = handler;
    showManagerAndView();
  }

  public void setManager(Manager manager) {
    this.manager = manager;
    getChildren().clear();
    showManagerAndView();
  }

  public void show(View view) {
    this.view = view;
    getChildren().clear();
    showManagerAndView();
  }

  private void showManagerAndView() {
    BoardComponent board = new BoardComponent(manager, view, handler);
    FiltersComponent filters = new FiltersComponent(manager, view, handler);
    getChildren().addAll(board, filters);
  }

}
