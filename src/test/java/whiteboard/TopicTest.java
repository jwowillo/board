package whiteboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class TopicTest {

  @Test
  public void testName() {
    Topic t = new Topic("name");
    assertEquals(t.name(), "name");
  }

  @Test
  public void testEquals() {
    Topic t1 = new Topic("name1");
    Topic t2 = new Topic("name1");
    Topic t3 = new Topic("name2");
    assertEquals(t1, t2);
    assertNotEquals(t2, t3);
  }

  @Test
  public void testHashCode() {
    Topic t1 = new Topic("name1");
    Topic t2 = new Topic("name1");
    Topic t3 = new Topic("name2");
    assertEquals(t1.hashCode(), t2.hashCode());
    assertNotEquals(t2.hashCode(), t3.hashCode());
  }

}
