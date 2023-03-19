package com.allen.newsfeed.machinecoding;

import com.allen.newsfeed.machinecoding.components.post.IPostManager;
import com.allen.newsfeed.machinecoding.components.post.PostManagerImpl;
import com.allen.newsfeed.machinecoding.components.user.IUserManager;
import com.allen.newsfeed.machinecoding.components.user.UserManagerImpl;
import com.allen.newsfeed.machinecoding.models.Post;
import com.allen.newsfeed.machinecoding.models.User;
import com.allen.newsfeed.machinecoding.strategies.timeline.ITimeLineGenerationStrategy;
import com.allen.newsfeed.machinecoding.strategies.timeline.NewestFirstStrategy;

public class Driver {

    private static IUserManager userManager = UserManagerImpl.getINSTANCE();

    private static IPostManager postManager = PostManagerImpl.getINSTANCE();

    //this should come from Factory Design Pattern
    private static ITimeLineGenerationStrategy timeLineGenerationStrategy = NewestFirstStrategy.getINSTANCE();

    public static void main(String[] args) {

        User u1 = new User("u1", "u1@gmail.com");
        User u2 = new User("u2", "u2@gmail.com");
        User u3 = new User("u3", "u3@gmail.com");

        userManager.login(u1);
        userManager.login(u2);
        //System.out.println(postManager.create(u2, new Post("First post by u2", u1, 0)));
        System.out.println(postManager.get(u1));
        System.out.println(userManager.follow(u2, u1));
        System.out.println(postManager.create(u1, new Post("First post by u2", u1, 0)));
        System.out.println(timeLineGenerationStrategy.getTimeLineForUser(u1));
        System.out.println(timeLineGenerationStrategy.getTimeLineForUser(u2));
    }


}
