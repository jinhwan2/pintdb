package dilcheck.pintdb.integration;

import dilcheck.pintdb.AbstractRunnerConfig;

class IntegrationTestConfig extends AbstractRunnerConfig {
  protected static final int CORES = Runtime.getRuntime().availableProcessors() * 2;
  protected static final int TEST = 1_000_000;
  protected static final int SECTION = TEST / CORES;
  
  protected static long getTime() {
    return System.currentTimeMillis();
  }

  protected static long getMemory() {
    return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
  }
}
