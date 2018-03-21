package board.gui.component;

import board.Manager;
import board.Topic;
import board.observer.Handler;

/**
 * FilterComponent displays a topic and a button to remove the Topic and
 * displays errors to the Handler if the Topic couldn't be removed.
 */
public class TopicComponent extends RemoveComponent {

  /**
   * TopicComponent initializes RemoveComponent with a Runnable that removes the
   * Topic from the Manager and handles errors with the Handler.
   */
  public TopicComponent(Manager manager, Handler handler, Topic topic) {
    super(topic.name(), () -> {
      try {
        manager.removeTopic(topic);
      } catch (Exception exception) {
        handler.handle(exception);
      }
    });
  }

}
