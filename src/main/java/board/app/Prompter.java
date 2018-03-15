package board.app;

import board.Manager;

@FunctionalInterface
public interface Prompter {

  void prompt(Manager manager) throws Exception;

}
