package whiteboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class WhiteboardExceptionTest {

  @Test
  public void testMessage() {
    Exception exception = new WhiteboardException("message");
    assertEquals("message", exception.getMessage());
  }

}
