package dilcheck.pintdb.config;

import static org.slf4j.LoggerFactory.getLogger;

import dilcheck.pintdb.service.DatabaseService;
import dilcheck.pintdb.service.SaveDatabaseService;
import dilcheck.pintdb.service.UnSaveDatabaseService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaveConfig {
  protected final Logger logger = getLogger(getClass());

  @Value("${kvStore.save}")
  boolean save;

  @Autowired
  SaveDatabaseService saveDatabaseService;

  @Autowired
  UnSaveDatabaseService unSaveDatabaseService;

  /**
   * return databaseService bean by save type.
   */
  @Bean
  public DatabaseService databaseService() {
    DatabaseService databaseService;
    if (save) {
      logger.info("save mode");
      databaseService = saveDatabaseService;
    } else {
      logger.info("unsave mode");
      databaseService = unSaveDatabaseService;
    }
    return databaseService;
  }
}
