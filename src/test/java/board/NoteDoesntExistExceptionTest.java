package board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class NoteDoesntExistExceptionTest {

  @Test
  public void testMessage() {
    Exception exception = new NoteDoesntExistException();
    assertEquals("note doesn't exist", exception.getMessage());
  }

}
