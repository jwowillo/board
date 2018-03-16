package board;

/** Filter for content. */
public class Filter implements Comparable<Filter> {

  /** term filtered. */
  private final String term;

  /** Filter with the term. */
  public Filter(String term) {
    this.term = term;
  }

  /** term of the Filter. */
  public String term() {
    return term;
  }

  /** equals is based on the Filter's term. */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Filter)) {
      return false;
    }
    return term.equals(((Filter) object).term());
  }

  /** hashCode is based on the Filter's term. */
  @Override
  public int hashCode() {
    return term.hashCode();
  }

  @Override
  public int compareTo(Filter filter) {
    return term.compareTo(filter.term());
  }

}
