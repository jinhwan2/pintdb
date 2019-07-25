package dilcheck.pintdb.domain.kvstore;

import static java.util.Objects.requireNonNull;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Hashtable;
import org.slf4j.Logger;

abstract class KvStore<T> {
  protected final Logger logger = getLogger(getClass());
  private static final String NULL_EXCEPTION_MESSAGE = "key must not be null";

  private static final Hashtable<String, Object> hashTable = new Hashtable<>();

  public void set(String key, T value) {
    logger.info("set key: {}, value: {}", key, value);

    requireNonNull(key, NULL_EXCEPTION_MESSAGE);
    hashTable.put(key, value);
  }

  @SuppressWarnings("unchecked")
  public T get(String key) {
    logger.info("get key: {}", key);

    requireNonNull(key, NULL_EXCEPTION_MESSAGE);
    return (T) hashTable.get(key);
  }

  public void delete(String key) {
    logger.info("delete key: {}", key);

    requireNonNull(key, NULL_EXCEPTION_MESSAGE);
    hashTable.remove(key);
  }
}
