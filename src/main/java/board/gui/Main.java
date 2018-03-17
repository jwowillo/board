package board.gui;

import board.app.App;
import board.app.Displayer;
import board.app.Prompter;
import board.app.StoreSupplier;
import board.cli.CliDisplayer;
import board.cli.CliPrompter;
import board.observer.Handler;
import board.store.DbStore;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    Prompter prompter = new CliPrompter();
    Displayer displayer = new CliDisplayer();
    Handler handler = new GuiHandler();
    StoreSupplier supplier = () -> new DbStore("board.db");
    new App(prompter, displayer, handler, supplier).run();
  }

}
