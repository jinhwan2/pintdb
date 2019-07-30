package dilcheck.pintdb.domain.kvstore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Test;

public class ListKvStoreTests extends TestConfig {
  private static final ListKvStore listKvStore = new ListKvStore();
  static final List<String> value = new ArrayList<>();

  static {
    value.add("test");
  }

  @Test
  public void simpleIoTest() {
    String key = "test";

    listKvStore.set(key, value);
    List<? extends Object> actual = listKvStore.get(key);
    assertEquals(value, actual);
  }

  @Test
  public void testA() {
    // single thread input test
    long start = System.currentTimeMillis();
    IntStream.range(1, TEST + 1).forEach(i -> {
      listKvStore.set(String.valueOf(i), value);
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
      listKvStore.set(String.valueOf(i), value);
    });

    long end = System.currentTimeMillis();
    // goal 1_000_000 write in 2 second
    assertTrue(end - start < 2_000);

  }

  @Test
  public void testC() {
    // multi thread input test
    long start = System.currentTimeMillis();

    // interrupt 고려하기
    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        listKvStore.set(String.valueOf(i), value);
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

    // interrupt 고려하기
    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        listKvStore.set(String.valueOf(i), value);
      }
    });

    long end = System.currentTimeMillis();
    // goal 1_000_000 write in 2 second
    assertTrue(end - start < 2_000);
  }
}
