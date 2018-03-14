package board.cli;

import board.Note;
import board.Observer;
import board.Topic;
import board.View;

class CliObserver implements Observer {

  public void observe(View view) {
    System.out.println("Board:");
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
