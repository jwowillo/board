package board.observer;

import board.Board;
import board.Manager;
import board.Observer;

import java.util.ArrayList;
import java.util.Collection;

public class StoringManagerFactory {

  // TODO: I like this since it allows DI to be used properly.
  // TODO: Make sure this is a factory and look at factory examples.
  // TODO: Minimize state in constructor.
  // TODO: Document behavior if can't load.

  public Manager build(Store store, Handler handler,
      Collection<Observer> observers) {
    Board board = loadBoard(store, handler);
    Observer storer = new StoreObserver(store, handler);
    Collection<Observer> all = new ArrayList<>(observers);
    all.add(storer);
    return new Manager(board, all);
  }

  private Board loadBoard(Store store, Handler handler) {
    try {
      return store.board();
    } catch (StoreException exception) {
      handler.handle(exception);
    }
    return new Board();
  }

}
