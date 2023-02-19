package com.twentytwo.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    private String name;
    private List<Subscriber> subs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Channel(String name){
        this.name = name;
    }

    public void addSubscriber(Subscriber sub){
        this.subs.add(sub);
    }

    public void removeSubscriber(Subscriber sub){
        this.subs.remove(sub);
    }

    public void uploadVideo(){
        notifySubscribers();
    }

    private void notifySubscribers(){
        for(Subscriber sub : subs){
            System.out.println("Hey " + sub.getName() + ", a new video has been uploaded");
        }
    }
}
