package dilcheck.pintdb.service;

public interface DatabaseService {
  public void set(String key, Object value, Class<? extends Object> clazz);
  
  public Object get(String key, Class<? extends Object> clazz);
  
  public void delete(String key);
}
