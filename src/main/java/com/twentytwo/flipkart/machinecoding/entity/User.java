package com.twentytwo.flipkart.machinecoding.entity;

import com.twentytwo.flipkart.machinecoding.entity.enums.City;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class User {

    private int id;
    private String name;
    private int age;
    private City city;

    private static int currId = 1;


    public User(String name, int age, City city) {
        this.id = currId++;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name) && city == user.city;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, city);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", city=" + city +
                '}';
    }
}
