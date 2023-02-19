package com.twentytwo.designpatterns.singleton;

public class Singleton {

    private static volatile Singleton INSTANCE;

    private Singleton(){}

    private static Singleton getInstance(){

        if(INSTANCE == null){

            synchronized (Singleton.class){
                if(INSTANCE == null) INSTANCE = new Singleton();
            }
        }
        return INSTANCE;
    }
}
