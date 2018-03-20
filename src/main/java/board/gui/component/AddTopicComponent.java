package board.gui.component;

import board.Manager;
import board.Topic;
import board.observer.Handler;

public class AddTopicComponent extends AddComponent {

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
