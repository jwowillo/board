package whiteboard;

import java.util.Set;

/** View is an immutable Whiteboard. */
public class View {

  /** board being viewed. */
  private final Whiteboard board;

  /** View of the Whiteboard. */
  public View(Whiteboard board) {
    this.board = board;
  }

  /** topics from the View's Whiteboard. */
  public Set<Topic> topics() {
    return board.topics();
  }

  /** notes from the View's Whiteboard. */
  public Set<Note> notes(Topic topic) {
    return board.notes(topic);
  }

}
