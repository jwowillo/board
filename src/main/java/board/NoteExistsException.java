package board;

/** NoteExistsException is thrown when a Note expected to not exist does. */
public class NoteExistsException extends BoardException {

  /** NoteExistsException initializes BoardException with a fixed message. */
  public NoteExistsException() {
    super("note exists");
  }

}
