package board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class NoteExistsExceptionTest {

  @Test
  public void testMessage() {
    Exception exception = new NoteExistsException();
    assertEquals("note exists", exception.getMessage());
  }

}
