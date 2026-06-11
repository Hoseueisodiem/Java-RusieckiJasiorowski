package org.example.projectmanagerapp.service;

import org.example.projectmanagerapp.entity.Users;
import org.example.projectmanagerapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Should return all users")
    void testGetAllUsers() {
        Users user1 = new Users();
        user1.setUsername("TestUser1");
        Users user2 = new Users();
        user2.setUsername("TestUser2");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<Users> users = userService.getAllUsers();

        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should save a user")
    void testSaveUser() {
        Users user = new Users();
        user.setUsername("NewUser");

        when(userRepository.save(user)).thenReturn(user);

        Users saved = userService.saveUser(user);

        assertEquals("NewUser", saved.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Should return user by ID")
    void testGetUserById() {
        Users user = new Users();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<Users> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should delete a user")
    void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should update a user")
    void testUpdateUser() {
        Users user = new Users();
        user.setUsername("Updated");

        when(userRepository.save(user)).thenReturn(user);

        Users updated = userService.updateUser(1L, user);

        assertEquals("Updated", updated.getUsername());
        verify(userRepository, times(1)).save(user);
    }
}