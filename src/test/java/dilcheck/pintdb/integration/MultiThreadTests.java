package dilcheck.pintdb.integration;

import dilcheck.pintdb.controller.DatabaseController;
import dilcheck.pintdb.model.RequestForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MultiThreadTests extends IntegrationTestConfig {
  /*
   * test scenario 
   * 1. Data order 
   *    String -> HashMap -> List
   * 
   * 2. Handle order 
   *    set -> get
   */

  @Autowired
  DatabaseController databaseController;

  @Test
  public void testA() {
    final String value = "test";
    final String key = "string";

    System.gc();
    long startTime = getTime();
    long startMemory = getMemory();

    RequestForm requestForm = new RequestForm();
    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        requestForm.setKey(key + "_" + j);
        requestForm.setValue(value);
        databaseController.setString(requestForm);
      }
    });

    long endTime = getTime();
    long endMemory = getMemory();

    System.out.printf("[string set] time: %f, memory: %f\n", (endTime - startTime) / 1_000.0,
        (endMemory - startMemory) / 1_000.0);
  }

  @Test
  public void testB() {
    final String key = "string";

    System.gc();
    long startTime = getTime();

    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        databaseController.getString(key + "_" + j);
      }
    });

    long endTime = getTime();

    System.out.printf("[string get] time: %f\n", (endTime - startTime) / 1_000.0);
  }
  
  @Test
  public void testC() {
    final HashMap<String, Object> value = new HashMap<String, Object>();
    value.put("key1", "value1");
    value.put("key2", "value2");
    final String key = "hashmap";

    System.gc();
    long startTime = getTime();
    long startMemory = getMemory();

    RequestForm requestForm = new RequestForm();
    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        requestForm.setKey(key + "_" + j);
        requestForm.setValue(value);
        databaseController.setHashMap(requestForm);
      }
    });

    long endTime = getTime();
    long endMemory = getMemory();

    System.out.printf("[hashmap set] time: %f, memory: %f\n", (endTime - startTime) / 1_000.0,
        (endMemory - startMemory) / 1_000.0);
  }

  @Test
  public void testD() {
    final String key = "hashmap";

    System.gc();
    long startTime = getTime();

    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        databaseController.getHashMap(key + "_" + j);
      }
    });

    long endTime = getTime();

    System.out.printf("[hashmap get] time: %f\n", (endTime - startTime) / 1_000.0);
  }
  
  @Test
  public void testE() {
    final String key = "list";
    final List<Object> value = new ArrayList<Object>();
    value.add("value1");
    value.add("value2");
    
    System.gc();
    long startTime = getTime();
    long startMemory = getMemory();

    RequestForm requestForm = new RequestForm();
    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        requestForm.setKey(key + "_" + j);
        requestForm.setValue(value);
        databaseController.setList(requestForm);
      }
    });

    long endTime = getTime();
    long endMemory = getMemory();

    System.out.printf("[list set] time: %f, memory: %f\n", (endTime - startTime) / 1_000.0,
        (endMemory - startMemory) / 1_000.0);
  }

  @Test
  public void testF() {
    final String key = "list";

    System.gc();
    long startTime = getTime();

    IntStream.range(0, CORES).parallel().forEach(i -> {
      for (int j = SECTION * i; j < (SECTION * (i + 1)); j++) {
        databaseController.getList(key + "_" + j);
      }
    });

    long endTime = getTime();

    System.out.printf("[list get] time: %f\n", (endTime - startTime) / 1_000.0);
  }
}
