package com.fanxan.bepo.controller;


import com.fanxan.bepo.exception.ResourceNotFoundException;
import com.fanxan.bepo.models.WebResponse;
import com.fanxan.bepo.models.entity.Users;
import com.fanxan.bepo.models.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private Users user;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPhone("1234567890");
    }

    @Test
    void getAllUsers_ShouldReturnAllUsers() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(user);
        when(userService.getAllUsers()).thenReturn(expectedUsers);

        // Act
        WebResponse<List<Users>> response = userController.getAllUsers();

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Success", response.getMessage());
        assertEquals(expectedUsers, response.getData());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserById_WithExistingId_ShouldReturnUser() {
        // Arrange
        when(userService.getUserById(1)).thenReturn(Optional.of(user));

        // Act
        WebResponse<Users> response = userController.getUserById(1);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("Success", response.getMessage());
        assertEquals(user, response.getData());
        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void getUserById_WithNonExistingId_ShouldThrowException() {
        // Arrange
        when(userService.getUserById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userController.getUserById(999));
        verify(userService, times(1)).getUserById(999);
    }

    @Test
    void createUser_ShouldCreateAndReturnUser() {
        // Arrange
        when(userService.createUser(any(Users.class))).thenReturn(user);

        // Act
        WebResponse<Users> response = userController.createUser(user);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("User created successfully", response.getMessage());
        assertEquals(user, response.getData());
        verify(userService, times(1)).createUser(user);
    }

    @Test
    void updateUser_WithExistingId_ShouldUpdateAndReturnUser() {
        // Arrange
        when(userService.updateUser(eq(1), any(Users.class))).thenReturn(Optional.of(user));

        // Act
        WebResponse<Users> response = userController.updateUser(1, user);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("User updated successfully", response.getMessage());
        assertEquals(user, response.getData());
        verify(userService, times(1)).updateUser(eq(1), any(Users.class));
    }

    @Test
    void updateUser_WithNonExistingId_ShouldThrowException() {
        // Arrange
        when(userService.updateUser(eq(999), any(Users.class))).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userController.updateUser(999, user));
        verify(userService, times(1)).updateUser(eq(999), any(Users.class));
    }

    @Test
    void deleteUser_WithExistingId_ShouldReturnSuccessResponse() {
        // Arrange
        when(userService.deleteUser(1)).thenReturn(true);

        // Act
        WebResponse<Void> response = userController.deleteUser(1);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals("User deleted successfully", response.getMessage());
        assertNull(response.getData());
        verify(userService, times(1)).deleteUser(1);
    }

    @Test
    void deleteUser_WithNonExistingId_ShouldThrowException() {
        // Arrange
        when(userService.deleteUser(999)).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userController.deleteUser(999));
        verify(userService, times(1)).deleteUser(999);
    }
}