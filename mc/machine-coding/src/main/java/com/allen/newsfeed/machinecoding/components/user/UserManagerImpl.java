package com.allen.newsfeed.machinecoding.components.user;

import com.allen.newsfeed.machinecoding.exceptions.UserNotExistsException;
import com.allen.newsfeed.machinecoding.models.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserManagerImpl implements IUserManager{

    private static final IUserManager INSTANCE = new UserManagerImpl();

    private UserManagerImpl(){}

    public static IUserManager getINSTANCE() {
        return INSTANCE;
    }

    Set<User> users = new HashSet<>();
    Map<User, List<User>> userFollowerMap = new HashMap<>();

    @Override
    public void login(User user) {

        if(users.contains(user)){
            System.out.println("Welcome back " + user.getName());
        }
        else{
            System.out.println("Signing you up..");
            users.add(user);
        }
    }

    @Override
    public Optional<User> exists(User user) {
        return users.contains(user) ? Optional.ofNullable(user) : Optional.empty();
    }

    @Override
    public boolean follow(User follower, User followed) throws UserNotExistsException {

        if(!exists(follower).isPresent() || !exists(followed)
                .isPresent()){
            throw new UserNotExistsException("User does not exist");
        }

        if(!userFollowerMap.containsKey(followed)){
            List<User> followers = new ArrayList<>();
            followers.add(followed);
        }

        else{
            List<User> followers = userFollowerMap.get(followed);
            followers.add(follower);
        }
        //see, if put is required.
        return true;
    }

    public List<User> getFollowersList(User followed){

        if(!exists(followed).isPresent()){
            throw new UserNotExistsException("User does not exist");
        }
        return userFollowerMap.get(followed);
    }

}
