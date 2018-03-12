package whiteboard;

/** NoteExistsException is thrown when a Note expected to not exist does. */
public class NoteExistsException extends WhiteboardException {

  /**
   * NoteExistsException initializes WhiteboardException with a fixed message.
   */
  public NoteExistsException() {
    super("note exists");
  }

}
