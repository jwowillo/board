package board;

/** TopicExistsException is thrown when a Topic expected to not exist does. */
public class TopicExistsException extends BoardException {

  public TopicExistsException() {
    super("topic exists");
  }

}
