package board.gui.component;

import board.Filter;
import board.Manager;
import board.View;
import board.observer.Handler;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class FilterScroll extends LabelledScroll {

  public FilterScroll(Manager manager, View view, Handler handler) {
    super("Filters:", pane(manager, view, handler));
  }

  private static Node pane(Manager manager, View view, Handler handler) {
    VBox pane = new VBox();
    for (Filter filter : view.filters()) {
      pane.getChildren().add(new FilterRow(manager, handler, filter));
    }
    pane.getChildren().add(new AddFilterRow(manager, handler));
    return pane;
  }

}
