package board;

/** Topic used to organize Notes. */
public class Topic {

  private final String name;

  public Topic(String name) {
    this.name = name;
  }

  /** name of the topic. */
  public String name() {
    return name;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Topic)) {
      return false;
    }
    return name.equals(((Topic) object).name());
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

}
