package com.twentythree.allen.exceptions;

public class KeyNotExists extends RuntimeException{

    private String message;

    public KeyNotExists(String message){
        super(message);
    }
}
