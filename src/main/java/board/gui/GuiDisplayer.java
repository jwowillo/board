package board.gui;

import board.View;
import board.app.Displayer;
import board.gui.component.Display;

public class GuiDisplayer implements Displayer {

  private final Display display;

  public GuiDisplayer(Display display) {
    this.display = display;
  }

  @Override
  public void display(View view) {
    display.show(view);
  }

}
