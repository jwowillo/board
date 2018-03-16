package board.observer;

import board.Board;
import board.Note;
import board.Observer;
import board.Topic;
import board.View;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

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
      Set<Topic> newTopics = new LinkedHashSet<>(view.topics());
      Set<Topic> oldTopics = new LinkedHashSet<>(board.topics());
      for (Topic topic : difference(newTopics, oldTopics)) {
        store.addTopic(topic);
      }
      for (Topic topic : difference(oldTopics, newTopics)) {
        store.removeTopic(topic);
      }
      for (Topic topic : view.topics()) {
        Set<Note> newNotes = new LinkedHashSet<>(view.notes(topic));
        Set<Note> oldNotes = new LinkedHashSet<>(board.notes(topic));
        for (Note note : difference(newNotes, oldNotes)) {
          store.addNote(topic, note);
        }
        for (Note note : difference(oldNotes, newNotes)) {
          store.removeNote(topic, note);
        }
      }
    } catch (Exception exception) {
      handler.handle(exception);
    }
  }

  private <T> Set<T> difference(Set<T> big, Set<T> small) {
    return big.stream()
      .filter(x -> !small.contains(x))
      .collect(Collectors.toCollection(LinkedHashSet::new));
  }

}
