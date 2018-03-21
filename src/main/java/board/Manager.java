package board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Manager mediates Board interactions and notifies all Observers of changes.
 *
 * <p>If an Observer takes long to observe, it is recommended to have it observe
 * in a separate thread.
 */
public class Manager {

  private final Set<Filter> filters = new LinkedHashSet<>();

  private final Board board;

  private final Collection<Observer> observers;

  public Manager(Board board) {
    this(board, new ArrayList<>());
  }

  /** Manager of the Board which is observed by the Collection of Observers. */
  public Manager(Board board, Collection<Observer> observers) {
    this.board = board;
    this.observers = observers;
    notifyObservers();
  }

  /**
   * addTopic to the Board.
   *
   * @throws BoardException If the Board throws a BoardException.
   */
  public void addTopic(Topic topic) throws BoardException {
    board.addTopic(topic);
    notifyObservers();
  }

  /**
   * addNote to the Topic on the Board.
   *
   * @throws BoardException If the Board throws a BoardException.
   */
  public void addNote(Topic topic, Note note) throws BoardException {
    board.addNote(topic, note);
    notifyObservers();
  }

  /**
   * removeTopic and all attached Notes from the Board.
   *
   * @throws BoardException If the Board throws a BoardException.
   */
  public void removeTopic(Topic topic) throws BoardException {
    board.removeTopic(topic);
    notifyObservers();
  }

  /**
   * removeNote from the Topic on the Board.
   *
   * @throws BoardException If the Board throws a BoardException.
   */
  public void removeNote(Topic topic, Note note) throws BoardException {
    board.removeNote(topic, note);
    notifyObservers();
  }

  /**
   * addFilter to the returned Notes.
   *
   * @throws FilterException If the Filter exists.
   */
  public void addFilter(Filter filter) throws FilterException {
    if (filters.contains(filter)) {
      throw new FilterExistsException();
    }
    filters.add(filter);
    notifyObservers();
  }

  /**
   * removeFilter from the returned Notes.
   *
   * @throws FilterException If the Filter doesn't exist.
   */
  public void removeFilter(Filter filter) throws FilterException {
    if (!filters.contains(filter)) {
      throw new FilterDoesntExistException();
    }
    filters.remove(filter);
    notifyObservers();
  }

  private void notifyObservers() {
    var view = new View(board, new ArrayList<>(filters));
    for (var observer : observers) {
      observer.observe(view);
    }
  }

}
