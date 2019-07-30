package dilcheck.pintdb.domain.config;

import java.io.InputStream;
import java.util.HashMap;
import org.yaml.snakeyaml.Yaml;

/*
 * use this configuration because of static kvStore. need to find better way
 */
public class StoreConfig {
  private static HashMap<String, HashMap<String, Object>> configMap;

  /**
   * setup configuration.
   */
  public StoreConfig() {
    final Yaml yaml = new Yaml();
    final InputStream inputStream =
        this.getClass().getClassLoader().getResourceAsStream("application.yaml");
    configMap = yaml.load(inputStream);
  }

  public static Integer getHealthPort() {
    String config = configMap.get("server").get("health").toString();
    return Integer.valueOf(config);
  }

  public static Integer getExpiredTime() {
    String config = configMap.get("kvStore").get("expired-time").toString();
    return Integer.valueOf(config);
  }

  public static String getFilePath() {
    String config = configMap.get("kvStore").get("file-path").toString();
    return config;
  }

  public static String getFileName() {
    String config = configMap.get("kvStore").get("file-name").toString();
    return config;
  }
}
