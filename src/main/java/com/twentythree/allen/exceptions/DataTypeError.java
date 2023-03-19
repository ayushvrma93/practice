package com.twentythree.allen.exceptions;

public class DataTypeError extends RuntimeException{

    private String message;

    public DataTypeError(String message){
        super(message);
    }

}
