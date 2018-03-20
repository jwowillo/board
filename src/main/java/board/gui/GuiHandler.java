package board.gui;

import board.observer.Handler;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GuiHandler implements Handler {

  @Override
  public void handle(Exception exception) {
    Platform.runLater(() -> {
      Stage stage = new Stage();
      stage.setTitle("board Error");
      stage.initStyle(StageStyle.UTILITY);
      Text text = new Text("Error: " + exception.getMessage());
      StackPane root = new StackPane();
      root.getChildren().add(text);
      stage.setScene(new Scene(root, 250, 100));
      stage.showAndWait();
    });
  }

}