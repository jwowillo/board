package board.observer;

import board.Board;
import board.Note;
import board.Observer;
import board.Topic;
import board.View;

import java.util.Set;
import java.util.TreeSet;

/** StoreObserver stores observed changes and defers Exceptions to a Handler. */
public class StoreObserver implements Observer {

  /** store to use. */
  private final Store store;

  /** handler of Exceptions. */
  private final Handler handler;

  /**
   * StoreObserver which stores observed changes in the Store and defers
   * Exceptions to the Handler.
   */
  public StoreObserver(Store store, Handler handler) {
    this.store = store;
    this.handler = handler;
  }

  /** observe stores the View. */
  @Override
  public void observe(View view) {
    try {
      Board board = store.board();
      Set<Topic> toAdd = new TreeSet<>(view.topics());
      toAdd.removeAll(new TreeSet<>(board.topics()));
      Set<Topic> toRemove = new TreeSet<>(board.topics());
      toRemove.removeAll(new TreeSet<>(view.topics()));
      for (Topic topic : toAdd) {
        store.addTopic(topic);
      }
      for (Topic topic : toRemove) {
        store.removeTopic(topic);
      }
      for (Topic topic : view.topics()) {
        Set<Note> toAddNotes = new TreeSet<>(view.notes(topic));
        toAddNotes.removeAll(new TreeSet<>(board.notes(topic)));
        Set<Note> toRemoveNotes = new TreeSet<>(board.notes(topic));
        toRemoveNotes.removeAll(new TreeSet<>(view.notes(topic)));
        for (Note note : toAddNotes) {
          store.addNote(topic, note);
        }
        for (Note note : toRemoveNotes) {
          store.removeNote(topic, note);
        }
      }
    } catch (StoreException exception) {
      handler.handle(exception);
    }
  }

}
