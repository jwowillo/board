package board.gui.component;

import board.Manager;
import board.Note;
import board.Topic;
import board.observer.Handler;

/**
 * NoteComponent displays a Note and a button to remove the Note and displays
 * errors to the Handler if the Note couldn't be removed.
 */
public class NoteComponent extends RemoveComponent {

  /**
   * NoteComponent initializes RemoveComponent with a Runnable that removes the
   * Note from the Topic in Manager and handles errors with the Handler.
   */
  public NoteComponent(Manager manager, Handler handler,
      Topic topic, Note note) {
    super(note.content(), () -> {
      try {
        manager.removeNote(topic, note);
      } catch (Exception exception) {
        handler.handle(exception);
      }
    });
  }

}
