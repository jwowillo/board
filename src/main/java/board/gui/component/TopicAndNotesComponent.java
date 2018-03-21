package board.gui.component;

import board.Manager;
import board.Note;
import board.Topic;
import board.observer.Handler;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

/**
 * TopicAndNotesComponent displays a Topic and its Notes along with an entry to
 * add new Notes.
 */
public class TopicAndNotesComponent extends VBox {

  /**
   * TopicAndNotesComponent adds Notes to the Manager, handles errors with
   * Handler, and displays the Topic and the Notes.
   */
  public TopicAndNotesComponent(Manager manager, Handler handler, Topic topic,
      List<Note> notes) {
    getChildren().add(new TopicComponent(manager, handler, topic));
    for (Note note : notes) {
      getChildren().add(new NoteComponent(manager, handler, topic, note));
    }
    getChildren().add(new AddNoteComponent(manager, handler, topic));
  }

}
