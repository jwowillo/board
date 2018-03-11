package whiteboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TopicExistsExceptionTest {

  @Test
  public void testMessage() {
    Exception exception = new TopicExistsException();
    assertEquals("topic exists", exception.getMessage());
  }

}
