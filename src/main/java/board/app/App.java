package board.app;

import board.Board;
import board.Manager;
import board.Observer;
import board.View;
import board.observer.Handler;
import board.observer.Store;
import board.observer.StoreException;
import board.observer.StoreObserver;

import java.util.ArrayList;
import java.util.Collection;

public class App {

  private final Prompter prompter;

  private final Manager manager;

  private final Handler handler;

  public App(Prompter prompter, Displayer displayer, Store store,
      Handler handler) {
    this(prompter, displayer, store, handler, new ArrayList<>());
  }

  public App(Prompter prompter, Displayer displayer, Store store,
      Handler handler, Collection<Observer> observers) {
    Collection<Observer> all = new ArrayList<>(observers);
    all.add(new StoreObserver(store, handler));
    all.add((View view) -> displayer.display(view));
    Board board = loadBoard(store, handler);
    this.prompter = prompter;
    this.manager = new Manager(board, all);
    this.handler = handler;
  }

  public void run() {
    while (true) {
      try {
        prompter.prompt(manager);
      } catch (Exception exception) {
        handler.handle(exception);
      }
    }
  }

  private static Board loadBoard(Store store, Handler handler) {
    try {
      return store.board();
    } catch (StoreException exception) {
      handler.handle(exception);
    }
    return new Board();
  }

}
