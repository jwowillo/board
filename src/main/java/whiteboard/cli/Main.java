package whiteboard.cli;

import whiteboard.Manager;
import whiteboard.Observer;
import whiteboard.Whiteboard;
import whiteboard.observer.Handler;
import whiteboard.observer.Store;
import whiteboard.observer.StoreException;
import whiteboard.observer.StoreObserver;
import whiteboard.store.FileStore;

import java.util.Arrays;
import java.util.Collection;

public class Main {

  /** main loads the Whiteboard and runs the CLI. */
  public static void main(String[] args) {
    Store store = new FileStore("whiteboard.db");
    Handler handler = new StderrHandler();
    Whiteboard board = loadWhiteboard(store, handler);
    Collection<Observer> observers = Arrays.asList(
        new CliObserver(), new StoreObserver(store, handler));
    Manager manager = new Manager(board, observers);
    CliPrompter prompter = new CliPrompter(manager, handler);
    prompter.prompt();
  }

  private static Whiteboard loadWhiteboard(Store store, Handler handler) {
    try {
      return store.whiteboard();
    } catch (StoreException exception) {
      handler.handle(exception);
      System.exit(1);
    }
    return null;
  }

}

