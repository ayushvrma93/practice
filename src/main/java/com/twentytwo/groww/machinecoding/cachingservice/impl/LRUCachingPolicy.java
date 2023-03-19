package com.twentytwo.groww.machinecoding.cachingservice.impl;

import com.twentytwo.groww.machinecoding.cachingservice.ICachingPolicy;
import com.twentytwo.groww.machinecoding.cachingservice.entities.Pair;

import java.util.*;

public class LRUCachingPolicy<K,V> implements ICachingPolicy {

    private static final LRUCachingPolicy INSTANCE = new LRUCachingPolicy(10);

    private final int SIZE;
    private Map<K, V> availableElements;
    private Deque<Pair<K,V>> elements;


    LRUCachingPolicy(int SIZE){
        this.SIZE = SIZE;
        availableElements = new HashMap<>();
        elements = new LinkedList<>();
    }

    public static LRUCachingPolicy getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public V get(Object key) {

        if(availableElements.containsKey(key)){

            if(!elements.peekLast().getKey().equals(key)){
                Pair removePair = new Pair(key, availableElements.get(key));
                elements.remove(removePair);
                elements.addFirst(removePair);
            }
        }

        return availableElements.get(key);
    }


    @Override
    public void put(Pair entry) {

        if(!availableElements.containsKey(entry.getKey())){

            if(availableElements.size() == SIZE) {
                Pair old = elements.removeLast();
                K oldKey = (K) old.getKey();
                availableElements.remove(oldKey);
            }
        } else{
            if(availableElements.containsKey(entry.getKey())){
                elements.remove(entry);
            }
        }
        availableElements.put((K) entry.getKey(), (V) entry.getValue());
        elements.addFirst(entry);
    }
}
