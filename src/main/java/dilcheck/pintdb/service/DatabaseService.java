package dilcheck.pintdb.service;

import static org.slf4j.LoggerFactory.getLogger;

import dilcheck.pintdb.domain.kvstore.HashMapKvStore;
import dilcheck.pintdb.domain.kvstore.KvStore;
import dilcheck.pintdb.domain.kvstore.ListKvStore;
import dilcheck.pintdb.domain.kvstore.StringKvStore;
import dilcheck.pintdb.model.DataType;
import java.util.HashMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
  protected final Logger logger = getLogger(getClass());

  private static final HashMap<DataType, KvStore<? extends Object>> kvStoreMap =
      new HashMap<DataType, KvStore<? extends Object>>();

  static {
    kvStoreMap.put(DataType.STRING, new StringKvStore());
    kvStoreMap.put(DataType.HASHMAP, new HashMapKvStore());
    kvStoreMap.put(DataType.LIST, new ListKvStore());
  }

  /**
   * set key-value to store.
   * @param key key
   * @param value value
   * @param dataType data type
   */
  public void set(String key, Object value, DataType dataType) {
    logger.info("[DS] set key: {}, value: {}, dataType: {}", key, value, dataType);

    KvStore<? extends Object> kvStore = kvStoreMap.get(dataType);
    kvStore.set(key, value);
  }

  /**
   * get value by key.
   * @param key key
   * @param dataType data type;
   */
  public Object get(String key, DataType dataType) {
    logger.info("[DS] get key: {}, dataType: {}", key, dataType);

    KvStore<? extends Object> kvStore = kvStoreMap.get(dataType);
    return kvStore.get(key);
  }

  /**
   * delete value by key.
   * @param key key
   * @param dataType data type;
   */
  public void delete(String key, DataType dataType) {
    logger.info("[DS] delete key: {}", key);

    KvStore<? extends Object> kvStore = kvStoreMap.get(dataType);
    kvStore.delete(key);
  }
}
