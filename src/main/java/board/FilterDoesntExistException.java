package board;

/** FilterDoesntExistException is thrown when a Filter expected to exist doesn't. */
public class FilterDoesntExistException extends FilterException {

  /**
   * FilterDoesntExistException initializes FilterException with a fixed
   * message.
   */
  public FilterDoesntExistException() {
    super("note doesn't exist");
  }

}
