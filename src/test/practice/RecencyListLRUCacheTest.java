package practice;

/** Runs the LRU cache scenarios against RecencyListLRUCache. */
public class RecencyListLRUCacheTest extends LRUCacheTest {

  private RecencyListLRUCache<Integer, String> cache;

  @Override
  protected void newCache(int capacity) {
    cache = new RecencyListLRUCache<>(capacity);
  }

  @Override
  protected String get(Integer key) {
    return cache.get(key);
  }

  @Override
  protected void put(Integer key, String value) {
    cache.put(key, value);
  }
}
