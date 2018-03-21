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
 * App that prompts with a Prompter and has results displayed by a Displayer.
 *
 * <p>A Handler handles exceptions and a StoreSupplier supplies the Store which
 * determines the initial Board and saves other Boards. Other Observers can also
 * be included.
 */
public class App {

  private final Prompter prompter;

  private final Displayer displayer;

  private final Handler handler;

  private final StoreSupplier supplier;

  private final Collection<Observer> observers;

  /** App with necessary components. */
  public App(Prompter prompter, Displayer displayer, Handler handler,
      StoreSupplier supplier) {
    this(prompter, displayer, handler, supplier, new ArrayList<>());
  }

  /** App with additional Observers. */
  public App(Prompter prompter, Displayer displayer, Handler handler,
        StoreSupplier supplier, Collection<Observer> observers) {
    this.prompter = prompter;
    this.displayer = displayer;
    this.supplier = supplier;
    this.handler = handler;
    this.observers = observers;
  }

  /** run the App. */
  public void run() {
    try (Store store = supplier.supply()) {
      var all = new ArrayList<Observer>(observers);
      all.add(new StoreObserver(store, handler));
      all.add((view) -> displayer.display(view));
      var board = loadBoard(store, handler);
      var manager = new Manager(board, all);
      try {
        prompter.prompt(manager);
      } catch (Exception exception) {
        handler.handle(exception);
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
