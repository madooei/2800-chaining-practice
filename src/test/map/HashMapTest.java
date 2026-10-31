package map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Runs the Map contract suite against HashMap, then checks the order its
 * iterators visit the keys in a seven-slot table and after the table grows.
 */
public class HashMapTest extends MapTest {

  @Override
  protected Map<String, Integer> createMap() {
    return new HashMap<>();
  }

  @Test
  public void iteratorSkipsEmptySlotsAtTheFront() {
    Map<String, Integer> table = new HashMap<>();
    table.put("fox", 70);  // slot 6, the last slot
    assertEquals("fox", table.iterator().next());
  }

  @Test
  public void iteratorSkipsEmptySlotsAtTheEnd() {
    Map<String, Integer> table = new HashMap<>();
    table.put("cat", 10);  // slot 3
    Iterator<String> it = table.iterator();
    it.next();
    assertFalse(it.hasNext());
  }

  @Test
  public void iteratorVisitsEveryKeyInABucket() {
    Map<String, Integer> table = new HashMap<>();
    table.put("bee", 40);  // bee, pig, and hen all go to slot 5
    table.put("pig", 50);
    table.put("hen", 60);
    assertEquals(listOf("bee", "pig", "hen"), keysOf(table));
  }

  @Test
  public void iteratorVisitsTheSlotsInOrder() {
    assertEquals(listOf("cat", "bee", "pig", "hen"), keysOf(fourKeys()));
  }

  @Test
  public void entriesVisitTheSlotsInOrder() {
    List<String> pairs = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : fourKeys().entries()) {
      pairs.add(entry.key() + "=" + entry.value());
    }
    assertEquals(listOf("cat=10", "bee=40", "pig=50", "hen=60"), pairs);
  }

  @Test
  public void iteratorOrderChangesAfterTheTableGrows() {
    Map<String, Integer> table = fourKeys();
    table.put("fox", 70);
    table.put("dog", 80);  // the sixth put grows the table to 17 slots
    table.remove("fox");
    table.remove("dog");
    assertEquals(listOf("bee", "cat", "hen", "pig"), keysOf(table));
  }

  // The four keys of the chapter's trace: cat in slot 3, and bee, pig, and hen
  // sharing slot 5 of a seven-slot table.
  private static Map<String, Integer> fourKeys() {
    Map<String, Integer> table = new HashMap<>();
    table.put("cat", 10);
    table.put("bee", 40);
    table.put("pig", 50);
    table.put("hen", 60);
    return table;
  }

  private static List<String> keysOf(Map<String, Integer> table) {
    List<String> keys = new ArrayList<>();
    for (String key : table) {
      keys.add(key);
    }
    return keys;
  }

  private static List<String> listOf(String... items) {
    List<String> list = new ArrayList<>();
    for (String item : items) {
      list.add(item);
    }
    return list;
  }
}
