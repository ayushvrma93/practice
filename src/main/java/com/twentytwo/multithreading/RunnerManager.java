package com.twentytwo.multithreading;

public class RunnerManager {

    public static void main(String[] args) throws InterruptedException {

        Runner runner = new Runner();

        Thread myThread1 = new RunnersTrainer(runner, "ayush");
        Thread myThread2 = new RunnersTrainer(runner, "suyash");

        myThread1.start();
        myThread1.join();
        myThread2.start();
    }
}
