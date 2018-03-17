package board.cli;

import board.observer.Handler;

/** StderrHandler prints Exceptions to stderr. */
class StderrHandler implements Handler {

  /** handle the Exception by printing it to stderr. */
  @Override
  public void handle(Exception exception) {
    System.err.println("Error: " + exception.getMessage());
    System.out.println();
  }

}
