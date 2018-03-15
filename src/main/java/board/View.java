package board;

import java.util.HashSet;
import java.util.Set;

/** View is an immutable Board. */
public class View {

  /** board being viewed. */
  private final Board board;

  private final Set<Filter> filters;

  public View(Board board) {
    this(board, new HashSet<>());
  }

  /** View of the Board. */
  public View(Board board, Set<Filter> filters) {
    this.board = board;
    this.filters = filters;
  }

  public Set<Note> unfilteredNotes(Topic topic) {
    return board.notes(topic);
  }

  /** topics from the View's Board. */
  public Set<Topic> topics() {
    return board.topics();
  }

  /** notes from the View's Board. */
  public Set<Note> notes(Topic topic) {
    Set<Note> notes = new HashSet<>();
    for (Note note : unfilteredNotes(topic)) {
      boolean shouldInclude = true;
      for (Filter filter : filters) {
        if (!note.content().contains(filter.term())) {
          shouldInclude = false;
        }
      }
      if (!shouldInclude) {
        continue;
      }
      notes.add(note);
    }
    return notes;
  }

  public Set<Filter> filters() {
    return filters;
  }

}
