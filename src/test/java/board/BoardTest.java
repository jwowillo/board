package board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class BoardTest {

  @Test
  public void addTopic() throws BoardException {
    Board b = new Board();
    Set<Topic> ts = new HashSet<>();
    ts.add(new Topic("1"));
    ts.add(new Topic("2"));
    ts.add(new Topic("3"));
    for (Topic t : ts) {
      b.addTopic(t);
      b.addNote(t, new Note("a"));
    }
    assertEquals(new ArrayList<>(ts), b.topics());
  }

  @Test
  public void addTopicExists() throws BoardException {
    Board b = new Board();
    Topic t = new Topic("name");
    b.addTopic(t);
    assertThrows(TopicExistsException.class, () -> b.addTopic(t));
  }

  @Test
  public void testAddNote() throws BoardException {
    Board b = new Board();
    Topic t1 = new Topic("1");
    Topic t2 = new Topic("2");
    Topic t3 = new Topic("3");
    b.addTopic(t1);
    b.addTopic(t2);
    b.addTopic(t3);
    Set<Note> ns1 = new HashSet<>();
    Set<Note> ns2 = new HashSet<>();
    Set<Note> ns3 = new HashSet<>();
    ns1.add(new Note("a"));
    ns2.add(new Note("b"));
    ns3.add(new Note("c"));
    for (Note n : ns1) {
      b.addNote(t1, n);
    }
    for (Note n : ns2) {
      b.addNote(t2, n);
    }
    for (Note n : ns3) {
      b.addNote(t3, n);
    }
    assertEquals(new ArrayList<>(ns1), b.notes(t1));
    assertEquals(new ArrayList<>(ns2), b.notes(t2));
    assertEquals(new ArrayList<>(ns3), b.notes(t3));
  }

  @Test
  public void testAddNoteNoteExists() throws BoardException {
    Board b = new Board();
    Topic t = new Topic("name");
    b.addTopic(t);
    Note n = new Note("content");
    b.addNote(t, n);
    assertThrows(NoteExistsException.class, () -> b.addNote(t, n));
  }

  @Test
  public void testAddNoteTopicDoesntExist() {
    Board b = new Board();
    Topic t = new Topic("name");
    Note n = new Note("content");
    assertThrows(TopicDoesntExistException.class, () -> b.addNote(t, n));
  }

  @Test
  public void testRemoveTopic() throws BoardException {
    Board b = new Board();
    Topic t = new Topic("name");
    b.addTopic(t);
    b.removeTopic(t);
    assertEquals(0, b.topics().size());
  }

  @Test
  public void testRemoveTopicDoesntExist() {
    Board b = new Board();
    Topic t = new Topic("name");
    assertThrows(TopicDoesntExistException.class, () -> b.removeTopic(t));
  }

  @Test
  public void removeNote() throws BoardException {
    Board b = new Board();
    Topic t1 = new Topic("1");
    Topic t2 = new Topic("2");
    Set<Note> ns = new HashSet<Note>();
    Note a = new Note("a");
    ns.add(new Note("b"));
    ns.add(new Note("c"));
    b.addTopic(t1);
    b.addNote(t1, a);
    b.addNote(t2, a);
    for (Note n : ns) {
      b.addNote(t1, n);
      b.addNote(t2, n);
    }
    b.removeNote(t1, a);
    assertEquals(new ArrayList<>(ns), b.notes(t1));
    ns.add(a);
    assertEquals(new ArrayList<>(ns), b.notes(t2));
  }

  @Test
  public void removeNoteTopicDoesntExist() throws BoardException {
    Board b = new Board();
    Topic t = new Topic("name");
    Note n = new Note("content");
    assertThrows(TopicDoesntExistException.class, () -> b.removeNote(t, n));
  }

  @Test
  public void removeNoteNoteDoesntExist() throws BoardException {
    Board b = new Board();
    Topic t = new Topic("name");
    Note n = new Note("content");
    b.addTopic(t);
    assertThrows(NoteDoesntExistException.class, () -> b.removeNote(t, n));
  }

  @Test
  public void testTopics() throws BoardException {
    Board b = new Board();
    Set<Topic> ts = new HashSet<>();
    ts.add(new Topic("a"));
    ts.add(new Topic("b"));
    ts.add(new Topic("c"));
    for (Topic t : ts) {
      b.addTopic(t);
    }
    assertEquals(new ArrayList<>(ts), b.topics());
  }

  @Test
  public void testTopicsCopy() throws BoardException {
    Board b = new Board();
    Topic t = new Topic("name");
    b.addTopic(t);
    b.topics().clear();
    assertEquals(1, b.topics().size());
  }

  @Test
  public void testNotesNoTopic() {
    Board b = new Board();
    Topic t = new Topic("name");
    assertEquals(0, b.notes(t).size());
  }

  @Test
  public void testNotesEmptyTopic() throws BoardException {
    Board b = new Board();
    Topic t = new Topic("name");
    b.addTopic(t);
    assertEquals(0, b.notes(t).size());
  }

  @Test
  public void testNotes() throws BoardException {
    Board b = new Board();
    Topic t1 = new Topic("name1");
    Topic t2 = new Topic("name2");
    Set<Note> ns = new HashSet<>();
    ns.add(new Note("a"));
    ns.add(new Note("b"));
    ns.add(new Note("c"));
    b.addTopic(t1);
    b.addTopic(t2);
    for (Note n : ns) {
      b.addNote(t1, n);
    }
    assertEquals(new ArrayList<>(ns), b.notes(t1));
    assertEquals(0, b.notes(t2).size());
  }

  @Test
  public void testNotesCopy() throws BoardException {
    Board b = new Board();
    Topic t = new Topic("name");
    b.addTopic(t);
    b.addNote(t, new Note("content"));
    b.notes(t).clear();
    assertEquals(1, b.notes(t).size());
  }

}
