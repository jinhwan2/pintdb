package dilcheck.pintdb;

import dilcheck.pintdb.domain.config.Configuration;
import org.junit.BeforeClass;

public class SetupConfig {
  @BeforeClass
  public static void setup() {
    new Configuration();
  }
}
