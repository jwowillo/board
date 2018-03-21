package board.gui.component;

import board.Manager;
import board.Topic;
import board.observer.Handler;

/**
 * AddTopicComponent adds entered Topics to a Manager and displays errors to a
 * Handler if the Note couldn't be added.
 */
public class AddTopicComponent extends AddComponent {

  /**
   * AddTopicComponent initializes AddTopicComponent with a Consumer that adds
   * the Topic to the Manager with the text and handles errors with the Handler.
   */
  public AddTopicComponent(Manager manager, Handler handler) {
    super(t -> {
      try {
        manager.addTopic(new Topic(t));
      } catch (Exception exception) {
        handler.handle(exception);
      }
    });
  }

}
