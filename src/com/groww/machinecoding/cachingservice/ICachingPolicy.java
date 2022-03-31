package com.groww.machinecoding.cachingservice;

import com.groww.machinecoding.cachingservice.entities.Pair;

public interface ICachingPolicy<K,V> {
    V get(K key);
    void put(Pair entry);
}