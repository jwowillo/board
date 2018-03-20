package board.gui.component;

import board.Filter;
import board.Manager;
import board.View;
import board.observer.Handler;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class FiltersComponent extends VBox {

  public FiltersComponent(Manager manager, View view, Handler handler) {
    getChildren().add(new Label("Filters:"));
    for (Filter filter : view.filters()) {
      getChildren().add(new FilterComponent(manager, handler, filter));
    }
    getChildren().add(new AddFilterComponent(manager, handler));
  }

}
