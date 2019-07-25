package dilcheck.pintdb.domain.kvstore;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import org.junit.BeforeClass;
import org.junit.Test;

public class HashMapKvStoreTests {
  private static final HashMapKvStore hashMapKvStore = new HashMapKvStore();
  private static HashMap<String, String> VALUE1;
  private static HashMap<String, String> VALUE2;

  /**
   * test environment setup.
   */
  @BeforeClass
  public static void setup() {
    VALUE1 = new HashMap<String, String>();
    VALUE2 = new HashMap<String, String>();
    hashMapKvStore.set("get", VALUE1);
    hashMapKvStore.set("delete", VALUE2);
  }

  @Test
  public void getTest() {
    HashMap<String, ? extends Object> actual = hashMapKvStore.get("get");
    assertEquals(VALUE1, actual);
  }

  @Test(expected = NullPointerException.class)
  public void getExceptionTest() {
    hashMapKvStore.get(null);
  }

  @Test
  public void setTest() {
    final String key = "key";
    final HashMap<String, String> expected = new HashMap<String, String>();

    hashMapKvStore.set(key, expected);
    final HashMap<String, ? extends Object> actual = hashMapKvStore.get(key);

    assertEquals(expected, actual);
  }

  @Test(expected = NullPointerException.class)
  public void setExceptionTest() {
    hashMapKvStore.set(null, new HashMap<String, String>());
  }

  @Test
  public void deleteTest() {
    final String key = "delete";
    final String expected = null;

    hashMapKvStore.delete(key);
    final HashMap<String, ? extends Object> actual = hashMapKvStore.get(key);

    assertEquals(expected, actual);
  }

  @Test(expected = NullPointerException.class)
  public void deleteExceptionTest() {
    hashMapKvStore.delete(null);
  }
}
