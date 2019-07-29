package dilcheck.pintdb.domain.kvstore;

import static java.util.Objects.requireNonNull;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Hashtable;
import org.slf4j.Logger;

public abstract class KvStore<T> {
  protected final Logger logger = getLogger(getClass());
  private static final String NULL_EXCEPTION_MESSAGE = "key must not be null";

  private static final Hashtable<String, Object> hashTable = new Hashtable<>();

  @SuppressWarnings("unchecked")
  public void set(String key, Object value) {
    requireNonNull(key, NULL_EXCEPTION_MESSAGE);
    hashTable.put(key, (T) value);
  }

  @SuppressWarnings("unchecked")
  public T get(String key) {
    requireNonNull(key, NULL_EXCEPTION_MESSAGE);
    return (T) hashTable.get(key);
  }

  public void delete(String key) {
    requireNonNull(key, NULL_EXCEPTION_MESSAGE);
    hashTable.remove(key);
  }
}
