package board.cli;

import board.Filter;
import board.Note;
import board.Topic;
import board.View;
import board.app.Displayer;

class CliDisplayer implements Displayer {

  @Override
  public void display(View view) {
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
    System.out.println("Filters:");
    for (Filter filter : view.filters()) {
      System.out.println(filter.term());
    }
    System.out.println();
  }

}
