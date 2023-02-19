package com.twentytwo.groww.machinecoding.cachingservice;

import com.twentytwo.groww.machinecoding.cachingservice.entities.Pair;
import com.twentytwo.groww.machinecoding.cachingservice.entities.User;

public interface ICaching {

    String get(User user, String key);
    void put(User user, Pair entry);

    User onboardUser(User newUser);
}