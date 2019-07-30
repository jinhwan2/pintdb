package dilcheck.pintdb.service;

import dilcheck.pintdb.domain.kvstore.KvStore;
import dilcheck.pintdb.domain.model.HandleType;
import dilcheck.pintdb.domain.util.Utils;
import org.springframework.stereotype.Service;

@Service
public class SaveDatabaseService extends AbstractDatabaseService implements DatabaseService {
  /**
   * set key-value to store.
   * @param key key
   * @param value value
   * @param clazz data type
   */
  @Override
  public void set(String key, Object value, Class<? extends Object> clazz) {
    logger.debug("[Save] set key: {}, value: {}, dataType: {}", key, value, clazz);

    KvStore<? extends Object> kvStore = kvStoreMap.get(clazz);
    kvStore.set(key, value);
    Utils.saveLog(HandleType.INSERT, key, value);
  }

  /**
   * get value by key.
   * @param key key
   * @param clazz data type;
   */
  @Override
  public Object get(String key, Class<? extends Object> clazz) {
    logger.debug("[Save] get key: {}, dataType: {}", key, clazz);

    KvStore<? extends Object> kvStore = kvStoreMap.get(clazz);
    Object data = kvStore.get(key);
    logger.debug("[Save] result data: {}", data);
    
    return data;
  }

  /**
   * delete value by key.
   * @param key key
   */
  @Override
  public void delete(String key) {
    logger.debug("[Save] delete key: {}", key);

    KvStore<? extends Object> defaultKvStore = kvStoreMap.get(String.class);
    defaultKvStore.delete(key);
    Utils.saveLog(HandleType.DELETE, key);
  }
}
