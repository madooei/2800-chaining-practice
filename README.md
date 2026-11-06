# Separate Chaining — Practice

One practice problem: build a least recently used (LRU) cache whose `get` and `put` do not slow down as the cache fills. It has three solutions, each a step toward the last one, and a JUnit suite.

## Prerequisites

- JDK 17+
- The JUnit jar is already vendored in `lib/`; there is nothing to download.

## Repository layout

```plaintext
code/
  README.md
  .gitignore
  lib/
    junit-platform-console-standalone-6.1.0.jar
  src/
    main/
      map/
        Map.java                     # the Map ADT (copied from the chapter)
        HashMap.java                 # the chapter's HashMap
      practice/
        StampedLRUCache.java         # a map that stamps each entry with its last use
        RecencyListLRUCache.java     # a map plus a recency list of keys
        NodeMapLRUCache.java         # a map from each key to its node in a recency list
    test/
      map/
        MapTest.java                 # abstract: the Map contract suite
        HashMapTest.java             # runs it against HashMap
      practice/
        LRUCacheTest.java            # abstract: the LRU cache scenarios
        StampedLRUCacheTest.java     # runs them against StampedLRUCache
        RecencyListLRUCacheTest.java # runs them against RecencyListLRUCache
        NodeMapLRUCacheTest.java     # runs them against NodeMapLRUCache
  scripts/
    test.sh                          # compile and run the JUnit tests
```

## How to compile and run

- `scripts/test.sh` compiles everything and runs the full JUnit suite.
- `scripts/test.sh practice.NodeMapLRUCacheTest` compiles everything and runs only that test class. Use this while you work on one solution and the others are still empty. The class names are listed in the layout above.

There is no demo program for this problem; the tests are how you check your work.

## What's here

- `map.Map<K, V>` — an unchanged copy of the `Map` interface.
- `map.HashMap<K, V>` — the chapter's hash table, complete.
- `practice.StampedLRUCache<K, V>`, `practice.RecencyListLRUCache<K, V>`, and `practice.NodeMapLRUCache<K, V>` — the three solutions to the LRU cache problem. Each has `get(key)` and `put(key, value)`, and a capacity set when it is created.
- `map.MapTest` — the abstract Map contract suite.
- `map.HashMapTest` — runs the contract suite against `HashMap`.
- `practice.LRUCacheTest` — the abstract scenario suite for the LRU cache. It has one subclass per solution, so you can test one solution alone.
