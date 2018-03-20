package board.gui.component;

import board.Manager;
import board.Note;
import board.Topic;
import board.observer.Handler;

public class AddNoteRow extends AddRow {

  public AddNoteRow(Manager manager, Handler handler, Topic topic) {
    super((t) -> {
      try {
        manager.addNote(topic, new Note(t));
      } catch (Exception exception) {
        handler.handle(exception);
      }
    });
  }

}
