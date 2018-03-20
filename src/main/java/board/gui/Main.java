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
    var handler = new GuiHandler();
    var gui = new Gui(handler);
    StoreSupplier supplier = () -> new DbStore("board.db");
    new App(gui, gui, handler, supplier).run();
  }

}
