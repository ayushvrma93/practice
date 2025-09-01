package com.twentyfour.coupang.machinecoding.movieticket.controller;


import com.twentyfour.coupang.machinecoding.movieticket.exception.CustomException;
import com.twentyfour.coupang.machinecoding.movieticket.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    Map<String, User> userMap = new HashMap<>();

    public User onboard(User user) throws CustomException {
        if(userMap.containsKey(user.getPhone())){
            throw new CustomException("user already exists");
        }
        userMap.put(user.getPhone(), user);
        return user;
    }

    public User getUser(String phone) throws CustomException {
        if(userMap.containsKey(phone)){
            throw new CustomException("user does not exist");
        }
        return userMap.get(phone);
    }
}
