package dilcheck.pintdb.domain.kvstore;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListKvStoreTests {
  private static final ListKvStore listKvStore = new ListKvStore();
  private static ArrayList<String> VALUE1;
  private static ArrayList<String> VALUE2;

  /**
   * test environment setup.
   */
  @BeforeClass
  public static void setup() {
    VALUE1 = new ArrayList<String>();
    VALUE2 = new ArrayList<String>();

    listKvStore.set("get", VALUE1);
    listKvStore.set("delete", VALUE2);
  }

  @Test
  public void getTest() {
    List<? extends Object> actual = listKvStore.get("get");
    assertEquals(VALUE1, actual);
  }

  @Test(expected = NullPointerException.class)
  public void getExceptionTest() {
    listKvStore.get(null);
  }

  @Test
  public void setTest() {
    final String key = "key";
    final ArrayList<String> expected = new ArrayList<String>();

    listKvStore.set(key, expected);
    final List<? extends Object> actual = listKvStore.get(key);

    assertEquals(expected, actual);
  }

  @Test(expected = NullPointerException.class)
  public void setExceptionTest() {
    listKvStore.set(null, new ArrayList<String>());
  }

  @Test
  public void deleteTest() {
    final String key = "delete";
    final String expected = null;

    listKvStore.delete(key);
    final List<? extends Object> actual = listKvStore.get(key);

    assertEquals(expected, actual);
  }

  @Test(expected = NullPointerException.class)
  public void deleteExceptionTest() {
    listKvStore.delete(null);
  }
}
