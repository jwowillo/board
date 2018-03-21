package board;

/** NoteDoesntExistException is thrown when a Note expected to exist doesn't. */
public class NoteDoesntExistException extends BoardException {

  public NoteDoesntExistException() {
    super("note doesn't exist");
  }

}
