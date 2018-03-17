package board.app;

import board.observer.Store;

/** StoreSupplier supplies Stores. */
@FunctionalInterface
public interface StoreSupplier {

  /**
   * supply a Store.
   *
   * @throws Exception If the Store couldn't be supplied.
   */
  Store supply() throws Exception;

}
