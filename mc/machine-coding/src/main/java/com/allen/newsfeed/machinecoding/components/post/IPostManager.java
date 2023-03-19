package com.allen.newsfeed.machinecoding.components.post;

import com.allen.newsfeed.machinecoding.models.Post;
import com.allen.newsfeed.machinecoding.models.User;

import java.util.List;
import java.util.Optional;

public interface IPostManager {

    Optional<Post> create(User user, Post post);
    List<Post> get(User user);
}
