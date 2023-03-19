package com.allen.newsfeed.machinecoding.strategies.timeline;

import com.allen.newsfeed.machinecoding.models.Post;
import com.allen.newsfeed.machinecoding.models.User;

import java.util.Deque;

public interface ITimeLineGenerationStrategy {

    boolean generateTimeLineForUser(User user, Post post);

    Deque<Post> getTimeLineForUser(User user);
}
