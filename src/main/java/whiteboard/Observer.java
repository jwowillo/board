package whiteboard;

import whiteboard.View;

/** Observer observers Views of Whiteboards. */
@FunctionalInterface
public interface Observer {

  /** observe the View. */
  void observe(View view);

}
