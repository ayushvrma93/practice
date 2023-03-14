package com.twentythree.phonepe;

import com.twentythree.phonepe.beans.User;

import java.util.List;

public class EqualSplittingStrategy implements ISplittingStrategies{

    private static final ISplittingStrategies splittingStrategies = new EqualSplittingStrategy();

    public EqualSplittingStrategy(){}

    public static ISplittingStrategies getInstance() {
        return splittingStrategies;
    }

    @Override
    public Float handle(List<User> users, Float amount) {
        return amount/ users.size();
    }
}
