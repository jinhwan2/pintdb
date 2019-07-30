package dilcheck.pintdb.service;

import static org.slf4j.LoggerFactory.getLogger;

import dilcheck.pintdb.domain.kvstore.HashMapKvStore;
import dilcheck.pintdb.domain.kvstore.KvStore;
import dilcheck.pintdb.domain.kvstore.ListKvStore;
import dilcheck.pintdb.domain.kvstore.StringKvStore;
import dilcheck.pintdb.domain.model.HandleType;
import dilcheck.pintdb.domain.util.Utils;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SaveDatabaseService {
  protected final Logger logger = getLogger(getClass());

  private static final HashMap<Class<? extends Object>, KvStore<? extends Object>> kvStoreMap =
      new HashMap<Class<? extends Object>, KvStore<? extends Object>>();

  static {
    kvStoreMap.put(String.class, new StringKvStore());
    kvStoreMap.put(HashMap.class, new HashMapKvStore());
    kvStoreMap.put(List.class, new ListKvStore());
  }

  /**
   * set key-value to store.
   * @param key key
   * @param value value
   * @param clazz data type
   */
  public void set(String key, Object value, Class<? extends Object> clazz) {
    logger.info("[DS] set key: {}, value: {}, dataType: {}", key, value, clazz);

    KvStore<? extends Object> kvStore = kvStoreMap.get(clazz);
    kvStore.set(key, value);
    Utils.saveLog(HandleType.INSERT, key, value);
  }

  /**
   * get value by key.
   * @param key key
   * @param clazz data type;
   */
  public Object get(String key, Class<? extends Object> clazz) {
    logger.info("[DS] get key: {}, dataType: {}", key, clazz);

    KvStore<? extends Object> kvStore = kvStoreMap.get(clazz);
    Object data = kvStore.get(key);
    logger.info("[DS] result data: {}", data);
    
    return data;
  }

  /**
   * delete value by key.
   * @param key key
   */
  public void delete(String key) {
    logger.info("[DS] delete key: {}", key);

    KvStore<? extends Object> defaultKvStore = kvStoreMap.get(String.class);
    defaultKvStore.delete(key);
    Utils.saveLog(HandleType.DELETE, key);
  }
}