package whiteboard.observer;

import whiteboard.View;
import whiteboard.Whiteboard;

/** Store supports updating Whiteboards and retrieving Whiteboards. */
public interface Store {

  /**
   * updateWhiteboard from View in the Store.
   *
   * <p>Should create the Whiteboard if it doesn't exist.
   *
   * @throws StoreException If the Whiteboard couldn't be updated.
   */
  void updateWhiteboard(View view) throws StoreException;

  /**
   * Whiteboard in the Store.
   *
   * <p>Should return an empty Whiteboard if it doesn't exist.
   *
   * @throws StoreException If the Whiteboard couldn't be retrieved.
   */
  Whiteboard whiteboard() throws StoreException;

}
