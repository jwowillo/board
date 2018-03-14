package board;

import java.util.Collection;

/**
 * Manager mediates Board interactions.
 *
 * <p>Changes to the Board cause all Observers to be notified by the Manager.
 */
public class Manager {

  /** board being managed. */
  private final Board board;

  /** observers of the board. */
  private final Collection<Observer> observers;

  /** Manager of Board which is observed by the Collection of Observers. */
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
   * removeNote from the Board.
   *
   * @throws BoardException If the Board throws a BoardException.
   */
  public void removeNote(Topic topic, Note note) throws BoardException {
    board.removeNote(topic, note);
    notifyObservers();
  }

  /** notifyObservers notifies the Observers. */
  private void notifyObservers() {
    View view = new View(board);
    for (Observer observer : observers) {
      observer.observe(view);
    }
  }

}
