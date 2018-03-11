package whiteboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.HashSet;

public class WhiteboardTest {

  @Test
  public void addTopic() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Set<Topic> ts = new HashSet<>();
    ts.add(new Topic("1"));
    ts.add(new Topic("2"));
    ts.add(new Topic("3"));
    for (Topic t : ts) {
      w.addTopic(t);
      w.addNote(t, new Note("a"));
    }
    assertEquals(ts, w.topics());
  }

  @Test
  public void addTopicExists() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    w.addTopic(t);
    assertThrows(TopicExistsException.class, () -> w.addTopic(t));
  }

  @Test
  public void testAddNote() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t1 = new Topic("1");
    Topic t2 = new Topic("2");
    Topic t3 = new Topic("3");
    w.addTopic(t1);
    w.addTopic(t2);
    w.addTopic(t3);
    Set<Note> ns1 = new HashSet<>();
    Set<Note> ns2 = new HashSet<>();
    Set<Note> ns3 = new HashSet<>();
    ns1.add(new Note("a"));
    ns2.add(new Note("b"));
    ns3.add(new Note("c"));
    for (Note n : ns1) {
      w.addNote(t1, n);
    }
    for (Note n : ns2) {
      w.addNote(t2, n);
    }
    for (Note n : ns3) {
      w.addNote(t3, n);
    }
    assertEquals(ns1, w.notes(t1));
    assertEquals(ns2, w.notes(t2));
    assertEquals(ns3, w.notes(t3));
  }

  @Test
  public void testAddNoteNoteExists() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    w.addTopic(t);
    Note n = new Note("content");
    w.addNote(t, n);
    assertThrows(NoteExistsException.class, () -> w.addNote(t, n));
  }

  @Test
  public void testAddNoteTopicDoesntExist() {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    Note n = new Note("content");
    assertThrows(TopicDoesntExistException.class, () -> w.addNote(t, n));
  }

  @Test
  public void testRemoveTopic() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    w.addTopic(t);
    w.removeTopic(t);
    assertEquals(0, w.topics().size());
  }

  @Test
  public void testRemoveTopicDoesntExist() {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    assertThrows(TopicDoesntExistException.class, () -> w.removeTopic(t));
  }

  @Test
  public void removeNote() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t1 = new Topic("1");
    Topic t2 = new Topic("2");
    Set<Note> ns = new HashSet<Note>();
    Note a = new Note("a");
    ns.add(new Note("b"));
    ns.add(new Note("c"));
    w.addTopic(t1);
    w.addNote(t1, a);
    w.addNote(t2, a);
    for (Note n : ns) {
      w.addNote(t1, n);
      w.addNote(t2, n);
    }
    w.removeNote(t1, a);
    assertEquals(ns, w.notes(t1));
    ns.add(a);
    assertEquals(ns, w.notes(t2));
  }

  @Test
  public void removeNoteTopicDoesntExist() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    Note n = new Note("content");
    assertThrows(TopicDoesntExistException.class, () -> w.removeNote(t, n));
  }

  @Test
  public void removeNoteNoteDoesntExist() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    Note n = new Note("content");
    w.addTopic(t);
    assertThrows(NoteDoesntExistException.class, () -> w.removeNote(t, n));
  }

  @Test
  public void testTopics() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Set<Topic> ts = new HashSet<>();
    ts.add(new Topic("a"));
    ts.add(new Topic("b"));
    ts.add(new Topic("c"));
    for (Topic t : ts) {
      w.addTopic(t);
    }
    assertEquals(ts, w.topics());
  }

  @Test
  public void testTopicsCopy() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    w.addTopic(t);
    w.topics().clear();
    assertEquals(1, w.topics().size());
  }

  @Test
  public void testNotesNoTopic() {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    assertEquals(0, w.notes(t).size());
  }

  @Test
  public void testNotesEmptyTopic() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    w.addTopic(t);
    assertEquals(0, w.notes(t).size());
  }

  @Test
  public void testNotes() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t1 = new Topic("name1");
    Topic t2 = new Topic("name2");
    Set<Note> ns = new HashSet<>();
    ns.add(new Note("a"));
    ns.add(new Note("b"));
    ns.add(new Note("c"));
    w.addTopic(t1);
    w.addTopic(t2);
    for (Note n : ns) {
      w.addNote(t1, n);
    }
    assertEquals(ns, w.notes(t1));
    assertEquals(0, w.notes(t2).size());
  }

  @Test
  public void testNotesCopy() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t = new Topic("name");
    w.addTopic(t);
    w.addNote(t, new Note("content"));
    w.notes(t).clear();
    assertEquals(1, w.notes(t).size());
  }

}
