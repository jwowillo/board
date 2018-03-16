package board.observer;

import board.Board;
import board.Note;
import board.Topic;

/** Store supports updating and retrieving Boards. */
public interface Store extends AutoCloseable {

  void addTopic(Topic topic) throws Exception;

  void removeTopic(Topic topic) throws Exception;

  void addNote(Topic topic, Note note) throws Exception;

  void removeNote(Topic topic, Note note) throws Exception;

  /**
   * Board in the Store or an empty Board if it doesn't exist.
   *
   * @throws Exception If the Board couldn't be retrieved.
   */
  Board board() throws Exception;

}
