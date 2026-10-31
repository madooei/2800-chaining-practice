package practice;

import map.HashMap;
import map.Map;

/**
 * A least recently used cache that keeps its keys in a recency list beside the
 * map.
 *
 * @param <K> the type of keys in this cache.
 * @param <V> the type of values in this cache.
 */
public class RecencyListLRUCache<K, V> {

  // A node of the recency list, holding one key.
  private static class Node<K> {
    K key;
    Node<K> prev;
    Node<K> next;

    Node(K key) {
      this.key = key;
    }
  }

  private final Map<K, V> map = new HashMap<>();
  private final Node<K> head;  // front sentinel; head.next is the most recently used
  private final Node<K> tail;  // back sentinel; tail.prev is the least recently used
  private final int capacity;

  // Assumes capacity is at least one.
  public RecencyListLRUCache(int capacity) {
    this.capacity = capacity;
    head = new Node<>(null);
    tail = new Node<>(null);
    head.next = tail;
    tail.prev = head;
  }

  // Returns the value stored under key, or null if key is not in the cache. A
  // hit counts as a use of key; a miss uses nothing. Assumes key is not null.
  public V get(K key) {
    V value = map.get(key);
    if (value == null) {
      return null;  // a miss uses nothing
    }
    moveToFront(key);
    return value;
  }

  // Stores value under key and counts as a use of key. If the key is new and the
  // cache is full, evicts the least recently used key first. Assumes key and
  // value are not null.
  public void put(K key, V value) {
    if (map.containsKey(key)) {
      map.put(key, value);  // an update: replace the value, refresh the recency
      moveToFront(key);
      return;
    }
    if (map.size() == capacity) {
      Node<K> oldest = tail.prev;  // the least recently used, sitting at the back
      unlink(oldest);
      map.remove(oldest.key);
    }
    linkAtFront(new Node<>(key));
    map.put(key, value);
  }

  // Moves the node holding key to the front. Assumes key is in the list.
  private void moveToFront(K key) {
    Node<K> node = head.next;
    while (!node.key.equals(key)) {  // walk from the front to the key's node
      node = node.next;
    }
    unlink(node);
    linkAtFront(node);
  }

  private void unlink(Node<K> node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private void linkAtFront(Node<K> node) {
    node.prev = head;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
  }
}
