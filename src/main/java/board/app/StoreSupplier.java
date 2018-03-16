package board.app;

import board.observer.Store;

@FunctionalInterface
public interface StoreSupplier {

  Store supply() throws Exception;

}
