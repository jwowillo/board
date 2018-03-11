package whiteboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.HashSet;

public class ViewTest {

  @Test
  public void testTopics() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Set<Topic> ts = new HashSet<>();
    ts.add(new Topic("1"));
    ts.add(new Topic("2"));
    ts.add(new Topic("3"));
    for (Topic t : ts) {
      w.addTopic(t);
      w.addNote(t, new Note("a"));
    }
    View v = new View(w);
    assertEquals(ts, v.topics());
    v.topics().clear();
    assertEquals(ts, v.topics());
    for (Topic t : ts) {
      w.removeTopic(t);
    }
    assertEquals(0, v.topics().size());
  }

  @Test
  public void testNotes() throws WhiteboardException {
    Whiteboard w = new Whiteboard();
    Topic t1 = new Topic("1");
    Topic t2 = new Topic("2");
    w.addTopic(t1);
    w.addTopic(t2);
    Set<Note> ns1 = new HashSet<>();
    Set<Note> ns2 = new HashSet<>();
    ns1.add(new Note("a"));
    ns2.add(new Note("b"));
    for (Note n : ns1) {
      w.addNote(t1, n);
    }
    for (Note n : ns2) {
      w.addNote(t2, n);
    }
    View v = new View(w);
    assertEquals(ns1, v.notes(t1));
    assertEquals(ns2, v.notes(t2));
    v.notes(t1).clear();
    v.notes(t2).clear();
    assertEquals(ns1, v.notes(t1));
    assertEquals(ns2, v.notes(t2));
    for (Note n : ns1) {
      w.removeNote(t1, n);
    }
    for (Note n : ns2) {
      w.removeNote(t2, n);
    }
    assertEquals(0, v.notes(t1).size());
    assertEquals(0, v.notes(t2).size());
  }


}
