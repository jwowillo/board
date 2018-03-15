package board.observer;

import board.Board;
import board.View;

/** Store supports updating and retrieving Boards. */
public interface Store {

  /**
   * updateBoard from View in the Store and create it if it doesn't exist.
   *
   * @throws StoreException If the Board couldn't be updated.
   */
  void updateBoard(View view) throws StoreException;

  /**
   * Board in the Store or an empty Board if it doesn't exist.
   *
   * @throws StoreException If the Board couldn't be retrieved.
   */
  Board board() throws StoreException;

}
