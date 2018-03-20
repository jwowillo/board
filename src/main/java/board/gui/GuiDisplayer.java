package board.gui;

import board.View;
import board.app.Displayer;
import board.gui.component.ViewComponent;

public class GuiDisplayer implements Displayer {

  private final Updater updater;

  public GuiDisplayer(Updater updater) {
    this.updater = updater;
  }

  @Override
  public void display(View view) {
    updater.update(view);
  }

}
