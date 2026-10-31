package practice;

/** Runs the LRU cache scenarios against NodeMapLRUCache. */
public class NodeMapLRUCacheTest extends LRUCacheTest {

  private NodeMapLRUCache<Integer, String> cache;

  @Override
  protected void newCache(int capacity) {
    cache = new NodeMapLRUCache<>(capacity);
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
