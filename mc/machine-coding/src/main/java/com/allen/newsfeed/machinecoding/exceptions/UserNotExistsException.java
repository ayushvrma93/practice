package com.allen.newsfeed.machinecoding.exceptions;

public class UserNotExistsException extends RuntimeException{

    private String message;

    public UserNotExistsException(String message){
        super(message);
    }
}
