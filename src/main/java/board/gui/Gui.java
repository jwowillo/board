package board.gui;

import board.Manager;
import board.View;
import board.app.Displayer;
import board.app.Prompter;
import board.gui.component.ViewComponent;
import board.observer.Handler;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Gui is a combined Prompter and Displayer that reports errors on the provided
 * Handler.
 */
public class Gui implements Prompter, Displayer {

  private final Stage stage = new Stage();

  private final Handler handler;

  private Manager manager;

  private View view;

  public Gui(Handler handler) {
    this.handler = handler;
  }

  @Override
  public void prompt(Manager manager) {
    this.manager = manager;
    updateView(view);
    stage.showAndWait();
  }

  @Override
  public void display(View view) {
    updateView(view);
  }

  private void updateView(View view) {
    this.view = view;
    if (manager == null) {
      return;
    }
    stage.setScene(new Scene(new ViewComponent(manager, view, handler)));
  }

}
