package board.gui.component;

import board.Filter;
import board.Manager;
import board.observer.Handler;

/**
 * FilterComponent displays a Filter and a button to remove the Filter and
 * displays errors to the Handler if the Filter couldn't be removed.
 */
public class FilterComponent extends RemoveComponent {

  /**
   * FilterComponent initializes RemoveComponent with a Runnable that removes
   * the Filter from the Manager and handles errors with the Handler.
   */
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
