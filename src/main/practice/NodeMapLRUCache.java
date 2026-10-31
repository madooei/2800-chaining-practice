package practice;

import map.HashMap;
import map.Map;

/**
 * A least recently used cache that maps each key to its node in a recency list.
 *
 * @param <K> the type of keys in this cache.
 * @param <V> the type of values in this cache.
 */
public class NodeMapLRUCache<K, V> {

  // A node of the recency list. It stores its key so that eviction, which starts
  // from the node at the back, can remove that key from the map.
  private static class Node<K, V> {
    K key;
    V value;
    Node<K, V> prev;
    Node<K, V> next;

    Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  private final Map<K, Node<K, V>> map = new HashMap<>();  // each key -> its node in the list
  private final Node<K, V> head;  // front sentinel; head.next is the most recently used
  private final Node<K, V> tail;  // back sentinel; tail.prev is the least recently used
  private final int capacity;

  // Assumes capacity is at least one.
  public NodeMapLRUCache(int capacity) {
    this.capacity = capacity;
    head = new Node<>(null, null);
    tail = new Node<>(null, null);
    head.next = tail;
    tail.prev = head;
  }

  // Returns the value stored under key, or null if key is not in the cache. A
  // hit counts as a use of key; a miss uses nothing. Assumes key is not null.
  public V get(K key) {
    Node<K, V> node = map.get(key);
    if (node == null) {
      return null;  // a miss uses nothing
    }
    moveToFront(node);
    return node.value;
  }

  // Stores value under key and counts as a use of key. If the key is new and the
  // cache is full, evicts the least recently used key first. Assumes key and
  // value are not null.
  public void put(K key, V value) {
    Node<K, V> node = map.get(key);
    if (node != null) {
      node.value = value;  // an update: replace the value, refresh the recency
      moveToFront(node);
      return;
    }
    if (map.size() == capacity) {
      Node<K, V> oldest = tail.prev;  // the least recently used, sitting at the back
      unlink(oldest);
      map.remove(oldest.key);
    }
    Node<K, V> newNode = new Node<>(key, value);
    linkAtFront(newNode);
    map.put(key, newNode);
  }

  private void moveToFront(Node<K, V> node) {
    unlink(node);
    linkAtFront(node);
  }

  private void unlink(Node<K, V> node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private void linkAtFront(Node<K, V> node) {
    node.prev = head;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
  }
}
