package board.gui.component;

import board.Board;
import board.Manager;
import board.Topic;
import board.View;
import board.observer.Handler;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class ViewScroll extends LabelledScroll {

  private static final int GRID_WIDTH = 3;

  private static final int PADDING = 10;

  public ViewScroll(Manager manager, View view, Handler handler) {
    super("Board:", pane(manager, view, handler));
  }

  private static Node pane(Manager manager, View view, Handler handler) {
    GridPane pane = new GridPane();
    pane.setPadding(new Insets(PADDING));
    int tiles = 0;
    for (Topic topic : view.topics()) {
      Tile tile = new Tile(manager, handler, topic, view.notes(topic));
      pane.add(tile, tiles % GRID_WIDTH, tiles / GRID_WIDTH);
      tiles++;
    }
    pane.add(new AddTile(manager, handler),
        tiles % GRID_WIDTH, tiles / GRID_WIDTH);
    return pane;
  }

}
