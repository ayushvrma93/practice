package com.twentythree.allen.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair<String, Object> {

    private String key;
    private Object value;

    public Pair(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}
