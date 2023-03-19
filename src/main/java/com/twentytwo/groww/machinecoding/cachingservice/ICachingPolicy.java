package com.twentytwo.groww.machinecoding.cachingservice;

import com.twentytwo.groww.machinecoding.cachingservice.entities.Pair;

public interface ICachingPolicy<K,V> {
    V get(K key);
    void put(Pair<K,V> entry);
}