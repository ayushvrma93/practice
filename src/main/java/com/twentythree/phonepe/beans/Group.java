package com.twentythree.phonepe.beans;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Group implements IEntity{

    private String id;
    private List<User> users;

    private Map<User, Integer> userVsIndices;

    private List<Transaction>[][] transactionsAndUsers;

    public Group(String id, List<User> users){
        this.id = id;
        this.users = users;
    }

}
