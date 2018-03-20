package board.gui.component;

import board.Filter;
import board.Manager;
import board.observer.Handler;

public class AddFilterRow extends AddRow {

  public AddFilterRow(Manager manager, Handler handler) {
    super((t) -> {
      try {
        manager.addFilter(new Filter(t));
      } catch (Exception exception) {
        handler.handle(exception);
      }
    });
  }

}
