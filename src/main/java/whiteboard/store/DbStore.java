package board.store;

import board.Board;
import board.View;
import board.observer.Store;
import board.observer.StoreException;

public class DbStore implements Store {

  public DbStore(String path) {

  }

  @Override
  public void updateBoard(View view) throws StoreException {
    throw new StoreException();
  }

  @Override
  public Board board() throws StoreException {
    throw new StoreException();
  }

}
