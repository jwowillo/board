package board.app;

import board.Manager;

/** Prompter prompts for actions to perform on Managers. */
@FunctionalInterface
public interface Prompter {

  /** prompt for actions to perform on the Manager and return once done. */
  void prompt(Manager manager) throws Exception;

}
