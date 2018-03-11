package whiteboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.HashSet;
import java.util.function.Function;

public class ManagerTest {

  private class WrappedBoolean {

    public boolean isSet;

  }

  @Test
  public void testConstructor() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Set<Topic> ts = new HashSet<>();
    ts.add(new Topic("name"));
    Set<Note> ns = new HashSet<>();
    ns.add(new Note("content"));
    for (Topic t : ts) {
      w.addTopic(t);
      for (Note n : ns) {
        w.addNote(t, n);
      }
    }
    Set<Observer> os = new HashSet<>();
    WrappedBoolean b1 = new WrappedBoolean();
    WrappedBoolean b2 = new WrappedBoolean();
    os.add(makeTopicsObserver(ts, (View v) -> true, b1));
    os.add(makeTopicsObserver(ts, (View v) -> true, b2));
    Manager m = new Manager(w, os);
    assertTrue(b1.isSet);
    assertTrue(b2.isSet);
  }

  @Test
  public void testAddTopic() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    WrappedBoolean b1 = new WrappedBoolean();
    WrappedBoolean b2 = new WrappedBoolean();
    Set<Observer> os = new HashSet<>();
    Set<Topic> ts = new HashSet<>();
    os.add(makeTopicsObserver(ts, (View v) -> v.topics().size() != 0, b1));
    os.add(makeTopicsObserver(ts, (View v) -> v.topics().size() != 0, b2));
    Manager m = new Manager(w, os);
    ts.add(new Topic("name"));
    for (Topic t : ts) {
      m.addTopic(t);
    }
    assertTrue(b1.isSet);
    assertTrue(b2.isSet);
  }

  @Test
  public void testAddTopicException() throws WhiteboardException {
    Manager m = new Manager(new Whiteboard(), new HashSet<>());
    Topic t = new Topic("name");
    m.addTopic(t);
    assertThrows(WhiteboardException.class, () -> m.addTopic(t));
  }

  @Test
  public void testRemoveTopic() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    w.addTopic(t);
    WrappedBoolean b1 = new WrappedBoolean();
    WrappedBoolean b2 = new WrappedBoolean();
    WrappedBoolean isRemoved = new WrappedBoolean();
    Set<Topic> ts = new HashSet<>();
    Set<Observer> os = new HashSet<>();
    os.add(makeTopicsObserver(ts, (View v) -> isRemoved.isSet, b1));
    os.add(makeTopicsObserver(ts, (View v) -> isRemoved.isSet, b2));
    Manager m = new Manager(w, os);
    isRemoved.isSet = true;
    m.removeTopic(t);
    assertTrue(b1.isSet);
    assertTrue(b2.isSet);
  }

  @Test
  public void testRemoveTopicException() {
    Manager m = new Manager(new Whiteboard(), new HashSet<>());
    Topic t = new Topic("name");
    assertThrows(WhiteboardException.class, () -> m.removeTopic(t));
  }

  @Test
  public void testAddNote() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    w.addTopic(t);
    WrappedBoolean b1 = new WrappedBoolean();
    WrappedBoolean b2 = new WrappedBoolean();
    Set<Note> ns = new HashSet<>();
    Set<Observer> os = new HashSet<>();
    os.add(makeNotesObserver(t, ns, (View v) -> ns.size() != 0, b1));
    os.add(makeNotesObserver(t, ns, (View v) -> ns.size() != 0, b2));
    Manager m = new Manager(w, os);
    ns.add(new Note("topic"));
    for (Note n : ns) {
      m.addNote(t, n);
    }
    assertTrue(b1.isSet);
    assertTrue(b2.isSet);
  }

  @Test
  public void testAddNoteException() {
    Manager m = new Manager(new Whiteboard(), new HashSet<>());
    Topic t = new Topic("name");
    Note n = new Note("content");
    assertThrows(WhiteboardException.class, () -> m.addNote(t, n));
  }

  @Test
  public void testRemoveNote() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    Note n  = new Note("content");
    w.addTopic(t);
    w.addNote(t, n);
    WrappedBoolean b1 = new WrappedBoolean();
    WrappedBoolean b2 = new WrappedBoolean();
    WrappedBoolean isRemoved = new WrappedBoolean();
    Set<Note> ns = new HashSet<>();
    Set<Observer> os = new HashSet<>();
    os.add(makeNotesObserver(t, ns, (View v) -> isRemoved.isSet, b1));
    os.add(makeNotesObserver(t, ns, (View v) -> isRemoved.isSet, b2));
    Manager m = new Manager(w, os);
    isRemoved.isSet = true;
    m.removeNote(t, n);
    assertTrue(b1.isSet);
    assertTrue(b2.isSet);
  }

  @Test
  public void testRemoveNoteException() {
    Manager m = new Manager(new Whiteboard(), new HashSet<>());
    Topic t = new Topic("name");
    Note n = new Note("content");
    assertThrows(WhiteboardException.class, () -> m.removeNote(t, n));
  }

  private Observer makeTopicsObserver(Set<Topic> ts, Function<View, Boolean> f,
      WrappedBoolean b) {
    return (View v) -> {
      if (!f.apply(v)) {
        return;
      }
      b.isSet = true;
      assertEquals(ts, v.topics());
    };
  }

  private Observer makeNotesObserver(Topic t, Set<Note> ns,
      Function<View, Boolean> f, WrappedBoolean b) {
    return (View v) -> {
      if (!f.apply(v)) {
        return;
      }
      b.isSet = true;
      assertEquals(ns, v.notes(t));
    };
  }

}
