package board.cli;

import board.observer.Handler;

/** StderrHandler prints Exceptions to stderr. */
public class StderrHandler implements Handler {

  @Override
  public void handle(Exception exception) {
    System.err.println("Error: " + exception.getMessage());
    System.out.println();
  }

}
