package com.groww.machinecoding.cachingservice.impl;

import com.groww.machinecoding.cachingservice.ICachingPolicy;
import com.groww.machinecoding.cachingservice.entities.Pair;

import java.util.*;

public class LRUCachingPolicy implements ICachingPolicy {

    private static final LRUCachingPolicy INSTANCE = new LRUCachingPolicy(10);

    private final int SIZE;
    private Map<String, String> availableElements;
    private Deque<Pair> elements;


    LRUCachingPolicy(int SIZE){
        this.SIZE = SIZE;
        availableElements = new HashMap<>();
        elements = new LinkedList<>();
    }

    public static LRUCachingPolicy getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public String get(String key) {
        return availableElements.get(key);
    }


    @Override
    public void put(Pair entry) {

        if(availableElements.size() == SIZE){

            if(!availableElements.containsKey(entry.getKey())){
                Pair old = elements.removeLast();
                String oldKey = old.getKey();
                availableElements.remove(oldKey);
            } else {
                elements.remove(entry);
            }
        }
        availableElements.put(entry.getKey(), entry.getValue());
        elements.addFirst(entry);
    }
}
