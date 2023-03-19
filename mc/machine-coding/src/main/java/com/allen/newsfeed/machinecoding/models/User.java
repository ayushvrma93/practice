package com.allen.newsfeed.machinecoding.models;

import lombok.Data;

import java.util.Objects;

@Data
public class User {

    private int id;
    private String name;
    private String email;
    private static int currId;

    public User(String name, String email){
        this.id = currId++;
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
