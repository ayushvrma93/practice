package com.multithreading;

public class RunnersTrainer extends Thread{

    Runner runner;
    String name;

    RunnersTrainer(Runner runner, String name){
        this.runner = runner;
        this.name = name;
    }


    @Override
    public void run() {

        try {
            runner.run(name, 10);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}
