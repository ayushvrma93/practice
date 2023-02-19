package com.twentytwo.multithreading;

public class PrintOddEven {

    private int currCount = 0;
    private int MAX_NUM;

    public PrintOddEven(int maxNum){
        this.MAX_NUM = maxNum;
    }

    public void printOdd(){

        synchronized (this) {
            while (currCount < MAX_NUM) {
                if (currCount % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(currCount + " ");
                currCount++;
                notify();
            }
        }
    }


    public void printEven(){

        synchronized (this) {
            while (currCount < MAX_NUM) {
                if (currCount % 2 == 1) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(currCount);
                currCount++;
                notify();
            }
        }
    }

    public static void main(String[] args) {

        PrintOddEven printOddEven = new PrintOddEven(20);

        Thread thread1 = new Thread(printOddEven::printOdd);

        Thread thread2 = new Thread(() -> printOddEven.printEven());

        thread1.start();
        thread2.start();
    }
}
