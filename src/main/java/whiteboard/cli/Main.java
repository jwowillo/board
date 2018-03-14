package board.cli;

import board.Manager;
import board.Observer;
import board.observer.Handler;
import board.observer.Store;
import board.observer.StoringManagerFactory;
import board.store.DbStore;

import java.util.Arrays;

public class Main {

  private static final Store store = new DbStore("board.db");

  private static final Handler handler = new StderrHandler();

  private static final Observer outputter = new CliObserver();

  /** main runs the CLI. */
  public static void main(String[] args) {
    StoringManagerFactory factory = new StoringManagerFactory();
    Manager manager = factory.build(store, handler, Arrays.asList(outputter));
    new CliPrompter(manager, handler).prompt();
  }

}

