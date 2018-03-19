package board.cli;

import board.app.App;
import board.app.Displayer;
import board.app.Prompter;
import board.app.StoreSupplier;
import board.observer.Handler;
import board.observer.Store;
import board.store.DbStore;

/** Main runs the CLI. */
public class Main {

  /** main runs the CLI. */
  public static void main(String[] args) {
    Handler handler = new StderrHandler();
    Prompter prompter = new CliPrompter(handler);
    Displayer displayer = new CliDisplayer();
    StoreSupplier supplier = () -> new DbStore("board.db");
    new App(prompter, displayer, handler, supplier).run();
  }

}

