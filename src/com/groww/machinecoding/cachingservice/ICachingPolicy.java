package com.groww.machinecoding.cachingservice;

import com.groww.machinecoding.cachingservice.entities.Pair;

public interface ICachingPolicy {
    String get(String key);
    void put(Pair entry);
}