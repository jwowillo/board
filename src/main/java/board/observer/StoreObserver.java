package board.observer;

import board.Board;
import board.Note;
import board.Observer;
import board.Topic;
import board.View;

import java.util.LinkedHashSet;
import java.util.Set;
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
    new Thread(() -> {
      try {
        Board board = store.board();
        var newTopics = new LinkedHashSet<Topic>(view.topics());
        var oldTopics = new LinkedHashSet<Topic>(board.topics());
        for (var topic : difference(newTopics, oldTopics)) {
          store.addTopic(topic);
        }
        for (var topic : difference(oldTopics, newTopics)) {
          for (var note : board.notes(topic)) {
            store.removeNote(topic, note);
          }
          store.removeTopic(topic);
        }
        for (var topic : view.topics()) {
          var newNotes = new LinkedHashSet<Note>(view.notes(topic));
          var oldNotes = new LinkedHashSet<Note>(board.notes(topic));
          for (var note : difference(newNotes, oldNotes)) {
            store.addNote(topic, note);
          }
          for (var note : difference(oldNotes, newNotes)) {
            store.removeNote(topic, note);
          }
        }
      } catch (Exception exception) {
        handler.handle(exception);
      }
    }).start();
  }

  private <T> Set<T> difference(Set<T> big, Set<T> small) {
    return big.stream()
      .filter(x -> !small.contains(x))
      .collect(Collectors.toCollection(LinkedHashSet::new));
  }

}
