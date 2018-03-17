package board.app;

import board.View;

/** Displayer displays Views. */
@FunctionalInterface
public interface Displayer {

  /** display the View. */
  void display(View view);

}
