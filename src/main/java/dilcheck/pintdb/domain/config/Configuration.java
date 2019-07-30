package dilcheck.pintdb.domain.config;

// must move to application.yaml
public class Configuration {
  public static final int SESSION_TIMEOUT_SECOND = 30;
  
  public static final String DATA_FILE_PATH = "/logfs/data";

  public static final String DATA_FILE_NAME = "store.log";
}
