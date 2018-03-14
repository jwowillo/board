package board;

/** BoardException is thrown if a bad operation is performed on a Board. */
public class BoardException extends Exception {

  /** BoardException initializes Exception with a fixed message. */
  public BoardException(String msg) {
    super(msg);
  }

}
