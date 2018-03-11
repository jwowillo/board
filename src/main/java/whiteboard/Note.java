package whiteboard;

/** Note contains content. */
public class Note {

  /** content of the Note. */
  private final String content;

  /** Note with the content. */
  protected Note(String content) {
    this.content = content;
  }

  /** content of the Note. */
  public String content() {
    return content;
  }

  /** equals is based on the Note's content. */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Note)) {
      return false;
    }
    return content.equals(((Note) object).content());
  }

  /** hashCode is based on the Note's content. */
  @Override
  public int hashCode() {
    return content.hashCode();
  }

}
