package com.twentyfour.misc;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap {
    private final Map<Integer, Integer> cache;
    private final int capacity;

    public LRUCacheLinkedHashMap(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}
