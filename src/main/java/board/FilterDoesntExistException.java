package board;

/** FilterDoesntExistException is thrown when a Filter expected to exist doesn't. */
public class FilterDoesntExistException extends FilterException {

  public FilterDoesntExistException() {
    super("note doesn't exist");
  }

}
