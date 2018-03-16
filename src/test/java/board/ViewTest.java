package board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class ViewTest {

  @Test
  public void testTopics() throws BoardException {
    Board b = new Board();
    Set<Topic> ts = new HashSet<>();
    ts.add(new Topic("1"));
    ts.add(new Topic("2"));
    ts.add(new Topic("3"));
    for (Topic t : ts) {
      b.addTopic(t);
      b.addNote(t, new Note("a"));
    }
    View v = new View(b);
    assertEquals(new ArrayList<>(ts), v.topics());
    v.topics().clear();
    assertEquals(new ArrayList<>(ts), v.topics());
    for (Topic t : ts) {
      b.removeTopic(t);
    }
    assertEquals(0, v.topics().size());
  }

  @Test
  public void testNotes() throws BoardException {
    Board b = new Board();
    Topic t1 = new Topic("1");
    Topic t2 = new Topic("2");
    b.addTopic(t1);
    b.addTopic(t2);
    Set<Note> ns1 = new HashSet<>();
    Set<Note> ns2 = new HashSet<>();
    ns1.add(new Note("a"));
    ns2.add(new Note("b"));
    for (Note n : ns1) {
      b.addNote(t1, n);
    }
    for (Note n : ns2) {
      b.addNote(t2, n);
    }
    View v = new View(b);
    assertEquals(new ArrayList<>(ns1), v.notes(t1));
    assertEquals(new ArrayList<>(ns2), v.notes(t2));
    v.notes(t1).clear();
    v.notes(t2).clear();
    assertEquals(new ArrayList<>(ns1), v.notes(t1));
    assertEquals(new ArrayList<>(ns2), v.notes(t2));
    for (Note n : ns1) {
      b.removeNote(t1, n);
    }
    for (Note n : ns2) {
      b.removeNote(t2, n);
    }
    assertEquals(0, v.notes(t1).size());
    assertEquals(0, v.notes(t2).size());
  }


}
