package board.gui.component;

import board.Manager;
import board.Note;
import board.Topic;
import board.observer.Handler;

import javafx.scene.text.Text;

public class NoteRow extends Row {

  public NoteRow(Manager manager, Handler handler, Topic topic, Note note) {
    super(new Text(note.content()), "X", () -> {
      try {
        manager.removeNote(topic, note);
      } catch (Exception exception) {
        handler.handle(exception);
      }
    });
  }

}
