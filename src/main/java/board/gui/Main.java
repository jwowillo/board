package board.gui;

import board.app.App;
import board.app.Displayer;
import board.app.Prompter;
import board.app.StoreSupplier;
import board.observer.Handler;
import board.observer.Store;
import board.store.DbStore;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    Handler handler = new GuiHandler();
    GuiPrompter prompter = new GuiPrompter(handler);
    Displayer displayer = new GuiDisplayer(prompter.display());
    StoreSupplier supplier = () -> new DbStore("board.db");
    new App(prompter, displayer, handler, supplier).run();
  }

}
