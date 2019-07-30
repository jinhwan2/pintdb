package dilcheck.pintdb.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import dilcheck.pintdb.AbstractRunnerConfig;
import dilcheck.pintdb.model.RequestForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseControllerTests extends AbstractRunnerConfig {
  @Autowired
  DatabaseController databaseController;

  @Test
  public void stringIntegrationTest() {
    final String key = "string key";
    final String value = "string value";

    RequestForm requestForm = new RequestForm();
    requestForm.setKey(key);
    requestForm.setValue(value);
    databaseController.setString(requestForm);

    Object actual = databaseController.getString(key).getData();
    assertEquals(value, actual);

    databaseController.deleteString(key);
    actual = databaseController.getString(key).getData();
    assertNull(actual);
  }

  @Test
  public void hashMapIntegrationTest() {
    final String key = "hashMap key";
    final HashMap<String, Object> value = new HashMap<String, Object>();
    value.put("test", "test");

    RequestForm requestForm = new RequestForm();
    requestForm.setKey(key);
    requestForm.setValue(value);
    databaseController.setHashMap(requestForm);

    Object actual = databaseController.getHashMap(key).getData();
    assertEquals(value, actual);

    databaseController.deleteHashMap(key);
    actual = databaseController.getString(key).getData();
    assertNull(actual);
  }

  @Test
  public void listIntegrationTest() {
    final String key = "list key";
    final List<Object> value = new ArrayList<Object>();
    value.add("test");

    RequestForm requestForm = new RequestForm();
    requestForm.setKey(key);
    requestForm.setValue(value);
    databaseController.setList(requestForm);

    Object actual = databaseController.getList(key).getData();
    assertEquals(value, actual);

    databaseController.deleteList(key);
    actual = databaseController.getList(key).getData();
    assertNull(actual);
  }
}
