package com.twentytwo.groww.machinecoding.cachingservice.enums;

public enum EvictionPolicy {

    LRU("LRU");

    private String value;

    EvictionPolicy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
