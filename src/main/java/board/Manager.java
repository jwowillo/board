package board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Manager mediates Board interactions and notifies all Observers of changes.
 */
public class Manager {

  /** board being managed. */
  private final Board board;

  /** observers of the Board. */
  private final Collection<Observer> observers;

  /** filters of Notes. */
  private final Set<Filter> filters;

  /** Manager of the Board which is observed by the Collection of Observers. */
  public Manager(Board board, Collection<Observer> observers) {
    this.filters = new LinkedHashSet<>();
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

  /** notifyObservers notifies the Observers. */
  private void notifyObservers() {
    View view = new View(board, new ArrayList<>(filters));
    for (Observer observer : observers) {
      observer.observe(view);
    }
  }

}
