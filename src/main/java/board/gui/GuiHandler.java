package board.gui;

import board.observer.Handler;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GuiHandler implements Handler {

  @Override
  public void handle(Exception exception) {
    Stage stage = new Stage();
    stage.setTitle("board Error");
    stage.initStyle(StageStyle.UTILITY);
    Label label = new Label("Error: " + exception.getMessage());
    StackPane root = new StackPane();
    root.getChildren().add(label);
    stage.setScene(new Scene(root, 250, 100));
    stage.showAndWait();
  }

}
