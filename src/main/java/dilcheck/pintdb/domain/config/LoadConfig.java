package dilcheck.pintdb.domain.config;

import static dilcheck.pintdb.domain.config.Configuration.DATA_FILE_NAME;
import static dilcheck.pintdb.domain.config.Configuration.DATA_FILE_PATH;
import static org.slf4j.LoggerFactory.getLogger;

import dilcheck.pintdb.domain.model.AppendableObjectInputStream;
import dilcheck.pintdb.service.UnSaveDatabaseService;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadConfig {
  protected final Logger logger = getLogger(getClass());

  private static final String FILE_PATH = DATA_FILE_PATH + "/" + DATA_FILE_NAME;

  @Autowired
  UnSaveDatabaseService storeService;

  @PostConstruct
  private void loadSetUp() throws IOException {
    try {
      logger.info("load start");
      loadStore(FILE_PATH);
    } catch (FileNotFoundException fnfe) {
      logger.info("nothing to load");
    }
  }

  private void loadStore(String filePath) throws IOException {
    try (AppendableObjectInputStream ois =
        new AppendableObjectInputStream(new FileInputStream(filePath))) {
      while (true) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> loadData = (HashMap<String, Object>) ois.readObject();
        load(loadData.get("type"), loadData.get("key").toString(), loadData.get("value"));
      }
    } catch (EOFException eof) {
      logger.info("load finished");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private void load(Object handleType, String key, Object value) {
    switch (handleType.toString()) {
      case "INSERT":
        storeService.set(key, value, value.getClass());
        break;

      case "DELETE":
        storeService.delete(key);
        break;
      default:
        break;
    }
  }
}
