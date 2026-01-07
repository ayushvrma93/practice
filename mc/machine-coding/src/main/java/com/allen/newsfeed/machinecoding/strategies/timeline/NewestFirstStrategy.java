package com.allen.newsfeed.machinecoding.strategies.timeline;

import com.allen.newsfeed.machinecoding.components.post.IPostManager;
import com.allen.newsfeed.machinecoding.components.user.IUserManager;
import com.allen.newsfeed.machinecoding.components.user.UserManagerImpl;
import com.allen.newsfeed.machinecoding.exceptions.UserNotExistsException;
import com.allen.newsfeed.machinecoding.models.Post;
import com.allen.newsfeed.machinecoding.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class NewestFirstStrategy implements ITimeLineGenerationStrategy{

    private static final ITimeLineGenerationStrategy INSTANCE = new NewestFirstStrategy();

    private NewestFirstStrategy(){}

    public static ITimeLineGenerationStrategy getINSTANCE() {
        return INSTANCE;
    }

    private final IUserManager userManager = UserManagerImpl.getINSTANCE();

    private Map<User, Deque<Post>> userAndPosts = new HashMap<>();

    /**
     * Generates timeline for followers of the given user by adding the post to their timelines.
     * Uses newest-first strategy where new posts are added to the front of the timeline.
     *
     * @param user the user who created the post
     * @param post the post to be added to followers' timelines
     * @return true if the post was successfully added to at least one follower's timeline, false if user has no followers
     * @throws UserNotExistsException if the specified user does not exist in the system
     */
    @Override
    public boolean generateTimeLineForUser(User user, Post post) throws UserNotExistsException{

        if(!userManager.exists(user).isPresent()){
            throw new UserNotExistsException("User does not exist");
        }

        List<User> followers = userManager.getFollowersList(user);

        if(followers == null || followers.isEmpty()){
            System.out.println("No one follows you..");
            return false;
        }

        for(User follower : followers){
            if(userAndPosts.containsKey(follower)){
                Deque timeline = userAndPosts.get(follower);

                if(timeline == null){
                    timeline = new LinkedList();
                }
                timeline.addFirst(post);
            }
        }
        return true;
    }

    @Override
    public Deque<Post> getTimeLineForUser(User user) {
        return userAndPosts.get(user);
    }
}
