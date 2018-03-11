package whiteboard;

import whiteboard.WhiteboardException;

/** NoteDoesntExistException is thrown when a Note expected to exist doesn't. */
public class NoteDoesntExistException extends WhiteboardException {

  /**
   * NoteDoesntExistException initializes WhiteboardException with a fixed
   * message.
   */
  public NoteDoesntExistException() {
    super("note doesn't exist");
  }

}
