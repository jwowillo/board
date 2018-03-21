package board.gui.component;

import board.Filter;
import board.Manager;
import board.View;
import board.observer.Handler;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FiltersComponent displays all the Filters in a View in FilterComponents along
 * with an AddFilterComponent to add new Filters.
 */
public class FiltersComponent extends VBox {

  /**
   * FiltersComponent adds Filters to the Manager, displays Filters from the
   * view, and handles errors with the Handler.
   */
  public FiltersComponent(Manager manager, View view, Handler handler) {
    getChildren().add(new Label("Filters:"));
    for (Filter filter : view.filters()) {
      getChildren().add(new FilterComponent(manager, handler, filter));
    }
    getChildren().add(new AddFilterComponent(manager, handler));
  }

}
