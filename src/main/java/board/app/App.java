package board.app;

import board.Board;
import board.Manager;
import board.Observer;
import board.View;
import board.observer.Handler;
import board.observer.Store;
import board.observer.StoreObserver;

import java.util.ArrayList;
import java.util.Collection;

public class App {

  private final Prompter prompter;

  private final Displayer displayer;

  private final Handler handler;

  private final StoreSupplier supplier;

  private final Collection<Observer> observers;

  public App(Prompter prompter, Displayer displayer, Handler handler,
      StoreSupplier supplier) {
    this(prompter, displayer, handler, supplier, new ArrayList<>());
  }

  public App(Prompter prompter, Displayer displayer, Handler handler,
      StoreSupplier supplier, Collection<Observer> observers) {
    this.prompter = prompter;
    this.displayer = displayer;
    this.handler = handler;
    this.supplier = supplier;
    this.observers = observers;
  }

  public void run() {
    try (Store store = supplier.supply()) {
      Collection<Observer> all = new ArrayList<>(observers);
      all.add(new StoreObserver(store, handler));
      all.add((view) -> displayer.display(view));
      Board board = loadBoard(store, handler);
      Manager manager = new Manager(board, all);
      boolean shouldPrompt = true;
      while (shouldPrompt) {
        try {
          shouldPrompt = prompter.prompt(manager);
        } catch (Exception exception) {
          handler.handle(exception);
        }
      }
    } catch (Exception exception) {
      handler.handle(exception);
    }
  }

  private static Board loadBoard(Store store, Handler handler) {
    try {
      return store.board();
    } catch (Exception exception) {
      handler.handle(exception);
    }
    return new Board();
  }

}
