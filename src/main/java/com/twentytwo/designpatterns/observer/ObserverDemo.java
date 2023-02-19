package com.twentytwo.designpatterns.observer;

public class ObserverDemo {

    public static void main(String[] args) {

        Subscriber subscriber1 = new Subscriber("Ayush");
        Subscriber subscriber2 = new Subscriber("Aman");
        Subscriber subscriber3 = new Subscriber("Suyash");


        Channel channel = new Channel("Test Channel");

        channel.addSubscriber(subscriber1);
        channel.addSubscriber(subscriber2);
        channel.addSubscriber(subscriber3);

        channel.removeSubscriber(subscriber1);

        channel.uploadVideo();
    }
}
