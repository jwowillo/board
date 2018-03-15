package board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class NoteTest {

  @Test
  public void testContent() {
    Note n = new Note("content");
    assertEquals(n.content(), "content");
  }

  @Test
  public void testEquals() {
    Note n1 = new Note("content1");
    Note n2 = new Note("content1");
    Note n3 = new Note("content2");
    assertEquals(n1, n2);
    assertNotEquals(n2, n3);
  }

  @Test
  public void testHashCode() {
    Note n1 = new Note("content1");
    Note n2 = new Note("content1");
    Note n3 = new Note("content2");
    assertEquals(n1.hashCode(), n2.hashCode());
    assertNotEquals(n2.hashCode(), n3.hashCode());
  }

}
