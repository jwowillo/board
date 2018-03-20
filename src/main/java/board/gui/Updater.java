package board.gui;

import board.View;

@FunctionalInterface
public interface Updater {

  void update(View view);

}
