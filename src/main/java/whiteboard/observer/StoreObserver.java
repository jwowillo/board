package board.observer;

import board.BoardException;
import board.Note;
import board.Observer;
import board.Topic;
import board.View;

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
      store.updateBoard(view);
    } catch (StoreException exception) {
      handler.handle(exception);
    }
  }

}
