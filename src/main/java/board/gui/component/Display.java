package board.gui.component;

import board.Board;
import board.Manager;
import board.View;
import board.observer.Handler;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Display extends VBox {

  private final Handler handler;

  private final Board initialBoard = new Board();

  private Manager currentManager = new Manager(initialBoard, new ArrayList<>());

  private View currentView = new View(initialBoard);


  public Display(Handler handler) {
    this.handler = handler;
    showManagerAndView();
  }

  public void setManager(Manager manager) {
    currentManager = manager;
    getChildren().clear();
    showManagerAndView();
  }

  public void show(View view) {
    currentView = view;
    getChildren().clear();
    showManagerAndView();
  }

  private void showManagerAndView() {
    ViewScroll viewScroll = new ViewScroll(currentManager, currentView,
        handler);
    FilterScroll filterScroll = new FilterScroll(currentManager, currentView,
        handler);
    getChildren().addAll(viewScroll, filterScroll);
  }

}
