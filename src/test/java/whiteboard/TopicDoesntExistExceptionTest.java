package whiteboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TopicDoesntExistExceptionTest {

  @Test
  public void testMessage() {
    Exception exception = new TopicDoesntExistException();
    assertEquals("topic doesn't exist", exception.getMessage());
  }

}
