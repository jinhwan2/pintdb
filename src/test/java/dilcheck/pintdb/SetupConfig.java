package dilcheck.pintdb;

import dilcheck.pintdb.domain.config.StoreConfig;
import org.junit.BeforeClass;

public class SetupConfig {
  @BeforeClass
  public static void setup() {
    new StoreConfig();
  }
}
