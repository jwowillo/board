package whiteboard;

import whiteboard.WhiteboardException;

/** TopicExistsException is thrown when a Topic expected to not exist does. */
public class TopicExistsException extends WhiteboardException {

  /**
   * TopicExistsException initializes WhiteboardException with a fixed message.
   */
  public TopicExistsException() {
    super("topic exists");
  }

}
