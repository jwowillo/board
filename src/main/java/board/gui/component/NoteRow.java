package board.gui.component;

import board.Manager;
import board.Note;
import board.Topic;
import board.observer.Handler;

public class NoteRow extends RemoveRow {

  public NoteRow(Manager manager, Handler handler, Topic topic, Note note) {
    super(note.content(), () -> {
      try {
        manager.removeNote(topic, note);
      } catch (Exception exception) {
        handler.handle(exception);
      }
    });
  }

}
