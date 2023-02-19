package com.twentytwo.multithreading;

public class Test implements Runnable{

    @Override
    public void run() {

        for(int i=0; i<5; i++){
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Test test = new Test();

        Thread t1 = new Thread(() -> {
                test.run(); }, "Thread1");

        Thread t2 = new Thread(() -> {
            test.run();
        } );

        Thread t3 = new Thread(() -> {
            test.run();
        } );

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Thread 3 starting..");

        t3.start();
    }
}
