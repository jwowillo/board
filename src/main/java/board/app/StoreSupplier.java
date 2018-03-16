package board.app;

import board.observer.Store;
import board.observer.StoreException;

@FunctionalInterface
public interface StoreSupplier {

  Store supply() throws StoreException;

}
