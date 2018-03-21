package board;

/** FilterExistsException is thrown when a Filter expected to not exist does. */
public class FilterExistsException extends FilterException {

  public FilterExistsException() {
    super("filter exists");
  }

}
