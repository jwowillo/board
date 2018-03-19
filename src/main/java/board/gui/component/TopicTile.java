package board.gui.component;

import board.Manager;
import board.Note;
import board.Topic;
import board.observer.Handler;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class TopicTile extends VBox {

  public TopicTile(Manager manager, Handler handler,
      Topic topic, List<Note> notes) {
    getChildren().add(new TopicRow(manager, handler, topic));
    for (Note note : notes) {
      getChildren().add(new NoteRow(manager, handler, topic, note));
    }
  }

}
