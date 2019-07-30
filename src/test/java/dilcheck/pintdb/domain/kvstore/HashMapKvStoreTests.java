package dilcheck.pintdb.domain.kvstore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.stream.IntStream;
import org.junit.Test;

public class HashMapKvStoreTests extends TestConfig {
  private static final HashMapKvStore hashMapKvStore = new HashMapKvStore();
  static HashMap<String, String> value = new HashMap<>();

  static {
    value.put("test", "test");
  }

  @Test
  public void simpleIoTest() {
    String key = "test";

    hashMapKvStore.set(key, value);
    HashMap<String, ? extends Object> actual = hashMapKvStore.get(key);

    assertEquals(value, actual);
  }

  @Test
  public void testA() {
    // single thread input test
    long start = System.currentTimeMillis();
    IntStream.range(1, TEST + 1).forEach(i -> {
      hashMapKvStore.set(String.valueOf(i), new HashMap<String, String>());
    });

    long end = System.currentTimeMillis();
    // goal 1_000_000 write in 2 second
    assertTrue(end - start < 2_000);
  }

  @Test
  public void testB() {
    // single thread read test
    long start = System.currentTimeMillis();

    IntStream.range(1, TEST + 1).forEach(i -> {
      hashMapKvStore.get(String.valueOf(i));
    });

    long end = System.currentTimeMillis();
    // goal 1_000_000 read in 2 second
    assertTrue(end - start < 2_000);

  }

  @Test
  public void testC() {
    // multi thread input test
    long start = System.currentTimeMillis();

    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        hashMapKvStore.set(String.valueOf(i), value);
      }
    });

    long end = System.currentTimeMillis();
    // goal 1_000_000 write in 2 second
    assertTrue(end - start < 2_000);
  }

  @Test
  public void testD() {
    // multi thread read test
    long start = System.currentTimeMillis();

    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        hashMapKvStore.get(String.valueOf(i));
      }
    });

    long end = System.currentTimeMillis();
    // goal 1_000_000 read in 2 second
    assertTrue(end - start < 2_000);
  }
}
