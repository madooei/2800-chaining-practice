package practice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * The scenarios for the LRU cache problem. A concrete subclass supplies the
 * three methods below to pick the solution under test.
 */
public abstract class LRUCacheTest {

  // Creates a new, empty cache of the given capacity for the solution under test.
  protected abstract void newCache(int capacity);

  // Calls get on the cache under test.
  protected abstract String get(Integer key);

  // Calls put on the cache under test.
  protected abstract void put(Integer key, String value);

  @Test
  public void getOnEmptyCacheReturnsNull() {
    newCache(3);
    assertNull(get(1));
  }

  @Test
  public void getReturnsThePutValue() {
    newCache(3);
    put(1, "ant");
    assertEquals("ant", get(1));
  }

  @Test
  public void getAbsentKeyReturnsNull() {
    newCache(3);
    put(1, "ant");
    assertNull(get(2));
  }

  @Test
  public void putExistingKeyReplacesTheValue() {
    newCache(3);
    put(1, "ant");
    put(1, "ape");
    assertEquals("ape", get(1));
  }

  @Test
  public void putUpToCapacityKeepsEveryKey() {
    newCache(3);
    put(1, "ant");
    put(2, "bat");
    put(3, "cow");
    assertEquals("ant", get(1));
    assertEquals("bat", get(2));
    assertEquals("cow", get(3));
  }

  @Test
  public void putPastCapacityEvictsTheFirstPutKey() {
    newCache(3);
    put(1, "ant");
    put(2, "bat");
    put(3, "cow");
    put(4, "dog");
    assertNull(get(1));
  }

  @Test
  public void putPastCapacityKeepsTheNewKey() {
    newCache(3);
    put(1, "ant");
    put(2, "bat");
    put(3, "cow");
    put(4, "dog");
    assertEquals("dog", get(4));
  }

  @Test
  public void getCountsAsAUse() {
    newCache(3);
    put(1, "ant");
    put(2, "bat");
    put(3, "cow");
    get(1);
    put(4, "dog");
    assertNull(get(2));
  }

  @Test
  public void getKeepsItsKeyFromEviction() {
    newCache(3);
    put(1, "ant");
    put(2, "bat");
    put(3, "cow");
    get(1);
    put(4, "dog");
    assertEquals("ant", get(1));
  }

  @Test
  public void getMissUsesNothing() {
    newCache(3);
    put(1, "ant");
    put(2, "bat");
    put(3, "cow");
    get(9);
    put(4, "dog");
    assertNull(get(1));
  }

  @Test
  public void putExistingKeyCountsAsAUse() {
    newCache(3);
    put(1, "ant");
    put(2, "bat");
    put(3, "cow");
    put(1, "ape");
    put(4, "dog");
    assertNull(get(2));
  }

  @Test
  public void putExistingKeyInFullCacheEvictsNothing() {
    newCache(3);
    put(1, "ant");
    put(2, "bat");
    put(3, "cow");
    put(2, "bee");
    assertEquals("ant", get(1));
    assertEquals("bee", get(2));
    assertEquals("cow", get(3));
  }

  @Test
  public void usingAMiddleKeyMovesItToTheFront() {
    newCache(3);
    put(1, "ant");
    put(2, "bat");
    put(3, "cow");
    get(2);
    put(4, "dog");
    put(5, "eel");
    assertEquals("bat", get(2));
  }

  @Test
  public void capacityOneEvictsOnEveryNewKey() {
    newCache(1);
    put(1, "ant");
    put(2, "bat");
    assertNull(get(1));
  }

  @Test
  public void capacityOneKeepsTheLastKey() {
    newCache(1);
    put(1, "ant");
    put(2, "bat");
    assertEquals("bat", get(2));
  }

  @Test
  public void manyPutsEvictTheOlderKeys() {
    newCache(3);
    for (int i = 1; i <= 10; i++) {
      put(i, "v" + i);
    }
    for (int i = 1; i <= 7; i++) {
      assertNull(get(i));
    }
  }

  @Test
  public void manyPutsKeepTheLastKeys() {
    newCache(3);
    for (int i = 1; i <= 10; i++) {
      put(i, "v" + i);
    }
    assertEquals("v8", get(8));
    assertEquals("v9", get(9));
    assertEquals("v10", get(10));
  }
}
