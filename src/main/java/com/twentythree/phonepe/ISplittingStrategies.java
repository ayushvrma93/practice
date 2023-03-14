package com.twentythree.phonepe;

import com.twentythree.phonepe.beans.User;

import java.util.List;

public interface ISplittingStrategies {

    //this should have other params as well, which enables us to add share, percentage, etc. splitting
    /*
    * class SplittedRatios{
    *
    * User user,
    * String fraction;
    *
    * }
    * */
    // List<Transaction> handle(List<SplittedRatios>, Float amount);
    Float handle(List<User> users, Float amount);
}
