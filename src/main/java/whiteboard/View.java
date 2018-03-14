package board;

import java.util.Set;

/** View is an immutable Board. */
public class View {

  /** board being viewed. */
  private final Board board;

  /** View of the Board. */
  public View(Board board) {
    this.board = board;
  }

  /** topics from the View's Board. */
  public Set<Topic> topics() {
    return board.topics();
  }

  /** notes from the View's Board. */
  public Set<Note> notes(Topic topic) {
    return board.notes(topic);
  }

}
