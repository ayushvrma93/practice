package com.twentytwo.groww.machinecoding.cachingservice;

import com.twentytwo.groww.machinecoding.cachingservice.entities.Pair;
import com.twentytwo.groww.machinecoding.cachingservice.entities.User;

public interface ICaching<K,V> {

    V get(User user, K key);
    void put(User user, Pair<K,V> entry);

    User onboardUser(User newUser);
}