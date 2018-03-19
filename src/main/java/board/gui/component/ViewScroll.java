package board.gui.component;

import board.Board;
import board.Manager;
import board.Topic;
import board.View;
import board.observer.Handler;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ViewScroll extends ScrollPane {

  private static final int GRID_WIDTH = 3;

  private static final int PADDING = 10;

  public ViewScroll(Handler handler) {
    Board initialBoard = new Board();
    Manager currentManager = new Manager(initialBoard, new ArrayList<>());
    View currentView = new View(initialBoard);
    build(currentManager, currentView, handler);
  }

  public ViewScroll(Manager manager, View view, Handler handler) {
    build(manager, view, handler);
  }

  private void build(Manager manager, View view, Handler handler) {
    GridPane pane = new GridPane();
    pane.setPadding(new Insets(PADDING));
    int tiles = 0;
    for (Topic topic : view.topics()) {
      TopicTile tile = new TopicTile(manager, handler,
          topic, view.notes(topic));
      pane.add(tile, tiles % GRID_WIDTH, tiles / GRID_WIDTH);
      tiles++;
    }
    pane.add(new AddTile(manager), tiles % GRID_WIDTH, tiles / GRID_WIDTH);
    setContent(pane);
  }

}
