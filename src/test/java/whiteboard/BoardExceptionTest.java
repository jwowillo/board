package board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BoardExceptionTest {

  @Test
  public void testMessage() {
    Exception exception = new BoardException("message");
    assertEquals("message", exception.getMessage());
  }

}
