package board.gui;

import board.View;
import board.app.Displayer;
import board.gui.component.ViewComponent;

public class GuiDisplayer implements Displayer {

  private final ViewComponent viewComponent;

  public GuiDisplayer(ViewComponent viewComponent) {
    this.viewComponent = viewComponent;
  }

  @Override
  public void display(View view) {
    viewComponent.show(view);
  }

}
