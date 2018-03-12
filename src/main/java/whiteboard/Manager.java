package whiteboard;

import java.util.Collection;

/**
 * Manager mediates Whiteboard interactions.
 *
 * <p>Changes to the Whiteboard cause all Observers to be notified by the
 * Manager.
 */
public class Manager {

  /** board being managed. */
  private final Whiteboard board;

  /** observers of the board. */
  private final Collection<Observer> observers;

  /** Manager of Whiteboard which is observed by the Collection of Observers. */
  public Manager(Whiteboard board, Collection<Observer> observers) {
    this.board = board;
    this.observers = observers;
    notifyObservers();
  }

  /**
   * addTopic to the Whiteboard.
   *
   * @throws WhiteboardException If the Whiteboard throws a WhiteboardException.
   */
  public void addTopic(Topic topic) throws WhiteboardException {
    board.addTopic(topic);
    notifyObservers();
  }

  /**
   * addNote to the Topic on the Whiteboard.
   *
   * @throws WhiteboardException If the Whiteboard throws a WhiteboardException.
   */
  public void addNote(Topic topic, Note note) throws WhiteboardException {
    board.addNote(topic, note);
    notifyObservers();
  }

  /**
   * removeTopic and all attached Notes from the Whiteboard.
   *
   * @throws WhiteboardException If the Whiteboard throws a WhiteboardException.
   */
  public void removeTopic(Topic topic) throws WhiteboardException {
    board.removeTopic(topic);
    notifyObservers();
  }

  /**
   * removeNote from the Whiteboard.
   *
   * @throws WhiteboardException If the Whiteboard throws a WhiteboardException.
   */
  public void removeNote(Topic topic, Note note) throws WhiteboardException {
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
