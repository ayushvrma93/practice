package com.twentythree.phonepe.beans;

import lombok.Data;

@Data
public class User implements IEntity{

    private String name;
    private String email;

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }
}
