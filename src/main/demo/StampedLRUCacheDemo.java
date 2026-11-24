package demo;

import practice.StampedLRUCache;

/** A demo of StampedLRUCache on the capacity-3 trace from the problem. */
public final class StampedLRUCacheDemo {

  private StampedLRUCacheDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    StampedLRUCache<Integer, String> cache = new StampedLRUCache<>(3);

    // Fill the cache to its capacity of 3.
    cache.put(1, "ant");
    cache.put(2, "bat");
    cache.put(3, "cow");
    System.out.println("put 1:ant, 2:bat, 3:cow");

    // Expect "ant". This get makes key 1 the most recently used.
    System.out.println("get(1): " + cache.get(1));

    // The cache is full, so put evicts the least recently used key, 2.
    cache.put(4, "dog");
    System.out.println("put 4:dog");

    // Expect null for key 2, which was evicted, and "ant", "cow", "dog" for the rest.
    System.out.println("get(2): " + cache.get(2));
    System.out.println("get(1): " + cache.get(1));
    System.out.println("get(3): " + cache.get(3));
    System.out.println("get(4): " + cache.get(4));
  }
}
