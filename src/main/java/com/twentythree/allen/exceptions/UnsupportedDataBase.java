package com.twentythree.allen.exceptions;

public class UnsupportedDataBase extends RuntimeException{

    private String message;

    public UnsupportedDataBase(String message){
        super(message);
    }
}
