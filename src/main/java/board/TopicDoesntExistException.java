package board;

/**
 * TopicDoesntExistException is thrown when a Topic expected to exist doesn't.
 */
public class TopicDoesntExistException extends BoardException {

  public TopicDoesntExistException() {
    super("topic doesn't exist");
  }

}
