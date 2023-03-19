package com.allen.newsfeed.machinecoding.components.post;

import com.allen.newsfeed.machinecoding.components.user.IUserManager;
import com.allen.newsfeed.machinecoding.components.user.UserManagerImpl;
import com.allen.newsfeed.machinecoding.models.Post;
import com.allen.newsfeed.machinecoding.models.User;
import com.allen.newsfeed.machinecoding.strategies.timeline.ITimeLineGenerationStrategy;
import com.allen.newsfeed.machinecoding.strategies.timeline.NewestFirstStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PostManagerHelper {

    private IUserManager userManager = UserManagerImpl.getINSTANCE();

    private static ITimeLineGenerationStrategy timeLineGenerationStrategy = NewestFirstStrategy.getINSTANCE();

    public static Map<User, List<Post>> userAndPostsMap = new HashMap<>();

    public static boolean addPost(User user, Post post){
        return timeLineGenerationStrategy.generateTimeLineForUser(user, post);
    }
}
