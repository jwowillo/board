package board.gui;

import board.Manager;
import board.app.Prompter;
import board.observer.Handler;
import board.gui.component.Display;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiPrompter implements Prompter {

  private final Display display;

  public GuiPrompter(Handler handler) {
    this.display = new Display(handler);
  }

  public Display display() {
    return display;
  }

  @Override
  public void prompt(Manager manager) {
    Stage stage = new Stage();
    display.setManager(manager);
    stage.setScene(new Scene(display));
    stage.showAndWait();
  }

}
