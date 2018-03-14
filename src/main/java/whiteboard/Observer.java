package board;

/** Observer observers Views of Boards. */
@FunctionalInterface
public interface Observer {

  /** observe the View. */
  void observe(View view);

}
