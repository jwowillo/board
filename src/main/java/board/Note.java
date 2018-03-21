package board;

/** Note contains content. */
public class Note {

  private final String content;

  public Note(String content) {
    this.content = content;
  }

  /** content of the Note. */
  public String content() {
    return content;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Note)) {
      return false;
    }
    return content.equals(((Note) object).content());
  }

  @Override
  public int hashCode() {
    return content.hashCode();
  }

}
