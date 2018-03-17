package board.cli;

import board.app.App;
import board.app.Displayer;
import board.app.Prompter;
import board.app.StoreSupplier;
import board.observer.Handler;
import board.store.DbStore;

/** Main runs the CLI. */
public class Main {

  /** Prompter to use. */
  private static final Prompter prompter = new CliPrompter();

  /** Displayer to use. */
  private static final Displayer displayer = new CliDisplayer();

  /** Handler to use. */
  private static final Handler handler = new StderrHandler();

  /** StoreSupplier to use. */
  private static final StoreSupplier supplier = () -> new DbStore("board.db");

  /** main runs the CLI. */
  public static void main(String[] args) {
    new App(prompter, displayer, handler, supplier).run();
  }

}

