package board.gui;

import board.Manager;
import board.app.Prompter;
import board.observer.Handler;
import board.gui.component.ViewComponent;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiPrompter implements Prompter {

  private final ViewComponent view;

  public GuiPrompter(Handler handler) {
    this.view = new ViewComponent(handler);
  }

  public ViewComponent view() {
    return view;
  }

  @Override
  public void prompt(Manager manager) {
    Stage stage = new Stage();
    view.setManager(manager);
    stage.setScene(new Scene(view));
    stage.showAndWait();
  }

}
