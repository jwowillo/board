package whiteboard;

/**
 * WhiteboardException is thrown if a bad operation is performed on a
 * Whiteboard.
 */
public class WhiteboardException extends Exception {

  /** WhiteboardException initializes Exception with a fixed message. */
  public WhiteboardException(String msg) {
    super(msg);
  }

}
