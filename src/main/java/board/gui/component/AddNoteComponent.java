package board.gui.component;

import board.Manager;
import board.Note;
import board.Topic;
import board.observer.Handler;

/**
 * AddNoteComponent adds entered Notes to a Manager and displays errors to a
 * Handler if the Note couldn't be added.
 */
public class AddNoteComponent extends AddComponent {

  /**
   * AddNoteComponent initializes AddComponent with a Consumer that adds the
   * Note to the Manager with the text and handles errors with the Handler.
   */
  public AddNoteComponent(Manager manager, Handler handler, Topic topic) {
    super(t -> {
      try {
        manager.addNote(topic, new Note(t));
      } catch (Exception exception) {
        handler.handle(exception);
      }
    });
  }

}
