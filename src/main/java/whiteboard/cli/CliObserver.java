package whiteboard.cli;

import whiteboard.Note;
import whiteboard.Observer;
import whiteboard.Topic;
import whiteboard.View;

class CliObserver implements Observer {

  public void observe(View view) {
    System.out.println("Whiteboard:");
    System.out.println();
    for (Topic topic : view.topics()) {
      System.out.println(topic.name());
      System.out.println("----------------");
      for (Note note : view.notes(topic)) {
        System.out.println(note.content());
      }
      System.out.println();
    }
  }

}
