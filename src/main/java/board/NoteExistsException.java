package board;

/** NoteExistsException is thrown when a Note expected to not exist does. */
public class NoteExistsException extends BoardException {

  public NoteExistsException() {
    super("note exists");
  }

}
