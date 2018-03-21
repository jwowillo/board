package board.cli;

import board.View;
import board.app.Displayer;

/** CliDisplayer displays Views to the command-line. */
public class CliDisplayer implements Displayer {

  @Override
  public void display(View view) {
    System.out.println("Board:");
    System.out.println();
    for (var topic : view.topics()) {
      System.out.println(topic.name());
      System.out.println("----------------");
      for (var note : view.notes(topic)) {
        System.out.println(note.content());
      }
      System.out.println();
    }
    System.out.println("Filters:");
    for (var filter : view.filters()) {
      System.out.println(filter.term());
    }
    System.out.println();
  }

}
