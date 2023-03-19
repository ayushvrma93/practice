package com.allen.newsfeed.machinecoding.components.user;

import com.allen.newsfeed.machinecoding.exceptions.UserNotExistsException;
import com.allen.newsfeed.machinecoding.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserManager {

    void login(User user);
    Optional<User> exists(User user);

    boolean follow(User follower, User followed) throws UserNotExistsException;

    List<User> getFollowersList(User user);
}
