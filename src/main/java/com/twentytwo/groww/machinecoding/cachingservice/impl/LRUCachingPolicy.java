package com.twentytwo.groww.machinecoding.cachingservice.impl;

import com.twentytwo.groww.machinecoding.cachingservice.ICachingPolicy;
import com.twentytwo.groww.machinecoding.cachingservice.entities.Pair;

import java.util.*;

public class LRUCachingPolicy implements ICachingPolicy<String, String> {

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
                String oldKey = old.getKey();
                availableElements.remove(oldKey);
            }
        } else{
            if(availableElements.containsKey(entry.getKey())){
                elements.remove(entry);
            }
        }
        availableElements.put(entry.getKey(), entry.getValue());
        elements.addFirst(entry);
    }
}
