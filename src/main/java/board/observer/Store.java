package board.observer;

import board.Board;
import board.Note;
import board.Topic;

/** Store supports updating and retrieving Boards. */
public interface Store extends AutoCloseable {

  void addTopic(Topic topic) throws StoreException;

  void removeTopic(Topic topic) throws StoreException;

  void addNote(Topic topic, Note note) throws StoreException;

  void removeNote(Topic topic, Note note) throws StoreException;

  /**
   * Board in the Store or an empty Board if it doesn't exist.
   *
   * @throws StoreException If the Board couldn't be retrieved.
   */
  Board board() throws StoreException;

}
