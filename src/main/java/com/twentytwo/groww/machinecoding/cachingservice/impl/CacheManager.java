package com.twentytwo.groww.machinecoding.cachingservice.impl;

import com.twentytwo.groww.machinecoding.cachingservice.ICaching;
import com.twentytwo.groww.machinecoding.cachingservice.ICachingPolicy;
import com.twentytwo.groww.machinecoding.cachingservice.entities.Pair;
import com.twentytwo.groww.machinecoding.cachingservice.entities.User;
import com.twentytwo.groww.machinecoding.cachingservice.enums.EvictionPolicy;

public class CacheManager<K,V> implements ICaching<K,V> {

    private static final UserManager userManager = UserManager.getINSTANCE();
    private static final ICaching caching = new CacheManager<>();

    private CacheManager(){}

    public static ICaching getCaching() {
        return caching;
    }

    @Override
    public V get(User user, K key) {
         EvictionPolicy evictionPolicy = getCachingPolicyByUser(user.getId());
         ICachingPolicy cachingPolicy = getPolicy(evictionPolicy);
         return (V) cachingPolicy.get(key);
    }

    @Override
    public void put(User user, Pair<K,V> entry) {
        EvictionPolicy evictionPolicy = getCachingPolicyByUser(user.getId());
        ICachingPolicy cachingPolicy = getPolicy(evictionPolicy);
        cachingPolicy.put(entry);
    }


    @Override
    public User onboardUser(User newUser) {
        User onboardedUser = userManager.onboardUser(newUser);
        return userManager.getUserById(onboardedUser.getId());
    }

    private ICachingPolicy getPolicy(EvictionPolicy evictionPolicy){

        switch (evictionPolicy){
            case LRU: return LRUCachingPolicy.getINSTANCE();
        }
        return null;
    }


    private EvictionPolicy getCachingPolicyByUser(Integer userId){
        User user = userManager.getUserById(userId);
        return user.getEvictionPolicy();
    }
}
