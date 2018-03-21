package board.observer;

import board.Board;
import board.Note;
import board.Topic;

/** Store supports updating and retrieving Boards. */
public interface Store extends AutoCloseable {

  /**
   * addTopic to the Board.
   *
   * @throws Exception If the Topic couldn't be added.
   */
  void addTopic(Topic topic) throws Exception;

  /**
   * removeTopic from the Board.
   *
   * @throws Exception if the Topic couldn't be removed.
   */
  void removeTopic(Topic topic) throws Exception;

  /**
   * addNote to the Board.
   *
   * @throws Exception If the Note couldn't be added to the Topic.
   */
  void addNote(Topic topic, Note note) throws Exception;

  /**
   * removeNote from the Board.
   *
   * @throws Exception If the Note couldn't be removed from the Topic.
   */
  void removeNote(Topic topic, Note note) throws Exception;

  /**
   * Board in the Store or an empty Board if it doesn't exist.
   *
   * @throws Exception If the Board couldn't be retrieved.
   */
  Board board() throws Exception;

}
