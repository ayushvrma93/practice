package com.arrays;

public class Pair {

    private int key;
    private int value;

    public Pair(int minStartTime, int maxEndTime) {
        this.key = minStartTime;
        this.value = maxEndTime;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "start=" + key +
                ", end=" + value +
                '}';
    }
}
