package dilcheck.pintdb.domain.kvstore;

import static org.junit.Assert.assertEquals;

import dilcheck.pintdb.domain.kvstore.StringKvStore;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringKvStoreTests {
  private static final StringKvStore stringKvStore = new StringKvStore();

  @BeforeClass
  public static void setup() {
    stringKvStore.set("get", "getTest");
    stringKvStore.set("delete", "deleteTest");
  }

  @Test
  public void getTest() {
    String expected = "getTest";
    String actual = stringKvStore.get("get");

    assertEquals(expected, actual);
  }

  @Test(expected = NullPointerException.class)
  public void getExceptionTest() {
    stringKvStore.get(null);
  }

  @Test
  public void setTest() {
    final String key = "key";
    final String expected = "value";

    stringKvStore.set(key, expected);
    final String actual = stringKvStore.get(key);

    assertEquals(expected, actual);
  }

  @Test(expected = NullPointerException.class)
  public void setExceptionTest() {
    stringKvStore.set(null, "value");
  }

  @Test
  public void deleteTest() {
    final String key = "delete";
    final String expected = null;

    stringKvStore.delete(key);
    final String actual = stringKvStore.get(key);

    assertEquals(expected, actual);
  }

  @Test(expected = NullPointerException.class)
  public void deleteExceptionTest() {
    stringKvStore.delete(null);
  }
}
