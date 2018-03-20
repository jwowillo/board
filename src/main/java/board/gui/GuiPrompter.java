package board.gui;

import board.Board;
import board.Manager;
import board.View;
import board.app.Prompter;
import board.observer.Handler;
import board.gui.component.ViewComponent;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiPrompter implements Prompter {

  private final Stage stage;

  private final Handler handler;

  private Manager manager;

  private View view;

  public GuiPrompter(Handler handler) {
    this.stage = new Stage();
    this.handler = handler;
  }

  public void updateView(View view) {
    this.view = view;
    if (manager == null) {
      return;
    }
    stage.setScene(new Scene(new ViewComponent(manager, view, handler)));
  }

  @Override
  public void prompt(Manager manager) {
    this.manager = manager;
    updateView(view);
    stage.showAndWait();
  }

}
