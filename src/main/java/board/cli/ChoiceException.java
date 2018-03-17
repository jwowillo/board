package board.cli;

/** ChoiceException is thrown when an invalid choice is made. */
public class ChoiceException extends Exception {

  /** ChoiceException initializes Exception with a fixed message. */
  public ChoiceException() {
    super("invalid choice");
  }

}
