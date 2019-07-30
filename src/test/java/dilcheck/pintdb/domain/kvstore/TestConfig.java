package dilcheck.pintdb.domain.kvstore;

import dilcheck.pintdb.SetupConfig;

class TestConfig extends SetupConfig {
  protected static final int CORES = Runtime.getRuntime().availableProcessors();
  protected static final int TEST = 1_000_000;
  protected static final int SECTION = TEST / CORES;
}
