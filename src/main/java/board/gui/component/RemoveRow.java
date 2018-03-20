package board.gui.component;

import javafx.scene.text.Text;

public class RemoveRow extends Row {

  public RemoveRow(String text, Runnable runnable) {
    super(new Text(text), "X", runnable);
  }

}
