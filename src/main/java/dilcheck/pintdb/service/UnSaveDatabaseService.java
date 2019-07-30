package dilcheck.pintdb.service;

import dilcheck.pintdb.domain.kvstore.KvStore;
import org.springframework.stereotype.Service;

@Service
public class UnSaveDatabaseService extends AbstractDatabaseService implements DatabaseService {
  /**
   * set key-value to store.
   * 
   * @param key key
   * @param value value
   * @param clazz data type
   */
  @Override
  public void set(String key, Object value, Class<? extends Object> clazz) {
    logger.debug("[unSave] set key: {}, value: {}, dataType: {}", key, value, clazz);

    KvStore<? extends Object> kvStore = kvStoreMap.get(clazz);
    kvStore.set(key, value);
  }

  /**
   * get value by key.
   * 
   * @param key key
   * @param clazz data type;
   */
  @Override
  public Object get(String key, Class<? extends Object> clazz) {
    logger.debug("[DS] get key: {}, dataType: {}", key, clazz);

    KvStore<? extends Object> kvStore = kvStoreMap.get(clazz);
    Object data = kvStore.get(key);

    return data;
  }

  /**
   * delete value by key.
   * 
   * @param key key
   */
  @Override
  public void delete(String key) {
    logger.debug("[unSave] delete key: {}", key);

    KvStore<? extends Object> kvStore = kvStoreMap.get(String.class);
    kvStore.delete(key);
  }
}
