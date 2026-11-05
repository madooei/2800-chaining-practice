package practice;

import map.HashMap;
import map.Map;

/**
 * A least recently used cache that stamps each value with its key's last use.
 *
 * @param <K> the type of keys in this cache.
 * @param <V> the type of values in this cache.
 */
public class StampedLRUCache<K, V> {

  // A value with the counter's reading at its key's last use.
  private static class Stamped<V> {
    V value;
    long lastUsed;

    Stamped(V value, long lastUsed) {
      this.value = value;
      this.lastUsed = lastUsed;
    }
  }

  private final Map<K, Stamped<V>> map;
  private final int capacity;
  private long tick;  // advances on each hit or put

  // Assumes capacity is at least one.
  public StampedLRUCache(int capacity) {
    map = new HashMap<>();
    this.capacity = capacity;
    tick = 0;
  }

  // Returns the value stored under key, or null if key is not in the cache. A
  // hit counts as a use of key; a miss uses nothing. Assumes key is not null.
  public V get(K key) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Stores value under key and counts as a use of key. If the key is new and the
  // cache is full, evicts the least recently used key first. Assumes key and
  // value are not null.
  public void put(K key, V value) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Returns the key with the smallest stamp. Assumes the cache is not empty.
  private K leastRecentlyUsed() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}
