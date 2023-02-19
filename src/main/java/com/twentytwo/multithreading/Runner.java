package com.twentytwo.multithreading;

public class Runner {

    public void run(String name, int repeat) throws InterruptedException {

        for(int i=0; i<repeat; i++)
        System.out.println("i am " + name + " and I am running for the " + i + "th time");
        Thread.sleep(500);
    }

}
