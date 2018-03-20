package board.gui.component;

import board.Filter;
import board.Manager;
import board.observer.Handler;

public class FilterComponent extends RemoveComponent {

  public FilterComponent(Manager manager, Handler handler, Filter filter) {
    super(filter.term(), () -> {
      try {
        manager.removeFilter(filter);
      } catch (Exception exception) {
        handler.handle(exception);
      }
    });
  }

}
