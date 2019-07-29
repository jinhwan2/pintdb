package dilcheck.pintdb.domain.kvstore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;
import org.junit.Test;

public class StringKvStoreTests extends StoreTestConfig {
  private static final StringKvStore stringKvStore = new StringKvStore();
  private static final String value = "value";

  @Test
  public void simpleIoTest() {
    String key = "test";

    stringKvStore.set(key, value);
    String actual = stringKvStore.get(key);
    assertEquals(value, actual);
  }

  @Test
  public void testA() {
    // single thread input test
    long start = System.currentTimeMillis();
    IntStream.range(1, TEST + 1).forEach(i -> {
      stringKvStore.set(String.valueOf(i), value);
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
      stringKvStore.get(String.valueOf(i));
    });

    long end = System.currentTimeMillis();
    // goal 1_000_000 write in 2 second
    assertTrue(end - start < 2000);
  }

  @Test
  public void testC() {
    // multi thread input test
    long start = System.currentTimeMillis();

    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        stringKvStore.set(String.valueOf(i + "_" + j), value);
      }
    });

    long end = System.currentTimeMillis();
    // goal 1_000_000 write in 2 second
    assertTrue(end - start < 2000);
  }

  @Test
  public void testD() {
    // multi thread read test
    long start = System.currentTimeMillis();

    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        stringKvStore.get(String.valueOf(i + "_" + j));
      }
    });

    long end = System.currentTimeMillis();
    // goal 1_000_000 write in 2 second
    assertTrue(end - start < 2000);
  }
}
