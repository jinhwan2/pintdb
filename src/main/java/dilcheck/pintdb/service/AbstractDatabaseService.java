package dilcheck.pintdb.service;

import static org.slf4j.LoggerFactory.getLogger;

import dilcheck.pintdb.domain.kvstore.HashMapKvStore;
import dilcheck.pintdb.domain.kvstore.KvStore;
import dilcheck.pintdb.domain.kvstore.ListKvStore;
import dilcheck.pintdb.domain.kvstore.StringKvStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.slf4j.Logger;

public abstract class AbstractDatabaseService {
  protected final Logger logger = getLogger(getClass());
  
  protected static final HashMap<Class<? extends Object>, KvStore<? extends Object>> kvStoreMap =
      new HashMap<Class<? extends Object>, KvStore<? extends Object>>();
  
  static {
    kvStoreMap.put(String.class, new StringKvStore());
    kvStoreMap.put(HashMap.class, new HashMapKvStore());
    kvStoreMap.put(LinkedHashMap.class, new HashMapKvStore());
    kvStoreMap.put(List.class, new ListKvStore());
    kvStoreMap.put(ArrayList.class, new ListKvStore());
  }
}
