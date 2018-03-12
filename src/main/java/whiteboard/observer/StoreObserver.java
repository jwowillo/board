package whiteboard.observer;

import whiteboard.Note;
import whiteboard.Observer;
import whiteboard.Topic;
import whiteboard.View;
import whiteboard.WhiteboardException;

/** StoreObserver stores observed changes and defers Exceptions to a Handler. */
public class StoreObserver implements Observer {

  /** store to use. */
  private final Store store;

  /** handler of Exceptions. */
  private final Handler handler;

  /**
   * StoreObserver which stores observed changes in the Store and defers
   * Exceptions to the Handler.
   */
  public StoreObserver(Store store, Handler handler) {
    this.store = store;
    this.handler = handler;
  }

  /** observe stores the View. */
  @Override
  public void observe(View view) {
    try {
      store.updateWhiteboard(view);
    } catch (StoreException exception) {
      handler.handle(exception);
    }
  }

}
