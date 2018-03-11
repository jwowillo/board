package whiteboard;

import whiteboard.WhiteboardException;

/**
 * TopicDoesntExistException is thrown when a Topic expected to exist doesn't.
 */
public class TopicDoesntExistException extends WhiteboardException {

  /**
   * TopicDoesntExistException initializes WhiteboardException with a fixed
   * message.
   */
  public TopicDoesntExistException() {
    super("topic doesn't exist");
  }

}
