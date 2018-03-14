package board.cli;

import board.Board;
import board.Manager;
import board.Observer;
import board.observer.Handler;
import board.observer.Store;
import board.observer.StoreException;
import board.observer.StoreObserver;
import board.store.DbStore;

import java.util.Arrays;
import java.util.Collection;

public class Main {

  private static final Store store = new DbStore("board.db");

  private static final Handler handler = new StderrHandler();

  /** main loads the Board and runs the CLI. */
  public static void main(String[] args) {
    Board board = loadBoard(store, handler);
    Observer cli = new CliObserver();
    Observer storer = new StoreObserver(store, handler);
    Collection<Observer> observers = Arrays.asList(cli, storer);
    Manager manager = new Manager(board, observers);
    CliPrompter prompter = new CliPrompter(manager, handler);
    prompter.prompt();
  }

  private static Board loadBoard(Store store, Handler handler) {
    try {
      return store.board();
    } catch (StoreException exception) {
      handler.handle(exception);
      System.exit(1);
    }
    return null;
  }

}

