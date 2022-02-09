package com.multithreading;

public class Person implements Runnable{
    @Override
    public void run() {
        System.out.println("Person is running");
        try {
            Thread.sleep(2000);
            System.out.println("Person ran and is now tired");
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception occurred");
        }
    }

    public static void main(String[] args) {

        Runnable person;
        person = new Person();

        Thread thread1 = new Thread(person);

        thread1.start();
    }
}
