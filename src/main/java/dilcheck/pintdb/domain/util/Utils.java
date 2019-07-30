package dilcheck.pintdb.domain.util;

import static dilcheck.pintdb.domain.config.Configuration.DATA_FILE_NAME;
import static dilcheck.pintdb.domain.config.Configuration.DATA_FILE_PATH;
import static org.slf4j.LoggerFactory.getLogger;

import dilcheck.pintdb.domain.model.AppendableObjectOutputStream;
import dilcheck.pintdb.domain.model.HandleType;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import org.slf4j.Logger;

public class Utils {
  protected final Logger logger = getLogger(getClass());
  private static final String FILE_PATH = DATA_FILE_PATH + "/" + DATA_FILE_NAME;
  private static AppendableObjectOutputStream outputStream;

  static {
    try {
      outputStream = new AppendableObjectOutputStream(new FileOutputStream(FILE_PATH, true));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * save log to store.
   * 
   * @param handleType handle type(insert, delete)
   * @param key key
   */
  public static void saveLog(HandleType handleType, String key) {
    HashMap<String, Object> log = new HashMap<String, Object>();
    log.put("type", handleType);
    log.put("key", key);

    save(log);
    log.clear();
  }

  /**
   * save log to store.
   * 
   * @param handleType handle type(insert, delete)
   * @param key key
   */
  public static void saveLog(HandleType handleType, String key, Object value) {
    HashMap<String, Object> log = new HashMap<String, Object>();
    log.put("type", handleType);
    log.put("key", key);
    log.put("value", value);

    save(log);
    log.clear();
  }

  private static void save(HashMap<String, Object> log) {
    try {
      outputStream.writeObject(log);
      outputStream.reset();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
