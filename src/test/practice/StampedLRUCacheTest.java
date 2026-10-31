package practice;

/** Runs the LRU cache scenarios against StampedLRUCache. */
public class StampedLRUCacheTest extends LRUCacheTest {

  private StampedLRUCache<Integer, String> cache;

  @Override
  protected void newCache(int capacity) {
    cache = new StampedLRUCache<>(capacity);
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
