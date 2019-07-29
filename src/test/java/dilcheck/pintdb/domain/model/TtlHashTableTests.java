package dilcheck.pintdb.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.junit.Test;

public class TtlHashTableTests {
  private static final int CORES = Runtime.getRuntime().availableProcessors() * 1;
  private static final int TEST = 1_000_000;
  private static final int SECTION = TEST / CORES;

  private static final TtlConcurrentHashMap<String, Object> ttlHashTable =
      new TtlConcurrentHashMap<String, Object>(TimeUnit.SECONDS, 1);
  private static final Object value = "value";

  @Test
  public void simpleIoTest() {
    Object key = "test";
    ttlHashTable.put("test", value);

    Object actual = ttlHashTable.get(key);
    assertEquals(value, actual);
  }

  @Test
  public void liveTest() throws InterruptedException {
    String key = "live";
    ttlHashTable.put(key, "test");

    Thread.sleep(1000);

    Object actual = ttlHashTable.get(key);
    assertEquals(null, actual);
  }

  @Test
  public void testA() {
    // single thread input test
    long start = System.currentTimeMillis();
    IntStream.range(1, TEST + 1).forEach(i -> {
      ttlHashTable.put(String.valueOf(i), value);
    });

    long end = System.currentTimeMillis();
    // goal 1_000_000 write in 2 second
    assertTrue(end - start < 2000);
  }

  @Test
  public void testB() {
    // single thread read test
    long start = System.currentTimeMillis();

    IntStream.range(1, TEST + 1).forEach(i -> {
      ttlHashTable.get(String.valueOf(i));
    });

    long end = System.currentTimeMillis();

    ttlHashTable.clear();
    // goal 1_000_000 read in 2 second
    assertTrue(end - start < 2000);

  }

  @Test
  public void testC() {
    // multi thread input test
    long start = System.currentTimeMillis();

    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        ttlHashTable.put(String.valueOf(i + "_" + j), value);
      }
    });

    long end = System.currentTimeMillis();
    // goal 1_000_000 write in 1.5 second
    assertTrue(end - start < 2000);
  }

  @Test
  public void testD() {
    // multi thread read test
    long start = System.currentTimeMillis();

    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        ttlHashTable.get(String.valueOf(i + "_" + j));
      }
    });

    long end = System.currentTimeMillis();
    // goal 1_000_000 read in 2 second
    assertTrue(end - start < 2000);
  }
}
