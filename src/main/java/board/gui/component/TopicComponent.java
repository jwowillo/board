package board.gui.component;

import board.Manager;
import board.Topic;
import board.observer.Handler;

public class TopicComponent extends RemoveComponent {

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
