package board.gui.component;

import board.Filter;
import board.Manager;
import board.observer.Handler;

public class FilterRow extends RemoveRow {

  public FilterRow(Manager manager, Handler handler, Filter filter) {
    super(filter.term(), () -> {
      try {
        manager.removeFilter(filter);
      } catch (Exception exception) {
        handler.handle(exception);
      }
    });
  }

}
