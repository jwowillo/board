package board.gui.component;

import board.Manager;
import board.Topic;
import board.observer.Handler;

import javafx.scene.text.Text;

public class TopicRow extends Row {

  public TopicRow(Manager manager, Handler handler, Topic topic) {
    super(new Text(topic.name()), "X", () -> {
      try {
        manager.removeTopic(topic);
      } catch (Exception exception) {
        handler.handle(exception);
      }
    });
  }

}
