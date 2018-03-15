package board.cli;

import board.app.App;
import board.app.Displayer;
import board.app.Prompter;
import board.observer.Handler;
import board.observer.Store;
import board.store.DbStore;

import java.util.Arrays;

public class Main {

  private static final Prompter prompter = new CliPrompter();

  private static final Displayer displayer = new CliDisplayer();

  private static final Store store = new DbStore("board.db");

  private static final Handler handler = new StderrHandler();

  /** main runs the CLI. */
  public static void main(String[] args) {
    new App(prompter, displayer, store, handler).run();
  }

}

