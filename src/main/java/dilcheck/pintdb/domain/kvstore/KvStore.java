package dilcheck.pintdb.domain.kvstore;

import static dilcheck.pintdb.domain.config.Configuration.SESSION_TIMEOUT_SECOND;
import static java.util.Objects.requireNonNull;
import static org.slf4j.LoggerFactory.getLogger;

import dilcheck.pintdb.domain.model.TtlConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;

public abstract class KvStore<T> {
  protected final Logger logger = getLogger(getClass());
  private static final String NULL_EXCEPTION_MESSAGE = "key must not be null";

  private static final TtlConcurrentHashMap<String, Object> ttlConcurrentHashMap =
      new TtlConcurrentHashMap<>(TimeUnit.SECONDS, SESSION_TIMEOUT_SECOND);

  @SuppressWarnings("unchecked")
  public void set(String key, Object value) {
    requireNonNull(key, NULL_EXCEPTION_MESSAGE);
    ttlConcurrentHashMap.put(key, (T) value);
  }

  @SuppressWarnings("unchecked")
  public T get(String key) {
    requireNonNull(key, NULL_EXCEPTION_MESSAGE);
    return (T) ttlConcurrentHashMap.get(key);
  }

  public void delete(String key) {
    requireNonNull(key, NULL_EXCEPTION_MESSAGE);
    ttlConcurrentHashMap.remove(key);
  }
}
