package board;

/**
 * TopicDoesntExistException is thrown when a Topic expected to exist doesn't.
 */
public class TopicDoesntExistException extends BoardException {

  /**
   * TopicDoesntExistException initializes BoardException with a fixed message.
   */
  public TopicDoesntExistException() {
    super("topic doesn't exist");
  }

}
