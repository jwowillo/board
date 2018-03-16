package board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.HashSet;
import java.util.function.Function;
import java.util.ArrayList;

public class ManagerTest {

  private class WrappedBoolean {

    public boolean isSet;

  }

  @Test
  public void testConstructor() throws BoardException {
    Board b = new Board();
    Set<Topic> ts = new HashSet<>();
    ts.add(new Topic("name"));
    Set<Note> ns = new HashSet<>();
    ns.add(new Note("content"));
    for (Topic t : ts) {
      b.addTopic(t);
      for (Note n : ns) {
        b.addNote(t, n);
      }
    }
    Set<Observer> os = new HashSet<>();
    WrappedBoolean b1 = new WrappedBoolean();
    WrappedBoolean b2 = new WrappedBoolean();
    os.add(makeTopicsObserver(ts, (View v) -> true, b1));
    os.add(makeTopicsObserver(ts, (View v) -> true, b2));
    Manager m = new Manager(b, os);
    assertTrue(b1.isSet);
    assertTrue(b2.isSet);
  }

  @Test
  public void testAddTopicException() throws BoardException {
    Manager m = new Manager(new Board(), new HashSet<>());
    Topic t = new Topic("name");
    m.addTopic(t);
    assertThrows(BoardException.class, () -> m.addTopic(t));
  }

  @Test
  public void testRemoveTopic() throws BoardException {
    Board b = new Board();
    Topic t = new Topic("name");
    b.addTopic(t);
    WrappedBoolean b1 = new WrappedBoolean();
    WrappedBoolean b2 = new WrappedBoolean();
    WrappedBoolean isRemoved = new WrappedBoolean();
    Set<Topic> ts = new HashSet<>();
    Set<Observer> os = new HashSet<>();
    os.add(makeTopicsObserver(ts, (View v) -> isRemoved.isSet, b1));
    os.add(makeTopicsObserver(ts, (View v) -> isRemoved.isSet, b2));
    Manager m = new Manager(b, os);
    isRemoved.isSet = true;
    m.removeTopic(t);
    assertTrue(b1.isSet);
    assertTrue(b2.isSet);
  }

  @Test
  public void testRemoveTopicException() {
    Manager m = new Manager(new Board(), new HashSet<>());
    Topic t = new Topic("name");
    assertThrows(BoardException.class, () -> m.removeTopic(t));
  }

  @Test
  public void testAddNote() throws BoardException {
    Board b = new Board();
    Topic t = new Topic("name");
    b.addTopic(t);
    WrappedBoolean b1 = new WrappedBoolean();
    WrappedBoolean b2 = new WrappedBoolean();
    Set<Note> ns = new HashSet<>();
    Set<Observer> os = new HashSet<>();
    os.add(makeNotesObserver(t, ns, (View v) -> ns.size() != 0, b1));
    os.add(makeNotesObserver(t, ns, (View v) -> ns.size() != 0, b2));
    Manager m = new Manager(b, os);
    ns.add(new Note("topic"));
    for (Note n : ns) {
      m.addNote(t, n);
    }
    assertTrue(b1.isSet);
    assertTrue(b2.isSet);
  }

  @Test
  public void testAddNoteException() {
    Manager m = new Manager(new Board(), new HashSet<>());
    Topic t = new Topic("name");
    Note n = new Note("content");
    assertThrows(BoardException.class, () -> m.addNote(t, n));
  }

  @Test
  public void testRemoveNote() throws BoardException {
    Board b = new Board();
    Topic t = new Topic("name");
    Note n  = new Note("content");
    b.addTopic(t);
    b.addNote(t, n);
    WrappedBoolean b1 = new WrappedBoolean();
    WrappedBoolean b2 = new WrappedBoolean();
    WrappedBoolean isRemoved = new WrappedBoolean();
    Set<Note> ns = new HashSet<>();
    Set<Observer> os = new HashSet<>();
    os.add(makeNotesObserver(t, ns, (View v) -> isRemoved.isSet, b1));
    os.add(makeNotesObserver(t, ns, (View v) -> isRemoved.isSet, b2));
    Manager m = new Manager(b, os);
    isRemoved.isSet = true;
    m.removeNote(t, n);
    assertTrue(b1.isSet);
    assertTrue(b2.isSet);
  }

  @Test
  public void testRemoveNoteException() {
    Manager m = new Manager(new Board(), new HashSet<>());
    Topic t = new Topic("name");
    Note n = new Note("content");
    assertThrows(BoardException.class, () -> m.removeNote(t, n));
  }

  private Observer makeTopicsObserver(Set<Topic> ts, Function<View, Boolean> f,
      WrappedBoolean b) {
    return (View v) -> {
      if (!f.apply(v)) {
        return;
      }
      b.isSet = true;
      assertEquals(new ArrayList<>(ts), v.topics());
    };
  }

  private Observer makeNotesObserver(Topic t, Set<Note> ns,
      Function<View, Boolean> f, WrappedBoolean b) {
    return (View v) -> {
      if (!f.apply(v)) {
        return;
      }
      b.isSet = true;
      assertEquals(new ArrayList<>(ns), v.notes(t));
    };
  }

}
