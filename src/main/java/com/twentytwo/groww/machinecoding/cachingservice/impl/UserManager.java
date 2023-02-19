package com.twentytwo.groww.machinecoding.cachingservice.impl;

import com.twentytwo.groww.machinecoding.cachingservice.entities.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private static final UserManager INSTANCE = new UserManager();
    private Map<Integer, User> users = new HashMap<>();
    private static Integer currAvailableId = 0;

    public static UserManager getINSTANCE() {
        return INSTANCE;
    }

    public User onboardUser(User newUser){

        //put mandatory checks
        Integer currId = currAvailableId++;
        newUser.setId(currId);
        users.put(currId, newUser);
        return users.get(currId);
    }

    public User getUserById(Integer id){
        return users.get(id);
    }
}
