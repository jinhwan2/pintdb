package dilcheck.pintdb.domain.kvstore;

class TestConfig {
  protected static final int CORES = Runtime.getRuntime().availableProcessors();
  protected static final int TEST = 1_000_000;
  protected static final int SECTION = TEST / CORES;
}