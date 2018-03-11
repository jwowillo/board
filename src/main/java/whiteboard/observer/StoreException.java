package whiteboard.observer;

/** StoreException is thrown when a Store has any failure. */
public class StoreException extends Exception {

  /** StoreException initializes Exception with a fixed message. */
  public StoreException() {
    super("couldn't store");
  }

}
