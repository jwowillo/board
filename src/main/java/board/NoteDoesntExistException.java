package board;

/** NoteDoesntExistException is thrown when a Note expected to exist doesn't. */
public class NoteDoesntExistException extends BoardException {

  /**
   * NoteDoesntExistException initializes BoardException with a fixed message.
   */
  public NoteDoesntExistException() {
    super("note doesn't exist");
  }

}
