package dilcheck.pintdb.controller;

import static org.slf4j.LoggerFactory.getLogger;

import dilcheck.pintdb.model.RequestForm;
import dilcheck.pintdb.model.ResultForm;
import dilcheck.pintdb.model.V1ApiVersion;
import dilcheck.pintdb.service.SaveDatabaseService;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@V1ApiVersion
@RestController
public class DatabaseController {
  protected final Logger logger = getLogger(getClass());

  @Autowired
  SaveDatabaseService databaseService;

  /**
   * set string value to store.
   * 
   * @param request requestForm(key, value)
   */
  @PostMapping("/strings")
  public ResultForm setString(@RequestBody RequestForm request) {
    String key = request.getKey();
    Object value = request.getValue();
    logger.info("[DC] setString key: {}, value: {}", key, value);
    databaseService.set(key, value, String.class);

    ResultForm resultForm = new ResultForm.Builder().status(true).build();
    return resultForm;
  }

  /**
   * get string value by key.
   * 
   * @param key key
   */
  @GetMapping("/strings/{key}")
  public ResultForm getString(@PathVariable String key) {
    logger.info("[DC] getString key: {}", key);

    Object value = databaseService.get(key, String.class);
    ResultForm resultForm = new ResultForm.Builder().status(true).data(value).build();
    return resultForm;
  }

  /**
   * delete string by key.
   * 
   * @param key key
   */
  @DeleteMapping("/strings/{key}")
  public ResultForm deleteString(@PathVariable String key) {
    logger.info("[DC] deleteString key: {}", key);

    databaseService.delete(key);
    ResultForm resultForm = new ResultForm.Builder().status(true).build();
    return resultForm;
  }

  /**
   * set hashMap value to store.
   * 
   * @param request requestForm(key, value)
   */
  @ResponseBody
  @PostMapping("/hashmaps")
  public ResultForm setHashMap(@RequestBody RequestForm request) {
    String key = request.getKey();
    Object value = request.getValue();
    logger.info("[DC] setHashMap key: {}, value: {}", key, value);

    databaseService.set(key, value, HashMap.class);
    ResultForm resultForm = new ResultForm.Builder().status(true).build();
    return resultForm;
  }

  /**
   * get hashMap by key.
   * 
   * @param key key
   */
  @GetMapping("/hashmaps/{key}")
  public ResultForm getHashMap(@PathVariable String key) {
    logger.info("[DC] getHashMap key: {}", key);

    Object value = databaseService.get(key, HashMap.class);
    ResultForm resultForm = new ResultForm.Builder().status(true).data(value).build();
    return resultForm;
  }

  /**
   * delete hashMap by key.
   * 
   * @param key key
   */
  @DeleteMapping("/hashmaps/{key}")
  public ResultForm deleteHashMap(@PathVariable String key) {
    logger.info("[DC] deleteHashMap key: {}", key);

    databaseService.delete(key);
    ResultForm resultForm = new ResultForm.Builder().status(true).build();
    return resultForm;
  }

  /**
   * set list value to store.
   * 
   * @param request requestForm(key, value)
   */
  @PostMapping("/lists")
  public ResultForm setList(@RequestBody RequestForm request) {
    String key = request.getKey();
    Object value = request.getValue();
    logger.info("[DC] setList key: {}, value: {}", key, value);

    databaseService.set(key, value, List.class);
    ResultForm resultForm = new ResultForm.Builder().status(true).build();
    return resultForm;
  }

  /**
   * get list by key.
   * 
   * @param key key
   */
  @GetMapping("/lists/{key}")
  public ResultForm getList(@PathVariable String key) {
    logger.info("[DC] getList key: {}", key);

    Object value = databaseService.get(key, List.class);
    ResultForm resultForm = new ResultForm.Builder().status(true).data(value).build();
    return resultForm;
  }

  /**
   * delete list by key.
   * 
   * @param key key
   */
  @DeleteMapping("/lists/{key}")
  public ResultForm deleteList(@PathVariable String key) {
    logger.info("[DC] deleteList key: {}", key);

    databaseService.delete(key);
    ResultForm resultForm = new ResultForm.Builder().status(true).build();
    return resultForm;
  }
}
