package board.cli;

import board.app.App;
import board.app.StoreSupplier;
import board.observer.Store;
import board.store.DbStore;

/** Main runs the CLI. */
public class Main {

  /** main constructs and runs the App with CLI-objects. */
  public static void main(String[] args) {
    var handler = new StderrHandler();
    var prompter = new CliPrompter(handler);
    var displayer = new CliDisplayer();
    StoreSupplier supplier = () -> new DbStore("board.db");
    new App(prompter, displayer, handler, supplier).run();
  }

}

