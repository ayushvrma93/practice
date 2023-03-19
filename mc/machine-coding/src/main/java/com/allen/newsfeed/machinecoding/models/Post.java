package com.allen.newsfeed.machinecoding.models;

import lombok.Data;

@Data
public class Post {

    private int id;
    private String text;
    private User madeBy;
    private int upVotes;
    private int downVotes;

    private int timeStamp;

    private static int currId =1;

    public Post(String text, User madeBy, int timeStamp) {
        this. id = currId++;
        this.text = text;
        this.madeBy = madeBy;
        this.timeStamp = timeStamp;
    }
}
