package board;

/** Filter for content. */
public class Filter {

  private final String term;

  public Filter(String term) {
    this.term = term;
  }

  /** term of the Filter. */
  public String term() {
    return term;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Filter)) {
      return false;
    }
    return term.equals(((Filter) object).term());
  }

  @Override
  public int hashCode() {
    return term.hashCode();
  }

}
