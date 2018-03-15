package board;

/** FilterExistsException is thrown when a Filter expected to not exist does. */
public class FilterExistsException extends FilterException {

  /** FilterExistsException initializes FilterException with a fixed message. */
  public FilterExistsException() {
    super("filter exists");
  }

}
