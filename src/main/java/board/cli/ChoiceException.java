package board.cli;

/** ChoiceException is thrown when an invalid choice is made. */
public class ChoiceException extends Exception {

  public ChoiceException() {
    super("invalid choice");
  }

}
