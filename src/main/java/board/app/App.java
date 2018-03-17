package board.app;

import board.Board;
import board.Manager;
import board.Observer;
import board.observer.Handler;
import board.observer.Store;
import board.observer.StoreObserver;

import java.util.ArrayList;
import java.util.Collection;

/**
 * App where users are prompted by a Prompter and have results displayed by a
 * Displayer.
 *
 * <p>A Handler handles exceptions and a StoreSupplier supplies the Store which
 * determines the initial Board and saves other Boards.
 *
 * <p>Other Observers can also be included.
 */
public class App {

  /** Prompter of user interactions. */
  private final Prompter prompter;

  /** Displayer of Views. */
  private final Displayer displayer;

  /** Handler of Exceptions. */
  private final Handler handler;

  /** StoreSupplier supplies the Store. */
  private final StoreSupplier supplier;

  /** Observers of changes. */
  private final Collection<Observer> observers;

  /** App with Prompter, Displayer, Handler, and StoreSupplier set. */
  public App(Prompter prompter, Displayer displayer, Handler handler,
      StoreSupplier supplier) {
    this(prompter, displayer, handler, supplier, new ArrayList<>());
  }

  /**
   * App with Prompter, Displayer, Handler, and StoreSupplier set and additional
   * Observers observing.
   */
  public App(Prompter prompter, Displayer displayer, Handler handler,
      StoreSupplier supplier, Collection<Observer> observers) {
    this.prompter = prompter;
    this.displayer = displayer;
    this.handler = handler;
    this.supplier = supplier;
    this.observers = observers;
  }

  /** run the App. */
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

  /** loadBoard from the Store and handle errors with the Handler. */
  private static Board loadBoard(Store store, Handler handler) {
    try {
      return store.board();
    } catch (Exception exception) {
      handler.handle(exception);
    }
    return new Board();
  }

}
