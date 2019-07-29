package dilcheck.pintdb.domain.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TtlConcurrentHashMap<K, V> implements Map<K, V> {
  private final ConcurrentHashMap<K, V> hashMap = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<K, Long> timeTable = new ConcurrentHashMap<>();
  private final long timeToLive;

  private static final String NOT_AVAILABLE_FUNCTION = "This function is not available.";

  public TtlConcurrentHashMap(TimeUnit timeUnit, long liveTime) {
    this.timeToLive = timeUnit.toNanos(liveTime);
  }

  @Override
  public int size() {
    return hashMap.size();
  }

  @Override
  public boolean isEmpty() {
    return hashMap.isEmpty();
  }

  @Override
  public V put(K key, V value) {
    timeTable.put(key, System.nanoTime());
    return hashMap.put(key, value);
  }

  @Override
  public V get(Object key) {
    V value = this.hashMap.get(key);

    if (value == null) {
      return null;
    }

    if (isLived(key, value)) {
      this.remove(key);
      return null;
    }

    return value;
  }

  private boolean isLived(Object key, V value) {
    long holdingTime = System.nanoTime() - timeTable.get(key);
    return holdingTime > this.timeToLive;
  }

  @Override
  public V remove(Object key) {
    timeTable.remove(key);
    return hashMap.remove(key);
  }

  @Override
  public void clear() {
    hashMap.clear();
  }

  // not use now
  @Override
  public boolean containsKey(Object key) {
    throw new RuntimeException(NOT_AVAILABLE_FUNCTION);
  }

  @Override
  public boolean containsValue(Object value) {
    throw new RuntimeException(NOT_AVAILABLE_FUNCTION);
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> m) {
    throw new RuntimeException(NOT_AVAILABLE_FUNCTION);
  }

  @Override
  public Set<K> keySet() {
    throw new RuntimeException(NOT_AVAILABLE_FUNCTION);
  }

  @Override
  public Collection<V> values() {
    throw new RuntimeException(NOT_AVAILABLE_FUNCTION);
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    throw new RuntimeException(NOT_AVAILABLE_FUNCTION);
  }
}
