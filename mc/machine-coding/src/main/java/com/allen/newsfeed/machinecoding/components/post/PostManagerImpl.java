package com.allen.newsfeed.machinecoding.components.post;

import com.allen.newsfeed.machinecoding.components.user.IUserManager;
import com.allen.newsfeed.machinecoding.components.user.UserManagerImpl;
import com.allen.newsfeed.machinecoding.exceptions.UserNotExistsException;
import com.allen.newsfeed.machinecoding.models.Post;
import com.allen.newsfeed.machinecoding.models.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostManagerImpl implements IPostManager{

    private static final IPostManager INSTANCE = new PostManagerImpl();

    private PostManagerImpl(){}

    public static IPostManager getINSTANCE() {
        return INSTANCE;
    }

    private static final IUserManager userManager = UserManagerImpl.getINSTANCE();

    Map<Integer, Post> posts = new HashMap<>();

    Map<User, List<Integer>> userPostMap = new HashMap<>();

    @Override
    public Optional<Post> create(User user, Post post) {

        if(!userManager.exists(user).isPresent()){
            throw new UserNotExistsException("User does not exist, please login to post");
        }
        Integer postId = post.getId();

        List<Integer> postIds;

        if(!userPostMap.containsKey(user)){
            postIds = new ArrayList<>();
        }

        else {
            postIds = userPostMap.get(user);
        }

        postIds.add(postId);
        posts.put(postId, post);
        userPostMap.put(user, postIds);
        PostManagerHelper.addPost(user, post);
        return Optional.ofNullable(posts.get(postId));
    }

    @Override
    public List<Post> get(User user) {

        if(!userManager.exists(user).isPresent()){
            throw new UserNotExistsException("User does not exist, please login to post");
        }

        List<Post> postList = new ArrayList<>();

        for(Integer postId : userPostMap.get(user)){
            postList.add(posts.get(postId));
        }
        return postList;
    }
}
