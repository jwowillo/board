package board.gui.component;

import board.Filter;
import board.Manager;
import board.observer.Handler;

/**
 * AddFilterComponent adds entered Filters to a Manager and displays errors to
 * a Handler if the Filter couldn't be added.
 */
public class AddFilterComponent extends AddComponent {

  /**
   * AddFilterComponent initializes AddComponent with a Consumer that adds the
   * Filter to the Manager with the text and handles errors with the Handler.
   */
  public AddFilterComponent(Manager manager, Handler handler) {
    super(t -> {
      try {
        manager.addFilter(new Filter(t));
      } catch (Exception exception) {
        handler.handle(exception);
      }
    });
  }

}
