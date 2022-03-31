package com.groww.machinecoding.cachingservice.impl;

import com.groww.machinecoding.cachingservice.ICaching;
import com.groww.machinecoding.cachingservice.ICachingPolicy;
import com.groww.machinecoding.cachingservice.entities.Pair;
import com.groww.machinecoding.cachingservice.entities.User;
import com.groww.machinecoding.cachingservice.enums.EvictionPolicy;

public class CacheManager implements ICaching {

    private static final UserManager userManager = UserManager.getINSTANCE();


    @Override
    public String get(User user, String key) {
         EvictionPolicy evictionPolicy = getCachingPolicyByUser(user.getId());
         ICachingPolicy cachingPolicy = getPolicy(evictionPolicy);
         return (String) cachingPolicy.get(key);
    }

    @Override
    public void put(User user, Pair entry) {
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
