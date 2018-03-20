package board;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/** View is an immutable view of a Board with filtered Notes. */
public class View {

  /** board being viewed. */
  private final Board board;

  /** filters on Notes. */
  private final List<Filter> filters;

  /** View with no Filters. */
  public View(Board board) {
    this(board, new ArrayList<>());
  }

  /** View of the Board with filters on Notes. */
  public View(Board board, List<Filter> filters) {
    this.board = board;
    this.filters = filters;
  }

  /**
   * unfilteredNotes returns the Notes for the Topic without applying any
   * Filters.
   */
  public List<Note> unfilteredNotes(Topic topic) {
    return board.notes(topic);
  }

  /** topics from the View's Board. */
  public List<Topic> topics() {
    return board.topics();
  }

  /** notes from the View's Board. */
  public List<Note> notes(Topic topic) {
    var notes = new ArrayList<Note>();
    for (var note : unfilteredNotes(topic)) {
      Predicate<Filter> match = f -> note.content().contains(f.term());
      if (filters.stream().allMatch(match)) {
        notes.add(note);
      }
    }
    return notes;
  }

  /** filters applied to Notes. */
  public List<Filter> filters() {
    return new ArrayList<>(filters);
  }

}
