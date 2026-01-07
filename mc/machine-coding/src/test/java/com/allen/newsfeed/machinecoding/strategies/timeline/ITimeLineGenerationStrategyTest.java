package com.allen.newsfeed.machinecoding.strategies.timeline;

import com.allen.newsfeed.machinecoding.models.Post;
import com.allen.newsfeed.machinecoding.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ITimeLineGenerationStrategyTest {

    @Mock
    private User mockUser;

    @Mock
    private Post mockPost;

    private ITimeLineGenerationStrategy timeLineGenerationStrategy;

    @BeforeEach
    void setUp() {
        // Create a concrete implementation for testing the interface
        timeLineGenerationStrategy = new TestTimeLineGenerationStrategy();
    }

    @Test
    void testGenerateTimeLineForUser_WithValidUserAndPost_ShouldReturnTrue() {
        // Given
        when(mockUser.getId()).thenReturn("user1");
        when(mockPost.getId()).thenReturn("post1");

        // When
        boolean result = timeLineGenerationStrategy.generateTimeLineForUser(mockUser, mockPost);

        // Then
        assertTrue(result);
    }

    @Test
    void testGenerateTimeLineForUser_WithNullUser_ShouldReturnFalse() {
        // When
        boolean result = timeLineGenerationStrategy.generateTimeLineForUser(null, mockPost);

        // Then
        assertFalse(result);
    }

    @Test
    void testGenerateTimeLineForUser_WithNullPost_ShouldReturnFalse() {
        // When
        boolean result = timeLineGenerationStrategy.generateTimeLineForUser(mockUser, null);

        // Then
        assertFalse(result);
    }

    @Test
    void testGetTimeLineForUser_WithValidUser_ShouldReturnDeque() {
        // Given
        when(mockUser.getId()).thenReturn("user1");

        // When
        Deque<Post> result = timeLineGenerationStrategy.getTimeLineForUser(mockUser);

        // Then
        assertNotNull(result);
        assertTrue(result instanceof Deque);
    }

    @Test
    void testGetTimeLineForUser_WithNullUser_ShouldReturnEmptyDeque() {
        // When
        Deque<Post> result = timeLineGenerationStrategy.getTimeLineForUser(null);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // Test implementation of the interface for testing purposes
    private static class TestTimeLineGenerationStrategy implements ITimeLineGenerationStrategy {
        
        @Override
        public boolean generateTimeLineForUser(User user, Post post) {
            if (user == null || post == null) {
                return false;
            }
            return true;
        }

        @Override
        public Deque<Post> getTimeLineForUser(User user) {
            if (user == null) {
                return new ArrayDeque<>();
            }
            return new ArrayDeque<>();
        }
    }
}
