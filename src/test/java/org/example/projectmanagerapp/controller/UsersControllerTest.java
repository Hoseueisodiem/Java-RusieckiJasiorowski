package org.example.projectmanagerapp.controller;

import org.example.projectmanagerapp.entity.Users;
import org.example.projectmanagerapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersControllerTest {

    private UserService userService;
    private UsersController usersController;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        usersController = new UsersController(userService);
    }

    @Test
    @DisplayName("Should return all users")
    void testGetAllUsers() {
        Users user1 = new Users();
        Users user2 = new Users();
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        List<Users> users = usersController.getAllUsers();

        assertEquals(2, users.size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    @DisplayName("Should create a user")
    void testCreateUser() {
        Users user = new Users();
        user.setUsername("Test");
        when(userService.saveUser(user)).thenReturn(user);

        Users created = usersController.createUser(user);

        assertEquals("Test", created.getUsername());
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    @DisplayName("Should update a user")
    void testUpdateUser() {
        Users user = new Users();
        when(userService.saveUser(user)).thenReturn(user);

        Users updated = usersController.updateUser(1L, user);

        assertNotNull(updated);
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    @DisplayName("Should delete a user")
    void testDeleteUser() {
        usersController.deleteUser(1L);
        verify(userService, times(1)).deleteUser(1L);
    }
}