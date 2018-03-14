package board;

/** TopicExistsException is thrown when a Topic expected to not exist does. */
public class TopicExistsException extends BoardException {

  /** TopicExistsException initializes BoardException with a fixed message. */
  public TopicExistsException() {
    super("topic exists");
  }

}
