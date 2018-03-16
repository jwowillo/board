package board.app;

import board.Manager;

@FunctionalInterface
public interface Prompter {

  boolean prompt(Manager manager) throws Exception;

}
