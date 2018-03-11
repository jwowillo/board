package whiteboard;

/** Topic used to organize Notes. */
public class Topic {

  /** name of the Topic. */
  private final String name;

  /** Topic with the name. */
  public Topic(String name) {
    this.name = name;
  }

  /** name of the topic. */
  public String name() {
    return name;
  }

  /** equals is based on the Topic's name. */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Topic)) {
      return false;
    }
    return name.equals(((Topic) object).name());
  }

  /** hashCode is based on the Topic's name. */
  @Override
  public int hashCode() {
    return name.hashCode();
  }

}
