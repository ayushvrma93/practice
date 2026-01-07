package com.allen.newsfeed.machinecoding.strategies.timeline;

import com.allen.newsfeed.machinecoding.components.user.IUserManager;
import com.allen.newsfeed.machinecoding.components.user.UserManagerImpl;
import com.allen.newsfeed.machinecoding.exceptions.UserNotExistsException;
import com.allen.newsfeed.machinecoding.models.Post;
import com.allen.newsfeed.machinecoding.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NewestFirstStrategyTest {

    private NewestFirstStrategy strategy;
    private IUserManager mockUserManager;
    private User testUser;
    private User follower1;
    private User follower2;
    private Post testPost;

    @BeforeEach
    void setUp() throws Exception {
        strategy = NewestFirstStrategy.getINSTANCE();
        
        // Create test objects
        testUser = new User("testUser", "test@example.com");
        follower1 = new User("follower1", "follower1@example.com");
        follower2 = new User("follower2", "follower2@example.com");
        testPost = new Post("Test post content", testUser);
        
        // Mock the user manager
        mockUserManager = mock(IUserManager.class);
        
        // Use reflection to replace the userManager field
        Field userManagerField = NewestFirstStrategy.class.getDeclaredField("userManager");
        userManagerField.setAccessible(true);
        userManagerField.set(strategy, mockUserManager);
        
        // Clear the userAndPosts map for each test
        Field userAndPostsField = NewestFirstStrategy.class.getDeclaredField("userAndPosts");
        userAndPostsField.setAccessible(true);
        Map<User, Deque<Post>> userAndPosts = new HashMap<>();
        userAndPostsField.set(strategy, userAndPosts);
    }

    @Test
    void testGetINSTANCE_ReturnsSameInstance() {
        // Test that getInstance returns the same instance (singleton pattern)
        ITimeLineGenerationStrategy instance1 = NewestFirstStrategy.getINSTANCE();
        ITimeLineGenerationStrategy instance2 = NewestFirstStrategy.getINSTANCE();
        
        assertNotNull(instance1);
        assertNotNull(instance2);
        assertSame(instance1, instance2);
        assertTrue(instance1 instanceof NewestFirstStrategy);
    }

    @Test
    void testGenerateTimeLineForUser_UserDoesNotExist_ThrowsException() {
        // Arrange
        when(mockUserManager.exists(testUser)).thenReturn(Optional.empty());
        
        // Act & Assert
        UserNotExistsException exception = assertThrows(UserNotExistsException.class, 
            () -> strategy.generateTimeLineForUser(testUser, testPost));
        
        assertEquals("User does not exist", exception.getMessage());
        verify(mockUserManager).exists(testUser);
    }

    @Test
    void testGenerateTimeLineForUser_UserExistsButNoFollowers_ReturnsFalse() throws UserNotExistsException {
        // Arrange
        when(mockUserManager.exists(testUser)).thenReturn(Optional.of(testUser));
        when(mockUserManager.getFollowersList(testUser)).thenReturn(null);
        
        // Act
        boolean result = strategy.generateTimeLineForUser(testUser, testPost);
        
        // Assert
        assertFalse(result);
        verify(mockUserManager).exists(testUser);
        verify(mockUserManager).getFollowersList(testUser);
    }

    @Test
    void testGenerateTimeLineForUser_UserExistsButEmptyFollowersList_ReturnsFalse() throws UserNotExistsException {
        // Arrange
        when(mockUserManager.exists(testUser)).thenReturn(Optional.of(testUser));
        when(mockUserManager.getFollowersList(testUser)).thenReturn(new ArrayList<>());
        
        // Act
        boolean result = strategy.generateTimeLineForUser(testUser, testPost);
        
        // Assert
        assertFalse(result);
        verify(mockUserManager).exists(testUser);
        verify(mockUserManager).getFollowersList(testUser);
    }

    @Test
    void testGenerateTimeLineForUser_UserExistsWithFollowers_ReturnsTrue() throws Exception {
        // Arrange
        List<User> followers = Arrays.asList(follower1, follower2);
        when(mockUserManager.exists(testUser)).thenReturn(Optional.of(testUser));
        when(mockUserManager.getFollowersList(testUser)).thenReturn(followers);
        
        // Initialize followers' timelines
        Field userAndPostsField = NewestFirstStrategy.class.getDeclaredField("userAndPosts");
        userAndPostsField.setAccessible(true);
        Map<User, Deque<Post>> userAndPosts = (Map<User, Deque<Post>>) userAndPostsField.get(strategy);
        userAndPosts.put(follower1, new LinkedList<>());
        userAndPosts.put(follower2, new LinkedList<>());
        
        // Act
        boolean result = strategy.generateTimeLineForUser(testUser, testPost);
        
        // Assert
        assertTrue(result);
        verify(mockUserManager).exists(testUser);
        verify(mockUserManager).getFollowersList(testUser);
        
        // Verify post was added to followers' timelines
        assertEquals(testPost, userAndPosts.get(follower1).peekFirst());
        assertEquals(testPost, userAndPosts.get(follower2).peekFirst());
    }

    @Test
    void testGetTimeLineForUser_UserExists_ReturnsTimeline() throws Exception {
        // Arrange
        Deque<Post> expectedTimeline = new LinkedList<>();
        expectedTimeline.add(testPost);
        
        Field userAndPostsField = NewestFirstStrategy.class.getDeclaredField("userAndPosts");
        userAndPostsField.setAccessible(true);
        Map<User, Deque<Post>> userAndPosts = (Map<User, Deque<Post>>) userAndPostsField.get(strategy);
        userAndPosts.put(testUser, expectedTimeline);
        
        // Act
        Deque<Post> result = strategy.getTimeLineForUser(testUser);
        
        // Assert
        assertSame(expectedTimeline, result);
        assertEquals(1, result.size());
        assertEquals(testPost, result.peekFirst());
    }

    @Test
    void testGetTimeLineForUser_UserDoesNotExist_ReturnsNull() {
        // Act
        Deque<Post> result = strategy.getTimeLineForUser(testUser);
        
        // Assert
        assertNull(result);
    }
}
