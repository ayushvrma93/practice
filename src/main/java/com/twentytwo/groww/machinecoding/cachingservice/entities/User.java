package com.twentytwo.groww.machinecoding.cachingservice.entities;

import com.twentytwo.groww.machinecoding.cachingservice.enums.EvictionPolicy;
import lombok.Data;

@Data
public class User {

    private int id;
    private String name;
    private EvictionPolicy evictionPolicy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EvictionPolicy getEvictionPolicy() {
        return evictionPolicy;
    }

    public void setEvictionPolicy(EvictionPolicy evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
    }

    public User(int id, String name, EvictionPolicy evictionPolicy) {
        this.id = id;
        this.name = name;
        this.evictionPolicy = evictionPolicy;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", evictionPolicy=" + evictionPolicy +
                '}';
    }
}
