package board.cli;

import board.app.App;
import board.app.Displayer;
import board.app.Prompter;
import board.app.StoreSupplier;
import board.observer.Handler;
import board.store.DbStore;

public class Main {

  private static final Prompter prompter = new CliPrompter();

  private static final Displayer displayer = new CliDisplayer();

  private static final Handler handler = new StderrHandler();

  private static final StoreSupplier supplier = () -> new DbStore("board.db");

  /** main runs the CLI. */
  public static void main(String[] args) {
    new App(prompter, displayer, handler, supplier).run();
  }

}

