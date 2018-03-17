package board.app;

import board.Manager;

/** Prompter prompts for actons to perform on Managers. */
@FunctionalInterface
public interface Prompter {

  /**
   * prompt for an action to perform on the Manager and return true to keep
   * prompting.
   *
   * @throws Exception If prompting fails.
   */
  boolean prompt(Manager manager) throws Exception;

}
