package whiteboard.observer;

/** Handler of Exceptions. */
@FunctionalInterface
public interface Handler {

  /** handle the Exception. */
  void handle(Exception exception);

}
