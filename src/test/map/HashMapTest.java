package map;

/** Runs the Map contract suite against HashMap. */
public class HashMapTest extends MapTest {

  @Override
  protected Map<String, Integer> createMap() {
    return new HashMap<>();
  }
}
