package whiteboard.cli;

import whiteboard.observer.Handler;

class StderrHandler implements Handler {

  @Override
  public void handle(Exception exception) {
    System.err.println("Error: " + exception.getMessage());
  }

}
