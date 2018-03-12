package whiteboard.store;

import whiteboard.View;
import whiteboard.Whiteboard;
import whiteboard.observer.Store;
import whiteboard.observer.StoreException;

public class FileStore implements Store {

  public FileStore(String path) {

  }

  @Override
  public void updateWhiteboard(View view) throws StoreException {

  }

  @Override
  public Whiteboard whiteboard() throws StoreException {
    return new Whiteboard();
  }

}
